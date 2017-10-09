//a global scope variable
var gEmployee; 

//function that runs when body is loaded
function body_onload() {
    //run onclick function when button is clicked
    btnSave.onclick = btnSave_onclick;
    //function to set the date default to current date
    setDateDefault();
    //initialize Array
    gEmployee = new Array();
    //retrieve the entries from local storage
    getEmployee();

    //if there are any entry, list them in the list
    if(gEmployee != null){
        displayEmployee();
        document.getElementById("txtEmployeeID").value = gEmployee[gEmployee.length - 1].ID;
    }
}
//save button function
function btnSave_onclick() {
    //initialize object
    var employee = new Object();
    //initialize and set values into object
    employee.ID = txtEmployeeID.value;   
    employee.HoursWorked = txtHoursWorked.value;
    employee.DateWorked = txtDateWorked.value;
    employee.Description = txtDescription.value;

    //check if the fields are valid
    if(checkEmployeeID(employee.ID) && checkDateWorked(employee.DateWorked) && 
    checkHoursWorked(employee.HoursWorked) && checkDescription(employee.Description)){

        //if entry is null, create an array
        if(gEmployee == null){
            gEmployee = [];
        }
        //push element at the end of array
        gEmployee.push(employee);
       //reset values after clicking save
        document.getElementById("txtHoursWorked").value= "";
        document.getElementById("txtDescription").value= "";
        document.getElementById("txtBillable").checked= false;
        //save array of entries into local storage
        saveEmployee();
        //display all the entries in the list
        displayEmployee();
    }         
    
}

//function to save the array of entries into local storage
function saveEmployee() {
    //convert array of entries into json strings
    var jsonEmployee = JSON.stringify(gEmployee);
    //setting the key and value
    localStorage.setItem("Employee", jsonEmployee);
}

//retrieving the array of entries from local storage
function getEmployee() {
    //retrieve the value with the key
    var jsonEmployee = localStorage.getItem("Employee");
    if (jsonEmployee == null) {
        return;
    }
    //convert json strings into array of entries
    gEmployee = JSON.parse(jsonEmployee);
}

//display all the entries in the list
function displayEmployee() {
    tableLogEntriesData.innerHTML = "";
    var totalHours = 0;
    //traverse through the array of entries and create HTML components and CSS styles.
    for (var i = 0; i < gEmployee.length; i++) {
        var employee = gEmployee[i];
        var row = document.createElement("div");
        var col1 = document.createElement("div");
        var col2 = document.createElement("div");
        var col3 = document.createElement("div");
        var col4 = document.createElement("div");
        var col5 = document.createElement("div");

        row.className = "divEmployeeRow";
        col1.className = "divEmployeeID";
        col2.className = "divDateWorked";
        col3.className = "divHoursWorked";
        col4.className = "divDescription";
        col5.className = "divDelete";

        col1.innerHTML = employee.ID;
        col2.innerHTML = employee.DateWorked;
        col3.innerHTML = parseFloat(employee.HoursWorked).toFixed(2);
        col4.innerHTML = employee.Description;
        col5.innerHTML = "Delete";

        //summing up the total hours worked
        totalHours += +parseFloat(employee.HoursWorked).toFixed(2);
        txtTotalHoursWorked.value = parseFloat(totalHours);

        //run click function when the button is clicked
        col5.onclick = col5_onclick;
        col5.index = i;
        //change the pointer when its hovered
        col5.style.cursor = "pointer";

        row.appendChild(col1);
        row.appendChild(col2);
        row.appendChild(col3);
        row.appendChild(col4);
        row.appendChild(col5);
        tableLogEntriesData.appendChild(row);
    }
}

//deletes entry when the Delete button is clicked
function col5_onclick(){
    if(confirm("Are you sure you want to delete this entry?") == true ){
        gEmployee.splice(this.index, 1);
        saveEmployee();
        displayEmployee();
    }
}

//Check if ID is valid
function checkEmployeeID(str){
    if (str === "" || str.length === 0 || str === null) {
        alert("Employee ID cannot be empty.");
        document.getElementById("txtEmployeeID").focus();
    }else{
        return true;
    }
}

//check if date worked is valid
function checkDateWorked(str) {
    if (!str) {
        alert("You must select or insert a valid date.");
        document.getElementById("txtDateWorked").focus();
    }else{
        return true;
    }
}
//check if hours worked is valid
function checkHoursWorked(hours) {
    /*var nstring = "0." + num.substring(2);
    var integer = parseFloat(nstring);
        integer = integer * 60;
    if (integer % 15 != 0) {
        alert("Hours Worked is invalid, it must be in fifteen-minute intervals. E.g. 1.50, 2.75, 3.25 or 1.00.")
        document.getElementById("txtHoursWorked").focus();
    }*/  


    if (hours <= 0 || hours > 4) {
        alert("Hours Worked must be in between 0 and 4.00.");
        document.getElementById("txtHoursWorked").focus();
    }else if(!tryParse(hours, 2)){
        alert("Hours Worked is invalid, it must be in fifteen-minute intervals. E.g. 1.50, 2.75, 3.25 or 1.00.");
        document.getElementById("txtHoursWorked").focus();
    }else{
        return true;
    }

  
}

//check if description is valid
function checkDescription(str) {
    var length = str.length;
    if (length < 20) {
        alert("Your description must contain at least 20 characters.");
        document.getElementById("txtDescription").focus();
    }else{
        return true;
    }
}

//function to set the default date to current date
function setDateDefault() {
    document.getElementById("txtDateWorked").valueAsDate = new Date();
}

function tryParse(str, decimals) {
	var decimal = false; // flag for decimals
	var decimalPos = 0; // position of decimal point.

	// check if value has only 1 character and it is a symbol.
	if (str.length == 1) {
		if (str.charCodeAt(i) < 48 || str.charCodeAt(i) > 57) {
			return false;
		}
	}

	// check for negative number. 
	if (str.charAt(i) === '-') {
		return false;
	} else { // check for positive numbers
		for (var i = 0; i < str.length; i++) { // loop through every character in the string.
		    if (decimal === true && str.charAt(i) === '.') { // check for extra decimal points.
				return false;
			} else if (str.charAt(i) === '.') { // check for decimal point.
				decimalPos = i; // set position of decimal point.
				decimal = true; // set flag as true.
				// check if character is from 0-9
			} else if (str.charCodeAt(i) < 48 || str.charCodeAt(i) > 57) { 
				return false;
			}
		}
	}
	// check if position of decimal point is valid.
	if (decimal) {
		if (str.length - parseInt(decimalPos) - 1 > decimals+1) {
			return false;
		}
    }
    
    if (str % 0.25 != 0) {
        return false;
    }

	return true;
}






