//global var
var filteredEmployees;
var filterOptions;

function body_onload() {
    //get logged in sawfatatus
    getLogInStatus();
    //if user is not log in, return to the sign in page
    if (loginStatus != true) {
        alert("Sign in required.");            
        signInPage();//go to sign in page       
    }
    btnRefresh.onclick = btnRefresh_onclick;
    btnAddNew.onclick = btnAddNew_onclick;
    entries = [];
    filteredEmployees = [];
    //get the entries from the local storage
    getEntries();
    getFilter();
    //if there are entries list them out
    if (entries != null) {
        displayEntries(entries);
    }
    //if there are filters, go to previous state and update the list
    if (filterOptions != null) {
        document.getElementById("txtDescriptionContains").value = filterOptions.Description;
        document.getElementById("txtDateFrom").value = filterOptions.DateFrom;
        document.getElementById("txtDateThrough").value = filterOptions.DateThrough;
        document.getElementById("currentEmployeeCheckBox").checked = filterOptions.ShowCurrentEmployee;
        btnRefresh_onclick();
    }

}

//refresh the list
function btnRefresh_onclick() {
    //reset the filter
    filteredEmployees = [];
    //if there is description
    if (txtDescriptionContains.value) {
        descriptionFilter(txtDescriptionContains.value);
        displayEntries(filteredEmployees);
    }
    //dateFrom set, dateThrough not set
    if (txtDateFrom.value && !txtDateThrough.value) {
        dateFromFilter(txtDateFrom.value);
        displayEntries(filteredEmployees);
    }
    //dateFrom not set, dateThrough set
    if (txtDateThrough.value && !txtDateFrom.value) {
        dateThroughFilter(txtDateThrough.value);
        displayEntries(filteredEmployees);
    }
    //both dateFrom and dateThrough set
    if (txtDateThrough.value && txtDateFrom.value) {
        if (txtDateFrom.value > txtDateThrough.value) {
            alert("Invalid date, the date from should not be later than date through.");
            document.getElementById("txtDateFrom").focus();
        } else {
            dateFromAndThroughFilter(txtDateFrom.value, txtDateThrough.value);
            displayEntries(filteredEmployees);
        }
    }

    //if checkbox is checked
    if (currentEmployeeCheckBox.checked) {
        getEmployeeID();
        currentEmployeeFilter();
        displayEntries(filteredEmployees);
    } 

    //if there are no descriptions
    if (txtDescriptionContains.value.length <= 0 && !txtDateFrom.value &&
        !txtDateThrough.value && !currentEmployeeCheckBox.checked) {
        displayEntries(entries);
    }

}

//add new entries with filters
function btnAddNew_onclick() {
    saveFilterOptions();
    window.location.href = "Edit.html";
}

function displayEntries(entriesArray) {
    tableLogEntriesData.innerHTML = "";
    var totalHours = 0;
    //traverse through the array of entries and create HTML components and CSS styles.
    for (var i = 0; i < entriesArray.length; i++) {
        var employee = entriesArray[i];
        var row = document.createElement("div");
        var col1 = document.createElement("div");
        var col2 = document.createElement("div");
        var col3 = document.createElement("div");
        var col4 = document.createElement("div");      

        row.className = "divEmployeeRow";
        col1.className = "divEmployeeID";
        col2.className = "divDateWorked";
        col3.className = "divHoursWorked";
        col4.className = "divDescription";
     
        col1.innerHTML = employee.ID;
        col2.innerHTML = employee.DateWorked;
        col3.innerHTML = parseFloat(employee.HoursWorked).toFixed(2);
        col4.innerHTML = employee.Description;
        
        //summing up the total hours worked
        totalHours += +parseFloat(employee.HoursWorked).toFixed(2);
        txtTotalHoursWorked.value = parseFloat(totalHours).toFixed(2);

        row.ondblclick = edit_onclick;
        row.employee = entriesArray[i];
        row.style.cursor = "pointer";

        row.appendChild(col1);
        row.appendChild(col2);
        row.appendChild(col3);
        row.appendChild(col4);      
        tableLogEntriesData.appendChild(row);
    }
}

//saves the filter options in local storage
function saveFilterOptions() {
    filterOptions = new Object();
    filterOptions.Description = txtDescriptionContains.value;
    filterOptions.DateFrom = txtDateFrom.value;
    filterOptions.DateThrough = txtDateThrough.value;
    filterOptions.ShowCurrentEmployee = currentEmployeeCheckBox.checked;

    var jsonFilter = JSON.stringify(filterOptions);
    sessionStorage.setItem("FilterOptions", jsonFilter);
}

//get the filters
function getFilter() {
    var jsonFilter = sessionStorage.getItem("FilterOptions");
    filterOptions = JSON.parse(jsonFilter);
}

//saves filters then go to edit page
function edit_onclick() {
    entryEdit = this.employee;
    saveFilterOptions();
    setEdit();
    window.location.href = "Edit.html";
}

//filter list to only show entries with given description
function descriptionFilter(search) {
    for (var i = 0; i < entries.length; i++) {
        var employee = entries[i];
        //make it case insensitive
        var caseSearch = search.toUpperCase();
        var caseDescription = employee.Description.toUpperCase();
        //if the search word exists in the description, if the word doesnt exist it will return -1
        if (caseDescription.indexOf(caseSearch) >= 0) {
            filteredEmployees.push(employee);
        }
    }
}

//filter list using from date
function dateFromFilter(dateFrom) {  
    if (filteredEmployees.length === 0) { 
        for (var i = 0; i < entries.length; i++) {
            var employee = entries[i];
            if (employee.DateWorked >= dateFrom) {
                filteredEmployees.push(employee);
            }
        }
    } else {
         //filter from array if exist
        for (var i = 0; i < filteredEmployees.length; i++) {
            var employee = filteredEmployees[i];
            // checks for entries, removes entries from the array recursively.
            if (employee.DateWorked < dateFrom) {
                filteredEmployees.splice(i, 1);
                dateFromFilter(dateFrom);
            }
        }

    }
}

//filter with only through
function dateThroughFilter(dateThrough) {
    if (filteredEmployees.length === 0) {
        for (var i = 0; i < entries.length; i++) {
            var employee = entries[i];
            if (employee.DateWorked <= dateThrough) {
                filteredEmployees.push(employee);
            }
        }
    } else {
        //filter from array if exist
        for (var i = 0; i < filteredEmployees.length; i++) {
            var employee = filteredEmployees[i];
            // checks for entries, removes entries from the filtered array recursively.
            if (employee.DateWorked >= dateThrough) {
                filteredEmployees.splice(i, 1);
                dateThroughFilter(dateThrough);
            }
        }

    }
}

//filter with date from and through
function dateFromAndThroughFilter(dateFrom, dateThrough) {    
    if (filteredEmployees.length === 0) {
        for (var i = 0; i < entries.length; i++) {
            var employee = entries[i];

            if (employee.DateWorked >= dateFrom && employee.DateWorked <= dateThrough) {
                filteredEmployees.push(employee);
            }
        }
    } else { // filter from array if existtrue
        for (var i = 0; i < filteredEmployees.length; i++) {
            var employee = filteredEmployees[i];
            // checks for entries, removes entries from the filtered array recursively.
            if (employee.dateWorked < dateFrom || employee.dateWorked > dateThrough) {
                if (filteredEmployees.length == 1) {//one filter left, reset it
                    filteredEmployees = [];
                } else {
                    filteredEmployees.splice(i, 1);
                    filterDateFromAndThrough(dateFrom, dateThrough);
                }
            }
        }
    }
}

//to filter the current employees
function currentEmployeeFilter() {
    if (filteredEmployees.length === 0) {
        for (var i = 0; i < entries.length; i++) {
            var employee = entries[i];

            if (employee.ID === employeeID) {
                filteredEmployees.push(employee);
            }
        }
    } else { // filters from array if exist.
        for (var i = 0; i < filteredEmployees.length; i++) {
            var employee = filteredEmployees[i];
            // checks for entries, removes entries from the filtered array recursively.
            if (employee.ID != employeeID) {
                filteredEmployees.splice(i, 1);
                currentEmployeeFilter();
            }
        }
    }
}