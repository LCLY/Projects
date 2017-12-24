//global var
var employeeID;
var loginStatus;
var entries;
var entryEdit;

//saves the login status in local storage
function setLoginStatus() {
    var jsonLogin = JSON.stringify(loginStatus);
    sessionStorage.setItem("LOGIN_STATUS", jsonLogin);
}

//get the login status in local storage
function getLogInStatus() {
    var jsonLogin = sessionStorage.getItem("LOGIN_STATUS");
    loginStatus = JSON.parse(jsonLogin);
}

//save employee ID in session storage
function saveEmployeeID() {
    var jsonEmployeeID = JSON.stringify(txtEmployeeID.value);
    employeeID = JSON.parse(jsonEmployeeID);
    sessionStorage.setItem("ID", jsonEmployeeID);
}

//retrieve employee ID from session storage
function getEmployeeID() {
    var jsonEmployeeID = sessionStorage.getItem("ID");
    employeeID = JSON.parse(jsonEmployeeID);
}

//save the checked employee ID
function saveCheckedEmployeeID() {
    var jsonCheckedID = JSON.stringify(txtEmployeeID.value);
    localStorage.setItem("CheckedID", jsonCheckedID);
}

//retrieve the saved and checked employee ID
function getSavedCheckedEmployeeID() {entries
    var jsonCheckedID = localStorage.getItem("CheckedID");
    employeeID = JSON.parse(jsonCheckedID);
}

//delete the saved and checked employee ID
function deleteSavedCheckedEmployeeID() {
    localStorage.removeItem("CheckedID");
}

//go to the sign in page
function signInPage() {
    window.location.href = "SignIn.html";
}

//saves entries in local storage
function setEntries(){
    var jsonEntries = JSON.stringify(entries);
    localStorage.setItem("Entries", jsonEntries);

}

//retrieve entries from local storage
function getEntries(){
    var jsonEntries = localStorage.getItem("Entries");
    entries = JSON.parse(jsonEntries);
}


//save edit entry in local storage
function setEdit(){
    var jsonEdit = JSON.stringify(entryEdit);
    localStorage.setItem("Edit Entry", jsonEdit);
}

//retrieve edit entry from local storage
function getEdit() {
    var jsonEdit = localStorage.getItem("Edit Entry");
    entryEdit = JSON.parse(jsonEdit);
}

//remove selected edit entry from local storage
function deleteEdit() {
    localStorage.removeItem("Edit Entry");
}