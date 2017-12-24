/*
 * This is a program which takes an integer either positive or negative as input. 
 * It will convert that integer from type int to type char. Say number -23 is
 * converted to "-23".
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>
char* itoa(int num, char result[]);


int main(int argc, char** argv) {
	if (argc < 2) {
		printf("Usage: itoa <number>\n");
		exit(1);
	}
	// after atoi, num would be a valid int within range
	int num = atoi(argv[1]);
	char result[50];
	printf("You are going to convert int %d\n", num);
	itoa(num, result);
	printf("The converted string version of %d is '%s'\n", num, result);
	return 0;
}

char* itoa(int num, char result[]){
	/*
	 *	TODO: Write the code to implement itoa. Convert number from int type to
	 *	char type. (Hint: mind the overflow case.)
	 *
	 *
	 */
	if(num >= INT_MIN && num <= INT_MAX){
		int negativeNum = 0;
		int charNum = 0;
		int lowerLimit = 0;

		if(num<0){
			if(num ==  INT_MIN){
				num = -1* (num+1);
				negativeNum = 1;//if it is a negative number the integer is equal to one
				lowerLimit =1;
				charNum++;
			}else{
				num = -1* num;
				negativeNum = 1;
				charNum++;
			}
		}
	int temp = num;
	do{
		charNum++;
		temp = temp/10;

	}while(temp);

	if(negativeNum == 1){
		result[0] = '-';
	}
	int x = charNum - 1;

	do{
		result[x--] = num % 10 + '0';
		num = num / 10;
	
	}while(num);

	if(lowerLimit == 1 ){
		int y = charNum - 1;
		result[y] = '8';
	}

}	
	return result;
}
