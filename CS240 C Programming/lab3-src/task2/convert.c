/*
 * This is a convert program that takes a base and a POSITIVE number in that base
 * as arguments. It converts the number to decimal and also converts the resulted
 * decimal back to the original base number.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "convert.h"

char baseChars[25];

int main(int argc, char** argv){
    int base, decimal_num;
    if (argc < 3) {
        printf("Usage: convert <base> <number-to-convert>\n");
        exit(1);
    }

    base = atoi(argv[1]);

    /*
     * First we need to check whether the input number is valid
     * Say if base is 2 and input number is 35, which is not a binary
     */
    validateInput(base, argv[2]);

    // Convert the base number to decimal
    decimal_num = convertToDecimal(base, argv[2]);
    printf("Converted to decimal: %d\n", decimal_num);

    // Convert the decimal number back to the base number
    convertToBase(decimal_num, base);
	return 0;
}

/*
 * Check if the user inputs are valid
 */
void validateInput(int base, char num[]){
    printf("Number read in base %d: %s\n", base, num);

    // First check whether base is valid
    if (base < 2 || base > 25){
        printf("Invalid base!!\n");
        exit(1);
    }

    // Check if number is positive and if it has valid length
    if (strlen(num) > 32 || num[0] == '-'){
        printf("Invalid input number!!\n");
        exit(1);
    }

    // Initialize our base char array for later use
    setBaseChars();

    /* TODO: Check if the input number matches the base
     *       Ex: 16 as input number of base 2 is invalid because
     *           16 is not a valid binary number
     */

}

/*
 *  Make baseChars filled with all the chars used in different bases
 */
void setBaseChars() {
    char m = '0';
    int i = 0;
    for (i = 0; i < 10; i++){
        baseChars[i] = m;
        m++;
    }

    m = 'A';
    for (i = 10; i < 25; i++){
        baseChars[i] = m;
        m++;
    }
}

/*
 * Convert number in given base to decimal
 */
int convertToDecimal(int base, char num[]){
    /*
	 *	TODO: Write the code to convert input number to decimal(Hint: use given
	 *	function my_pow)
	 */

	return 0;
}

/*
 * Convert decimal back to the given base number
 */
void convertToBase(int num, int base){
	/*
	 *	TODO: Write the code to convert decimal back to the input number
	 *
	 */
}

/*
 * Return result of power calculation in math
 */
int my_pow(int base, int n){
    int s = 1;
    int i;
    for (i = 0; i < n; i++){
        s *= base;
    }
    return s;
}
