// Global variables.
var gSalesTaxAmount;
var gTipAmount;
function body_onload() {

    new Vue ({
        el:"#calculatorApp",
        data:{
            checkAmount:"",
            salesTaxPct:"7.0",
            tipPct:"18.00",            
            result:"TipPreTax"
        },

        computed:{
            op1Message: function(){
                if (tryParse(this.checkAmount, 2) === false) {              
                    return "Check Amount is required and must be a valid number > zero.";
                }
                if (parseFloat(this.checkAmount) <= 0) {              
                    return "Check Amount is required and must be a valid number > zero.";
                }
            },
    
            op2Message: function(){
                if (tryParse(this.salesTaxPct, 2) === false) {              
                    return "Sales Tax Percent is required and must be a valid number > zero.";
                }
                if (parseFloat(this.salesTaxPct) <= 0) {              
                    return "Sales Tax Percent is required and must be a valid number > zero.";
                }
            },
    
            op3Message: function(){
                if (tryParse(this.tipPct, 2) === false) {              
                    return "Tip Percent is required and must be a valid number > zero.";
                }
                if (parseFloat(this.tipPct) <= 0) {              
                    return "Tip Percent is required and must be a valid number > zero.";
                }
            },

            salesTaxAmount: function(){
                if (tryParse(this.checkAmount, 2) === false) {              
                    return "";
                }
                if (parseFloat(this.checkAmount) <= 0) {  
                    return "";
                }
                if (tryParse(this.salesTaxPct, 2) === false) {              
                    return "";
                }
                if (tryParse(this.salesTaxPct, 2) <= 0) {              
                    return "";
                }
                if (tryParse(this.tipPct, 2) === false) {              
                    return "";
                }
                if (tryParse(this.tipPct, 2) <= 0) {              
                    return "";
                }
                if(this.result === "TipPreTax"){
                    gSalesTaxAmount =  parseFloat(this.checkAmount) - (parseFloat(this.checkAmount) / (1 + (parseFloat(this.salesTaxPct) / 100)));
                    return gSalesTaxAmount.toFixed(2);
                }else{
                    return gSalesTaxAmount.toFixed(2);
                }
               
                        
            },

            tipAmount: function(){
                if (tryParse(this.checkAmount, 2) === false) {              
                    return "";
                }
                if (parseFloat(this.checkAmount) <= 0) {  
                    return "";
                }
                if (tryParse(this.salesTaxPct, 2) === false) {              
                    return "";
                }
                if (tryParse(this.salesTaxPct, 2) <= 0) {              
                    return "";
                }
                if (tryParse(this.tipPct, 2) === false) {              
                    return "";
                }
                if (tryParse(this.tipPct, 2) <= 0) {              
                    return "";
                }
                if(this.result === "TipPreTax"){
                    gTipAmount = (parseFloat(this.checkAmount) - gSalesTaxAmount) * (parseFloat(this.tipPct) / 100);
                    return gTipAmount.toFixed(2);
                }else{
                    gTipAmount = parseFloat(this.checkAmount)*(parseFloat(this.tipPct)/100); 
                    return gTipAmount.toFixed(2);
                }
                             
            }          

        }

       
    });


}


// The function returns true if the input string can be converted to a number.
// Rules for the input string are:
//  1) Must not be an empty string.
//  2) Can contain only number characters 0 through 9.
//  3) Contains at most one dot character to indicate the decimal position.
//  4) If a hyphen is present it is the first character in the string (because the JavaScript parseFloat 
//     function ignores a trailing hyphen).
//  5) Does not contain more decimal places indicated by the maxDecimals parameter.

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