
#include <string.h>
#include <assert.h>
#include <stdlib.h>
#include <stdio.h>

#include "prototypes.h"

/* test case 1 */
void test1(){
    
    int num, result;
    num = 6;
    result = is_pow_of_two(num);
    if (result == 0)
        printf("The number %d is not power of 2\n", num);
    else
        printf("The number %d is a power of 2\n", num);
}

/* test case 2 */
void test2() {

        int num, result;	
        num = 16; 
        result = find_pos(num);    
	if (result == -1)
		printf("The number %d is not power of 2\n", num);
	else
	        printf("The bit position %d of number %d is 1\n", result, num);
}

/* test case 3 */
void test3() {

        int num, result;
        num = 100; 
        result = find_pos(num);    
	if (result == -1)
		printf("The number %d is not power of 2\n", num);
	else
	        printf("The bit position %d of number %d is 1\n", result, num);
}

/* test case 4 */
void test4(){

        int num, result;
        num = 1024; 
        result = find_pos(num);    
	if (result == -1)
		printf("The number %d is not power of 2\n", num);
	else
	        printf("The bit position %d of number %d is 1\n", result, num);
}

/* test case 5 */
void test5(){

        int num, result;
        num = 32767; 
        result = find_pos(num);    
	if (result == -1)
		printf("The number %d is not power of 2\n", num);
	else
	        printf("The bit position %d of number %d is 1\n", result, num);
}

int main(int argc, char ** argv) {

	char * test;
	
	if (argc <2) {
		printf("Usage: test_resizable_table test1|test2|...test7\n");
		exit(1);
	}

	test = argv[1];
	printf("Running %s\n", test);
	if (strcmp(test, "test1")==0) {
		test1();
	}
	else if (strcmp(test, "test2")==0) {
		test2();
	}
	else if (strcmp(test, "test3")==0) {
		test3();
	}
        else if (strcmp(test, "test4")==0) {
		test4();
	}
        else if (strcmp(test, "test5")==0) {
        test5();
    }



	
	else {
		printf("Test not found!!n");
		exit(1);
	}

	return 0;

}
