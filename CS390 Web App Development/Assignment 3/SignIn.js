//harded coded employee IDs and passwords.
var employeeIDArray = ["Barry", "Caitlin", "Cisco", "Wally", "Iris"];
var passwordArray = ["Allen", "Snow", "Ramon", "West", "West"];

function body_onload() {
    loginStatus=false;//to prevent it from going back to main page
    setLoginStatus();
    //go to onlick function when button is clicked.
    btnSignIn.onclick = btnSignIn_onclick;
    getSavedCheckedEmployeeID();
    if (employeeID != null) {
        txtEmployeeID.value = employeeID;
        setEmployeeID();
        document.getElementById("txtPassword").focus();
        document.getElementById("txtCheckBox").checked = true;
    }
}

//sign in 
function btnSignIn_onclick() {
    if (txtEmployeeID.value.length <= 0) {
        alert("Employee ID is required.");
        txtEmployeeID.focus();
    } else if (txtPassword.value.length <= 0) {
        alert("Password is required.");
        txtPassword.focus();
    } else if (checkValidIDPassword(txtEmployeeID.value, txtPassword.value)){
        saveEmployeeID(); //in Shared.js, save employee id in local storage
        loginStatus = true;
        setLoginStatus(); // in Shared.js, set the login status
        //if checkbox is checked, save the ID
        if (document.getElementById("txtCheckBox").checked) {
            saveCheckedEmployeeID(); //save the checked employee ID in local Storage
            //document.getElementById("txtEmployeeID").value = "";
            document.getElementById("txtPassword").value = "";
        } else {
            document.getElementById("txtEmployeeID").value = "";
            document.getElementById("txtPassword").value = "";
            employeeID = "";
            document.getElementById("txtCheckBox").checked = false;
            deleteSavedCheckedEmployeeID(); //delete saved and checked employee ID in local storage
        }
       
        window.location.href = "Main.html";
    } else {
        alert("You have entered an invalid Employee ID or Password.");
    }
}

//function to check if the ID or password is valid by matching them with the hardcoded lists.
function checkValidIDPassword(id, password) {
    for (var i = 0; i < employeeIDArray.length; i++) {
        if (employeeIDArray[i] === id && passwordArray[i] === password) {
            return true;
        }
    }
}

