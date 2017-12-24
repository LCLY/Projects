#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>

/*Credit for this task goes to Proj-Dev of cs252 Spring 2015*/

//extern void private_int_array_check(const int* array);
//extern void private_string_array_check(const char* array);
//extern void private_while_loop_check();

/*
*Main function call the different tests
*/
void init_int_array();
void test_string_array();
void test_while_loop();

int main(int argc, char const *argv[])
{
	printf("Starting Tests...\n");
	fflush(stdout);

	init_int_array();

	test_string_array();

	test_while_loop();

	return 0;
}

//This should finish with an array of size 5(arrays index the same as in Java), filled with numbers 1-5.
void init_int_array(){

	int numbers[5];
	int j = 1;

	while(j < 5){
		numbers[j] = j+1;
		j++;
	}

	private_int_array_check(numbers);
}

//This should finish with a character array(string) which says "Welcome!"
void test_string_array(){

	char strings[9] = "Welcome";
	strings[8] = '\0';//This is the null character.  This tells the program where the end of the string is.

	private_string_array_check(strings);

}

//This should finish with the while loop being run 10 times
void test_while_loop(){

	int j=7;

	while(j < 10){
		j++;
		private_while_loop_check(0);
	}
	private_while_loop_check(1);
}