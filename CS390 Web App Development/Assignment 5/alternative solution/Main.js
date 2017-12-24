"use strict"
var gMode;
var gIndex;
var gEntry;
var gCurrentEmployeeID;
var gEntries;
var gModal;
var span;
var gQuery;

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
     
    /* ========== MAIN HEADINGS END ========== */
 
}


/* ========== MAIN START ========== */

function btnRefresh_onclick() {  
    console.log("refresh clicked");
    sortQuery(null);
    getEntries();	  	
}

function row_ondblclick() {
   gIndex = this.index;  
   gMode= "edit";
   displayModal("editModal");
   displayEditFormValues();
}

function displayEntries() {
    tableLogEntriesData.innerHTML = "";
    var totalHoursWorked = 0;

    for (var i = 0; i < gEntries.length; i++) {

        var entry = gEntries[i];

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

    txtTotalHoursWorked.innerHTML = "Total Hours Worked: " + totalHoursWorked.toFixed(2).toString();
}

function sortQuery(filter){   
    gQuery = "";
    var query = "";  
    query = gQuery;       
    if(currentEmployeeCheckBox.checked === true){
        if(query.length === 0){
            query += "forEmployeeID=" + gCurrentEmployeeID;
        }else{
            query += "&forEmployeeID=" + gCurrentEmployeeID;
        }
    }

    if(txtDescriptionContains.value.length > 0){
        if(query.length === 0){
            query += "descContains=" + txtDescriptionContains.value;
        }else{
            query += "&descContains=" + txtDescriptionContains.value;
        }
    }

    if(validDate(txtDateFrom.value)){
        if(query.length === 0){
            query += "dateFrom=" + txtDateFrom.value;
        }else{
            query += "&dateFrom=" + txtDateFrom.value;
        }
    }

    if(validDate(txtDateThrough.value)){
        if(query.length === 0){
            query += "dateTo=" + txtDateThrough.value;
        }else{
            query += "&dateTo=" + txtDateThrough.value;
        }
    }

    if(filter === "EmployeeID"){
        if(query.length === 0){
            query += "sortBy=EmployeeID";
        }else{
            query += "&sortBy=EmployeeID";
        }
    }

    if(filter === "DateWorked"){
        if(query.length === 0){
            query += "sortBy=DateWorked";
        }else{
            query += "&sortBy=DateWorked";
        }
    }

    if(filter === "HoursWorked"){
        if(query.length === 0){
            query += "sortBy=HoursWorked";
        }else{
            query += "&sortBy=HoursWorked";
        }
    }

    if(filter === "Description"){
        if(query.length === 0){
            query += "sortBy=Description";
        }else{
            query += "&sortBy=Description";
        }
    }

    gQuery = query;
    /*if(query.length > 0){
        return query;
    }else{
        return null;
    }*/
    console.log("query in sortQuery at the end:" + query);
}

function orderQuery(order){
    var query = ""; 
    query = gQuery;   
    if(order === "asc"){
        if(query.length === 0){
            query += "orderBy=asc";
        }else{
            query += "&orderBy=asc";
        }
    }

    if(order === "desc"){
        if(query.length === 0){
            query += "orderBy=desc";
        }else{
            query += "&orderBy=desc";
        }
    }

    gQuery = query;
   /* if(query.length > 0){
        return query;
    }else{
        return null;
    }*/
}

/* ========== MAIN END ========== */

/* ========== SIGN IN START ========== */

function btnSignIn_click() {
    var url = "https://cs390-hw5.herokuapp.com/";
	try{
        var httpRequest = new XMLHttpRequest();
        httpRequest.onreadystatechange = httpStateChange;
        httpRequest.onerror = httpError;
        httpRequest.open("POST", url + "auth/login" , true);
        httpRequest.setRequestHeader("Content-type", "application/json");
        httpRequest.send(JSON.stringify({ employeeID: txtSignInEmployeeID.value, password: txtPassword.value }));
        console.log("Sign in clicked");

    }catch(error){
		alert("Error");
    }
    
	function httpError(){
		alert("Network error!");
	}

	function httpStateChange() {
        console.log("httpStateChange");
		if(httpRequest.readyState === 4){
			if(httpRequest.status === 200) {            
                console.log(httpRequest.responseText);
                // Save the Sign In state for the next time someone signs in.
                localStorageSet("Remember", txtCheckBox.checked);                
                if (txtCheckBox.checked === true) {
                    localStorageSet("EmployeeID", txtSignInEmployeeID.value);
                } else {
                    localStorageSet("EmployeeID", "");
                }
                // Save the signed in employee in session state so other pages know someone is signed in.
                sessionStorageSet("CurrentEmployeeID", txtSignInEmployeeID.value);
                gCurrentEmployeeID = sessionStorageGet("CurrentEmployeeID", null);

                // Navigate to the main page.
                gModal.style.display = "none";
                displayMain();
            } else {
                alertModal("EmployeeID and/or Password is incorrect", gModal);
                return;
            }
		}
    }     
}
/* ========== SIGN IN END ========== */




/* ========== SHARED START ========== */

    // Init the UI.
function displayMain(){ 
    btnRefresh.onclick = btnRefresh_onclick;                
        getEntries();      
}

function displayModal(modal){
    gModal = document.getElementById(modal);
        if(modal === "signInModal"){
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
                    gModal.style.display = "none";
                }
            }
        }

        span.onclick = function(){
            if(modal === "editModal"){
                gModal.style.display = "none";
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


function getEntries() {
        gEntries = new Array();      

            var url = "https://cs390-hw5.herokuapp.com/";
            try {
                var httpRequest = new XMLHttpRequest();
                httpRequest.onreadystatechange = httpStateChange;
                httpRequest.onerror = httpError;
                var queryString = "";
                //if no query, get the default entries
                if(gQuery == null){
                    httpRequest.open("GET", url + "timelogs" , true);
                    httpRequest.send(null);
                }else{
                //get the filtered entries
                    queryString = "?" + gQuery;
                    console.log("query in getEntries:" + gQuery);
                    httpRequest.open("GET", url + "timelogs" + queryString, true);
                    httpRequest.send(null);
                }                  
                
            } catch(error){
                alert("Error");
            }
            
            function httpError(){
                alert("Network error!");
            }
        
            function httpStateChange() {
                if(httpRequest.readyState === 4){
                    if(httpRequest.status ===200) {
                        var json = httpRequest.responseText;
                        gEntries = JSON.parse(json);
                        console.log(gEntries);                        
                        displayEntries();
                    }
                }
            }
    }
    
    function saveEntries() {
    
        var json = JSON.stringify(gEntries); //Convert data array to a single string
    
        localStorage.setItem("Entries", json); //Store the string into local storage
    }   
    
    function validDate(dateString) {
    
        var date = new Date(dateString);
    
        if (date === "Invalid Date") {
            return false;
        }
    
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
            var date = gEntry.DateWorked;
            txtDateWorked.value  = date.substring(0,10);
            txtBillable.checked  = gEntry.Billable;
            txtDescription.value = gEntry.Description;          
        }       
    }

    function userConfirmationMessage(message, lastModal, button){
        displayModal("confirmationMessageModal");
        confirmationMessage.innerHTML = message;
       
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


    function employeeHeading_onclick(){
      if(employeeHeading.innerHTML === "Employee \u2193"){
          sortQuery("EmployeeID");
          orderQuery("desc");
          getEntries();
          employeeHeading.innerHTML = "Employee &uarr;";
          dateWorkedHeading.innerHTML = "Date";
          hoursWorkedHeading.innerHTML = "Hours";
          descriptionHeading.innerHTML = "Description";          
      }else{
          sortQuery("EmployeeID");
          orderQuery("asc");
          getEntries();
          employeeHeading.innerHTML = "Employee &darr;";
          dateWorkedHeading.innerHTML = "Date";
          hoursWorkedHeading.innerHTML = "Hours";
          descriptionHeading.innerHTML = "Description";
      }
        
    }

    function dateWorkedHeading_onclick(){
        //when there is no arrow beside the heading word, the arrow will point down and in ascending order
        //when there is an pointing down arrow beside the heading, the arrow will point up and in descending order
        if(dateWorkedHeading.innerHTML === "Date \u2193"){ //2193 unicode for down arrow
            sortQuery("DateWorked");
            orderQuery("desc");
            getEntries();
            employeeHeading.innerHTML = "Employee";
            dateWorkedHeading.innerHTML = "Date &uarr;";
            hoursWorkedHeading.innerHTML = "Hours";
            descriptionHeading.innerHTML = "Description";          
        }else{
            sortQuery("DateWorked");
            orderQuery("asc");
            getEntries();
            employeeHeading.innerHTML = "Employee";
            dateWorkedHeading.innerHTML = "Date &darr;";
            hoursWorkedHeading.innerHTML = "Hours";
            descriptionHeading.innerHTML = "Description";
        }
    }

    function hoursWorkedHeading_onclick(){
        if(hoursWorkedHeading.innerHTML === "Hours \u2193"){ 
            sortQuery("HoursWorked");
            orderQuery("desc");
            getEntries();
            employeeHeading.innerHTML = "Employee";
            dateWorkedHeading.innerHTML = "Date";
            hoursWorkedHeading.innerHTML = "Hours &uarr;";
            descriptionHeading.innerHTML = "Description";          
        }else{
            sortQuery("HoursWorked");
            orderQuery("asc");
            getEntries();
            employeeHeading.innerHTML = "Employee";
            dateWorkedHeading.innerHTML = "Date";
            hoursWorkedHeading.innerHTML = "Hours &darr;";
            descriptionHeading.innerHTML = "Description";
        }
    }

    function descriptionHeading_onclick(){
        if(descriptionHeading.innerHTML === "Description \u2193"){ 
            sortQuery("Description");
            orderQuery("desc");
            getEntries();
            employeeHeading.innerHTML = "Employee";
            dateWorkedHeading.innerHTML = "Date";
            hoursWorkedHeading.innerHTML = "Hours";
            descriptionHeading.innerHTML = "Description &uarr;";          
        }else{
            sortQuery("Description");
            orderQuery("asc");
            getEntries();
            employeeHeading.innerHTML = "Employee";
            dateWorkedHeading.innerHTML = "Date";
            hoursWorkedHeading.innerHTML = "Hours";
            descriptionHeading.innerHTML = "Description &darr;";
        }
    }

   
/* ========== SHARED END ========== */