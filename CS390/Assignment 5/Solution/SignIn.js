"use strict";

function showSignIn(successCallback) {

    // Show the modal.
    divSignIn.style.display = "flex";
    btnSignIn.onclick = btnSignIn_onclick;

    // Populate the UI with any values we saved.
    txtSignInEmployeeID.value = localStorageGet("EmployeeID", "");
    chkRemember.checked = localStorageGet("Remember", false);  

    if (txtSignInEmployeeID.value === "") {
        txtSignInEmployeeID.focus();
    }
    else {
        txtPassword.focus();
    }

    return;

    function btnSignIn_onclick() {
       var url ="https://cs390-hw5.herokuapp.com/";
       try{
            var httpRequest = new XMLHttpRequest();
            httpRequest.onreadystatechange = httpStateChange;
            httpRequest.onerror = httpError;
            httpRequest.open("POST",url+"auth/login",true); //submit data
            httpRequest.setRequestHeader("Content-type", "application/json");
            httpRequest.send(JSON.stringify({employeeID: txtSignInEmployeeID.value, password: txtPassword.value}));
            console.log("Sign In button clicked");
       }catch(error){
            alert("Error!");
       }

       function httpError(){
           alert("Network Error!");
       }

       function httpStateChange(){
           //console.log("HTTP state");
      
           //determine state of request (0) has not been sent (4) complete and response received
           if(httpRequest.readyState === 4){
               //determine whether the request was successful or not
                if(httpRequest.status === 200){ 
                    console.log(httpRequest.responseText);              
                     // Save the Sign In state for the next time someone signs in.
                    localStorageSet("Remember", chkRemember.checked);
         
                    if (chkRemember.checked === true) {
                        localStorageSet("EmployeeID", txtSignInEmployeeID.value);
                    }
                    else {
                        localStorageSet("EmployeeID", "");
                    } 
                    //close the modal
                    divSignIn.style.display = "none";                                    

                }else{
                    showAlert("EmployeeID and/or Password is incorrect.");
                    return;
                }  
                
            }
       }
        // Save the signed in employee so we can use as a default value when adding an entry.

        //gCurrentEmployeeID = txtSignInEmployeeID.value;

        successCallback();
    }
}
