var gMode;
var gIndex;
var gEntry;
var gCurrentEmployeeID;
var gEntries;
var gModal;
var gHeadings;
var span;

function body_onload() {
    displayModal("signInModal");
    // Make sure someone is signed in.
    gCurrentEmployeeID = sessionStorageGet("CurrentEmployeeID", null);

    /* ========== SIGN IN START ==========*/ 
    btnSignIn.onclick = btnSignIn_click;
    
        // Populate the UI with any values we saved.
        
        txtSignInEmployeeID.value = localStorageGet("EmployeeID", "");

        if(localStorageGet("Remember", false) === "false"){
            txtCheckBox.checked = false;
        }else{
            txtCheckBox.checked = localStorageGet("Remember", false);
        }
        
    
        if (txtSignInEmployeeID.value === "") {
            txtSignInEmployeeID.focus();
        }
        else {
            txtPassword.focus();
        }
    /* ========== SIGN IN END ==========*/



    /* ========== MAIN HEADINGS START ========== */
    employeeHeading.onclick = employeeHeading_onclick;
    dateWorkedHeading.onclick = dateWorkedHeading_onclick;
    hoursWorkedHeading.onclick = hoursWorkedHeading_onclick;
    descriptionHeading.onclick = descriptionHeading_onclick;

    gHeadings = {
        EmployeeID : "Employee",
        DateWorked: "Date",
        HoursWorked: "Hours",
        Description: "Description"
    };
    /* ========== MAIN HEADINGS END ========== */



    /* ========== EDIT START ========== */
        btnSave.onclick = btnSave_onclick;
        btnDelete.onclick = btnDelete_onclick;
    /* ========== EDIT END ========== */

   

}


/* ========== MAIN START ========== */
function btnAddNew_onclick() {
    gMode = "add";
    displayModal("editModal");
    displayEditFormValues();    
}

function btnRefresh_onclick() {
    tableLogEntriesData.innerHTML = "";
	displayEntries();

    // Save the search critera state.

    sessionStorageSet("txtDescContains", txtDescriptionContains.value);
	sessionStorageSet("txtDateFrom"    , txtDateFrom.value);
	sessionStorageSet("txtDateThrough" , txtDateThrough.value);
    sessionStorageSet("chkShowCurrEmp", currentEmployeeCheckBox.value);
}

function row_ondblclick() {
   gIndex = this.index;
   
   
   
   gMode= "edit";
   displayModal("editModal");
   displayEditFormValues();
}

function displayEntries() {
    getEntries();
    getHeadings();
    tableLogEntriesData.innerHTML = "";
    var totalHoursWorked = 0;

    for (var i = 0; i < gEntries.length; i++) {

        var entry = gEntries[i];

        if (descriptionContains(entry) && dateBetween(entry) && isCurrentEmployee(entry)) {

            var row = document.createElement("div");
            var col1 = document.createElement("div");
            var col2 = document.createElement("div");
            var col3 = document.createElement("div");
            var col4 = document.createElement("div");

            row.className = "divEmployeeRow";
            row.Index = i;

            col1.className = "divEmployeeID";
            col2.className = "divDateWorked";
            col3.className = "divHoursWorked";
            col4.className = "divDescription";

            col1.innerHTML = entry.EmployeeID;
            col2.innerHTML = entry.DateWorked;
            col3.innerHTML = entry.HoursWorked;
            col4.innerHTML = entry.Description;

            row.ondblclick = row_ondblclick;
            row.index = i;
            row.style.cursor = "pointer";

            row.appendChild(col1);
            row.appendChild(col2);
            row.appendChild(col3);
            row.appendChild(col4);

            tableLogEntriesData.appendChild(row);

            totalHoursWorked += parseFloat(entry.HoursWorked);
        }
    }

    txtTotalHoursWorked.innerHTML = "Total Hours Worked: " + totalHoursWorked.toFixed(2).toString();
}

function descriptionContains(entry) {
    if (txtDescriptionContains.value === "") {
        return true;
    }

    var searchFor = txtDescriptionContains.value.toLowerCase();

    if (entry.Description.toLowerCase().includes(searchFor) === true) {
        return true;
    }
    
    return false;
}

function isCurrentEmployee(entry) {

    if (currentEmployeeCheckBox.checked === true) {
        if (entry.EmployeeID === gCurrentEmployeeID) {
            return true;
        }
        return false;
    }
    return true;
}


function dateBetween(entry) {

	if (validDate(txtDateFrom.value)) {
	    if (new Date(txtDateFrom.value) > new Date(entry.DateWorked)) {
			return false;
		}
	}

	if (validDate(txtDateThrough.value)) {
	    if (new Date(txtDateThrough.value) < new Date(entry.DateWorked)) {
			return false;
		}
	}

	return true;
}
/* ========== MAIN END ========== */

/* ========== SIGN IN START ========== */

function btnSignIn_click() {
    
        if (signinEmployee(txtSignInEmployeeID.value, txtPassword.value) === false) {
            alertModal("EmployeeID and/or Password is incorrect", gModal);
            return;
        }
    
        // Save the Sign In state for the next time someone signs in.
    
        localStorageSet("Remember", txtCheckBox.checked);
    
        if (txtCheckBox.checked === true) {
            localStorageSet("EmployeeID", txtSignInEmployeeID.value);
        }
        else {
            localStorageSet("EmployeeID", "");
        }
    
        // Save the signed in employee in session state so other pages know someone is signed in.
    
        sessionStorageSet("CurrentEmployeeID", txtSignInEmployeeID.value);
        currentEmployeeID = sessionStorageGet("CurrentEmployeeID", null);
        // Navigate to the main page.
    
        gModal.style.display = "none";
        displayMain();
}
    

/* ========== SIGN IN END ========== */


/* ========== EDIT START ========== */

function btnSave_onclick() {
    
        // Check to make sure all the inputs are valid
    
        if (txtEditEmployeeID.value === "") {
            alertModal("Employee ID is required.", gModal);
            txtEditEmployeeID.focus();
            return;
        }
    
        if (!validHoursWorked(txtHoursWorked.value)) {
            alertModal("Hours Worked must be a valid number greater than zero and less than 4.00, and only in fifteen-minute intervals.", gModal);
            txtHoursWorked.focus();
            return;
        }
    
        if (!validDate(txtDateWorked.value)) {
            alertModal("Date Worked is required.", gModal);
            txtDateWorked.focus();
            return;
        }
    
        if (txtDescription.value.length < 20) {
            alertModal("Description is required and must be at least 20 characters long.", gModal);
            txtDescription.focus();
            return;
        }
    
        // Create a new entry object using the inputs from the user and either add it to the list,
        // or replace the exsiting entry in the list.
    
        var entry = {
            EmployeeID: txtEditEmployeeID.value,
            DateWorked: txtDateWorked.value,
            HoursWorked: txtHoursWorked.value,
            Billable: txtBillable.checked,
            Description: txtDescription.value
        }
        if (gMode === "add") { 
            gEntries.push(entry);
        }
        else {
            gEntries[gIndex] = entry;
        }
    
        saveEntries();
        gModal.style.display = "none";
        displayEntries();
    }
    
function btnDelete_onclick() {
    userConfirmationMessage("Are you sure you want to delete this entry?", gModal, "delete");                
}

function deleteEntry() {
    gEntries.splice(gIndex, 1);
    saveEntries();
    gModal.style.display = "none";
    displayEntries();
}        
function validHoursWorked(hrs) {
    var floatHrs = parseFloat(hrs);
    if (floatHrs <= 0 || hrs >= 4.00) {
        return false;
    }
    if (floatHrs * 4 === parseInt(floatHrs * 4)) {
        return true;
    }
        return false;
}
    
/* ========== EDIT END ========== */

/* ========== SHARED START ========== */

    // Init the UI.
function displayMain(){
        btnAddNew.onclick = btnAddNew_onclick;
        btnRefresh.onclick = btnRefresh_onclick;
    
        txtDescriptionContains.value = sessionStorageGet("txtDescContains", "");
        txtDateFrom.value     = sessionStorageGet("txtDateFrom", "");
        txtDateThrough.value  = sessionStorageGet("txtDateThrough", "");
        currentEmployeeCheckBox.value  = sessionStorageGet("chkShowCurrEmp", false);
    
        getEntries();
        displayEntries();
        getHeadings();
}

function displayModal(modal){
    gModal = document.getElementById(modal);
    
        if (modal === "signInModal") {
            span = document.getElementById("signInClose");
        } else if (modal === "editModal") {
            span = document.getElementById("editClose");
        } else if (modal === "alertMessageModal") {
            span = document.getElementById("alertMessageClose");
        } else if (modal === "confirmationMessageModal") {
            span = document.getElementById("confirmationMessageClose");
        }
        gModal.style.display = "block";
        
        gModal.onclick = function(event){
            if(event.target == gModal){
                if(modal === "editModal"){
                    userConfirmationMessage("Are you sure you want to discard your changes to this entry?", gModal, "cancel");
                }else if(checkLoginStatus()){
                    gModal.style.display = "none";
                }else{
                    alertModal("Sign In Required.", gModal);
                }
            }
        }


        span.onclick = function(){
            if(modal === "editModal"){
                userConfirmationMessage("Are you sure you want to discard your changes to this entry?", gModal, "cancel");
            }else if(checkLoginStatus()){
                gModal.style.display = "none";
            }else{
                alertModal("Sign In Required.", gModal);
            }
        }
}

function alertModal(message, lastModal){
    displayModal("alertMessageModal");
    alertMessage.innerHTML = message;

    btnOk.onclick = function() {
        gModal.style.display = "none";
        gModal = lastModal;
    }

    span.onclick = function() {
        gModal.style.display = "none";
        gModal = lastModal;
    }

    gModal.onclick = function(event) {
        if (event.target == gModal) {
            modal.style.display = "none";
            gModal = lastModal;
        }
    }
}

function checkLoginStatus(){
    currentEmployeeID = sessionStorageGet("CurrentEmployeeID", null);
    if(currentEmployeeID === "null" || currentEmployeeID === null){
        return false;
    }else{
        return true;
    }
}


function getEntries() {
    
        gEntries = new Array();
    
        var json = localStorageGet("Entries", null); //Load the string we saved earlier
    
        if (json === null) {
            return;
        }
    
        gEntries = JSON.parse(json); //Convert the string we loaded into an array
    }
    
    function saveEntries() {
    
        var json = JSON.stringify(gEntries); //Convert data array to a single string
    
        localStorage.setItem("Entries", json); //Store the string into local storage
    }
    
    function signinEmployee(employeeID, password) {
    
        var employees = new Array();
    
        employees.push({ EmployeeID: "Barry", Password: "Allen" });
        employees.push({ EmployeeID: "Iris", Password: "West" });
        employees.push({ EmployeeID: "Wally", Password: "West" });
        employees.push({ EmployeeID: "Cisco", Password: "Ramon" });
        employees.push({ EmployeeID: "Caitlin", Password: "Snow" });
    
        for (var i = 0; i < employees.length; i++) {
    
            if (employees[i].EmployeeID.toLowerCase() === employeeID.toLowerCase() && employees[i].Password === password) {
                return true;
            }
        }
    
        return false;
    }
    
    function validDate(dateString) {
    
        var date = new Date(dateString);
    
        if (date === "Invalid Date") {
            return false;
        }
    
        return true;
    }
    
    function getQueryStringValue(name, defaultValue) {
    
        // Remove the ?
    
        var queryString = location.search.substr(1);
    
        // Split into key/value pairs.
    
        var values = queryString.split("&");
    
        // Look for a matching key.
    
        for (var i = 0; i < values.length; i++) {
    
            var parts = values[i].split("=");
    
            if (parts[0] === name) {
                return parts[1];
            }
        }
    
        // If we get here we didn't find the key.
    
        return defaultValue;
    }
    
    function tryParse(stringValue, maxDecimals) {
        var char;
        var dotFound = false;
        var numberDecimals = 0;
    
        if (stringValue === "") {
            return false;
        }
    
        for (var i = 0; i < stringValue.length; i++) {
    
            char = stringValue.charAt(i);
    
            if (char === ".") {
                if (dotFound === true) {
                    return false;
                }
                dotFound = true;
            }
            else if (char === "-") {
                if (i > 0) {
                    return false;
                }
            }
            else if (char < "0" || char > "9") {
                return false;
            }
            else {
    
                // If we get here, we must have a number character.  Make sure we haven't found
                // too many decimal positions.
    
                if (dotFound === true) {
                    numberDecimals++;
                    if (numberDecimals > maxDecimals) {
                        return false;
                    }
                }
            }
        }
    
        // If we get here, the value is OK.
    
        return true;
    }
    // These functions encapsulate local and session storage for consistencty and to simplify handling 
    // default values.
    
    function localStorageGet(token, defaultValue) {
    
        var value = localStorage.getItem(token);
    
        if (value === null) {
            return defaultValue;
        }
    
        return value;
    }
    
    function localStorageSet(token, value) {
        localStorage.setItem(token, value);
    }
    
    function sessionStorageGet(token, defaultValue) {
    
        var value = sessionStorage.getItem(token);
    
        if (value === null) {
            return defaultValue;
        }
    
        return value;
    }
    
    function sessionStorageSet(token, value) {
        sessionStorage.setItem(token, value);
    }

    function displayEditFormValues(){
        if (gMode === "edit") {
            gEntry = gEntries[gIndex];
            
            txtEditEmployeeID.value  = gEntry.EmployeeID;
            txtHoursWorked.value = gEntry.HoursWorked;
            txtDateWorked.value  = gEntry.DateWorked;
            txtBillable.checked  = gEntry.Billable;
            txtDescription.value = gEntry.Description;
            btnDelete.disabled = false;
        }
        else {
            txtEditEmployeeID.value = gCurrentEmployeeID;
            txtHoursWorked.value = "";
            var date = new Date();
            txtBillable.checked = false;
            txtDateWorked.valueAsDate = date;
            txtDescription.value = "";
            btnDelete.disabled = true;
        }
    }

    function userConfirmationMessage(message, lastModal, button){
        displayModal("confirmationMessageModal");
        confirmationMessage.innerHTML = message;

        btnYes.onclick = function(){
            gModal.style.display = "none";
            gModal = lastModal;
            if (button === "delete") {
                deleteEntry();
            }else if(button === "cancel"){
                gModal.style.display = "none";
            }
        }

        btnNo.onclick = function(){
            gModal.style.display = "none";
            gModal = lastModal;
        }

        span.onclick = function(){
            gModal.style.display = "none";
            gModal = lastModal;
        }

        gModal.onclick = function(event){
            if(event.target == gModal){
                gModal.style.display = "none";
                gModal = lastModal;
            }
        }
    }

    function getHeadings(){
        var jsonHeadings = localStorageGet("Headings", null);
        if(jsonHeadings === null){
            return;
        }

        gHeadings = JSON.parse(jsonHeadings);
        employeeHeading.innerHTML = gHeadings.EmployeeID;
        dateWorkedHeading.innerHTML = gHeadings.DateWorked;
        hoursWorkedHeading.innerHTML = gHeadings.HoursWorked;
        descriptionHeading.innerHTML = gHeadings.Description;


    }

    function saveHeadings(){
        var jsonHeadings = JSON.stringify(gHeadings);
        localStorage.setItem("Headings", jsonHeadings);
    }

    function employeeHeading_onclick(){
        if(gHeadings.EmployeeID === "Employee &darr;"){
            gHeadings.EmployeeID = "Employee &uarr;";
            gEntries.sort(employeeIDDescending);
        }else{
            gHeadings.EmployeeID = "Employee &darr;";
            gEntries.sort(employeeIDAscending);
        }
        saveEntries();
        saveHeadings();
        displayEntries();
    }

    function dateWorkedHeading_onclick(){
        if(gHeadings.DateWorked === "Date &darr;"){
            gHeadings.DateWorked = "Date &uarr;";            
            gEntries.sort(dateWorkedDescending);
        }else{            
            gHeadings.DateWorked = "Date &darr;";           
            gEntries.sort(dateWorkedAscending);
        }
        saveEntries();
        saveHeadings();
        displayEntries();
    }

    function hoursWorkedHeading_onclick(){
        if(gHeadings.HoursWorked === "Hours &darr;"){           
            gHeadings.HoursWorked = "Hours &uarr;";           
            gEntries.sort(hoursWorkedDescending);
        }else{
            gHeadings.HoursWorked = "Hours &darr;";           
            gEntries.sort(hoursWorkedAscending);
        }
        saveEntries();
        saveHeadings();
        displayEntries();
    }

    function descriptionHeading_onclick(){
        if(gHeadings.Description === "Description &darr;"){          
            gHeadings.Description = "Description &uarr;";
            gEntries.sort(descriptionDescending);
        }else{
            gHeadings.Description = "Description &darr;";
            gEntries.sort(descriptionAscending);
        }
        saveEntries();
        saveHeadings();
        displayEntries();
    }

    function employeeIDAscending(prev, next){
        if(prev.EmployeeID < next.EmployeeID){
            return -1;
        }
        if(prev.EmployeeID > next.EmployeeID){
            return 1;
        }
    }

    function employeeIDDescending(prev, next){
        if(prev.EmployeeID < next.EmployeeID){
            return 1;
        }
        if(prev.EmployeeID > next.EmployeeID){
            return -1;
        }
    }

    function dateWorkedAscending(prev, next){
        if(new Date(prev.DateWorked) < new Date(next.DateWorked)){
            return -1;
        }
        if(new Date(prev.DateWorked) > new Date(next.DateWorked)){
            return 1;
        }
    }

    function dateWorkedDescending(prev, next){
        if(new Date(prev.DateWorked) < new Date(next.DateWorked)){
            return 1;
        }
        if(new Date(prev.DateWorked) > new Date(next.DateWorked)){
            return -1;
        }
    }

    function hoursWorkedAscending(prev, next){
        if(parseFloat(prev.HoursWorked) < parseFloat(next.HoursWorked)){
            return -1;
        }
        if(parseFloat(prev.HoursWorked) > parseFloat(next.HoursWorked)){
            return 1;
        }
    }

    
    function hoursWorkedDescending(prev, next){
        if(parseFloat(prev.HoursWorked) < parseFloat(next.HoursWorked)){
            return 1;
        }
        if(parseFloat(prev.HoursWorked) > parseFloat(next.HoursWorked)){
            return -1;
        }
    }

      
    function descriptionAscending(prev, next){
        if(prev.Description < next.Description){
            return -1;
        }
        if(prev.Description > next.Description){
            return 1;
        }
    }
      
    function descriptionDescending(prev, next){
        if(prev.Description < next.Description){
            return 1;
        }
        if(prev.Description > next.Description){
            return -1;
        }
    }
/* ========== SHARED END ========== */