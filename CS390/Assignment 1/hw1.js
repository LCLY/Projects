function body_onload() {
    btnTipPreTax.onclick = displayTipPreTax;
    btnTotalTip.onclick = displayTotalTip;
}

function displayTipPreTax() {
    var amount;
    var tipPercent;
    var tip;
    var salesTaxPercent;
    var base;
    var salesTax;

    if (tryParse(txtCheckAmount.value, 2) && tryParse(txtTipPercentage.value, 2)
        && tryParse(txtSalesTaxPercentage.value, 2)) {
        amount = parseFloat(txtCheckAmount.value);
        tipPercent = parseFloat(txtTipPercentage.value);
        salesTaxPercent = parseFloat(txtSalesTaxPercentage.value);

        base = amount * 100 / (100 + salesTaxPercent);
        if (amount >= 0 && tipPercent >= 0 && salesTaxPercent >= 0) {
            salesTax = base * (salesTaxPercent / 100);
            txtSalesTax.value = salesTax.toFixed(2);
            tip = base * tipPercent / 100;
            txtTipAmount.value = tip.toFixed(2);
        } else {
            alert("Input number has to be at least zero.");
        }
    } else {
        displayErrorMessage();
    }       
}

function displayTotalTip() {
    var amount;
    var tipPercent;
    var totalTip;
    var salesTaxPercent;

    if (tryParse(txtCheckAmount.value, 2) && tryParse(txtTipPercentage.value, 2)
        && tryParse(txtSalesTaxPercentage.value, 2)) {
        amount = parseFloat(txtCheckAmount.value);
        tipPercent = parseFloat(txtTipPercentage.value);
        salesTaxPercent = parseFloat(txtSalesTaxPercentage.value);
        if (amount >= 0 && tipPercent >= 0 && salesTaxPercent >= 0) {           
            totalTip = amount * tipPercent / 100;
            txtTipAmount.value = totalTip.toFixed(2);
        } else {
            alert("Input number has to be at least zero");
        }
    } else {
        displayErrorMessage();
    }
    
}

function displayErrorMessage() {
    if (!tryParse(txtCheckAmount.value, 2)) {
        alert("Check Amount must only contain numbers with at most 2 decimal places.");
        document.getElementById("txtCheckAmount").focus();
    } else if (!tryParse(txtSalesTaxPercentage.value, 2)) {
        alert("Sales Tax % must only contain numbers with at most 2 decimal places.");
        document.getElementById("txtSalesTaxPercentage").focus();
    } else if (!tryParse(txtTipPercentage.value, 2)) {
        alert("Tip % must only numbers contain with at most 2 decimal places.");
        document.getElementById("txtTipPercentage").focus();
    }
}

function tryParse(str, num) { 
    var decimalNum = false;
    var pos = 0;
    if(str.charAt(0) === '-') { // if its a negative number
        for (var i = 1; i < str.length; i++) {
            if (str.charAt(i) === '-') { //if in between the number there is a -
                return false;
            } else if (str.charAt(i) === '.') {
                decimalNum = true;
                pos = i;               
            } else if (decimalNum === true && str.charAt(i) === '.') {//if there is more than one .
                return false;
            } else if (str.charCodeAt(i) < 48 || str.charCodeAt(i) > 57) { //using ASCII
                return false;
            }
        }

    } else { //positive number
        for (var i = 0; i < str.length; i++) {
            if (str.charAt(i) === '-') {
                return false;
            } else if (str.charAt(i) === '.') {
                decimalNum = true;
                pos = i; 
                if (decimalNum === true) {
                    if (str.length - parseInt(pos) - 1 > num) {//finding the decimal places
                        return false;
                    }
                }
            } else if (decimalNum === true && str.charAt(i) === '.') {
                return false;
            } else if (str.charCodeAt(i) < 48 || str.charCodeAt(i) > 57) {
                return false;
            }
        }
    }
        
    return true;
}
