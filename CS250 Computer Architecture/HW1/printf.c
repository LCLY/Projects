#include <stdio.h>
	
int main(){
	int num1 = 4;
	int num2 = 67;
	char letter = 'y';
	double decimal = 56.72;
	char * word = "CS240 is really fun";
	/*Print "I am about to print the values of variables\n"*/
	printf("I am about to print the values of variables\n");
	/*Print "The value of num1 is %d and the value of num2 is %d\n"*/
	printf("The value of num1 is %d and the value of num2 is %d\n",num1,num2);
	/*Print "The value of letter is %c\n"*/
	printf("The value of letter is %c\n",letter);
	/*Print "The value of decimal is %lf\n"*/
	printf("The value of decimal is %lf\n", decimal);
	/*Print "The value of word is %s\n"*/
	printf("The value of word is %s\n",word);
	/*Print "\"This is a quote\" and '\\' is a slash\n"*/
	printf("\"This is a quote\" and '\\'is a slash\n");
	return 0;
}
