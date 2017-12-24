function body_onload(){
    //check log in status
    getEmployeeID();
    getLogInStatus();
    if(!loginStatus){
        alert("Sign in required.");
        signInPage(); //go back to sign in page if the user is not logged in
    }

    btnSave.onclick = btnSave_onclick;
    btnDelete.onclick = btnDelete_onclick;
    //set date to default
    setDateDefault();
    //array intialization
    entries = [];
    //get entries from local storage
    getEntries();
    getEdit();
    if(entryEdit){
        document.getElementById("txtEmployeeID").value = entryEdit.ID;
        document.getElementById("txtDateWorked").value = entryEdit.DateWorked;
        document.getElementById("txtHoursWorked").value = entryEdit.HoursWorked;
        document.getElementById("txtDescription").value = entryEdit.Description;
        document.getElementById("txtBillable").value = entryEdit.Billable;
        document.getElementById("btnDelete").disabled = false;
    }else{ 
        //if nothing is being edited, the delete button won't work
        document.getElementById("btnDelete").disabled = true;
    }

}


function btnSave_onclick(){
    //alert if employee ID text box is empty
    if(txtEmployeeID.value.length === ""){
        alert("Employee ID is required.");
        txtEmployeeID.focus();
        return;
    }

    //alert if date worked text box is empty
    if(!validDate(txtDateWorked.value)){
        alert("Date Worked is required.")
        txtDateWorked.focus();
        return;
    }

    //alert if the input is invalid for hours
    if (!tryParse(txtHoursWorked.value, 2)) {
        alert("Hours Worked must be a valid number greater than zero and less than 4.00, and only in fifteen-minute intervals.");
        txtHoursWorked.focus();
        return;
    }

    //alert if description is less than 20 characters
	if (txtDescription.value.length < 20) {
		alert("Description is required and must be at least 20 characters long.");
		txtDescription.focus();
		return;
    }
    

    if(entryEdit != null){
        var i = searchEntry();
        entries[i].ID = txtEmployeeID.value;
        entries[i].DateWorked = txtDateWorked.value;
        entries[i].HoursWorked = txtHoursWorked.value;
        entries[i].Description = txtDescription.value;
        entries[i].Billable = txtBillable.checked;
        setEntries();
        deleteEdit();
    }else{
        //initialize object and values
        var employee = new Object();
        employee.ID = txtEmployeeID.value;
        employee.DateWorked = txtDateWorked.value;
        employee.HoursWorked = txtHoursWorked.value;
        employee.Description = txtDescription.value;
        employee.Billable = txtBillable.checked;
        //if entry is null, create array
        if(entries === null){
            entries = [];
        }
        //add entry into the entries array
        entries.push(employee);
        //reset the values
        document.getElementById("txtHoursWorked").value = "";
        document.getElementById("txtDescription").value = "";
        document.getElementById("txtBillable").checked = false;
        //save the entries
        setEntries();

    }
    //go back to main page
    window.location.href="Main.html";
}

//check if the hours are valid
function tryParse(hrs, decimals) {
    var decimal = false; // flag for decimals
    var decimalPos = 0; // position of decimal point.

    // check if value has only 1 character and it is a symbol.
    if (hrs.length == 1) {
        if (hrs.charCodeAt(i) < 48 || hrs.charCodeAt(i) > 57) {
            return false;
        }
    }

    // check for negative number. 
    if (hrs.charAt(i) === '-') {
        return false;
    } else { // check for positive numbers
        for (var i = 0; i < hrs.length; i++) { // loop through every character in the hrsing.
            if (decimal === true && hrs.charAt(i) === '.') { // check for extra decimal points.
                return false;
            } else if (hrs.charAt(i) === '.') { // check for decimal point.
                decimalPos = i; // set position of decimal point.
                decimal = true; // set flag as true.
                // check if character is from 0-9
            } else if (hrs.charCodeAt(i) < 48 || hrs.charCodeAt(i) > 57) { 
                return false;
            }
        }
    }
    // check if position of decimal point is valid.
    if (decimal) {
        if (hrs.length - parseInt(decimalPos) - 1 > decimals+1) {
            return false;
        }
    }
    
    if (hrs % 0.25 != 0) {
        return false;
    }

    return true;
}

//check if the date is valid
function validDate(dateString) {
    
        var date = new Date(dateString);
    
        if (date === "Invalid Date") {
            return false;
        }
    
        return true;
    }

//set default date to current date
function setDateDefault(){
    document.getElementById("txtDateWorked").valueAsDate = new Date();
}

function searchEntry(){
    for(var i = 0; i < entries.length; i++){
        var employee = new Object();
        employee = entries[i];
        if(JSON.stringify(employee) === JSON.stringify(entryEdit)){
            return i;
        }
    }
}

function btnDelete_onclick(){
    alert("Are you sure you want to delete this entry?");
    var i = searchEntry();
    entries.splice(i,1);
    setEntries();
    deleteEdit();
    window.location.href="Main.html";
}

