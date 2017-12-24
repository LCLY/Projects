
#include <assert.h>
#include <stdlib.h>
#include <stdio.h>
#include "memcmp.h"


/* test case 1 */
void test1(){

	const char * s1 = "computer science";
	const char * s2 = "computer architecture";
    int num = 9;
	if(memcmp(s1, s2, num))
		printf("The first %d bytes of \"%s\" doesn't equal \"%s\"\n", num, s1, s2);
	else
		printf("The first %d bytes of \"%s\" equal \"%s\"\n", num, s1, s2);
}

/* test case 2 */
void test2() {
	char s1[3] = {'\000', '\201', '\234'};
	char s2[3] = {'\000', '\200', '\234'};
    int num = 3;
	if(memcmp(s1, s2, num))
		printf("The first %d bytes of data1 doesn't equal data2\n", num);
	else
		printf("The first %d bytes of data1 equal data2\n", num);
}

void test3() {
	long l1 = 0b11101000000;
	long l2 = 0b00011111000;
    int num = 1;
	if(memcmp(&l1, &l2, num) < 0)
		printf("The first %d bytes of data1 are less than data2\n", num);
	else
		printf("The first %d bytes of data1 are greater than or equal to data2\n", num);
}

void test4() {
	const long l1 = 0b00000011000;
	const long l2 = 0b00000001000;
    int num = 2;
	if(memcmp(&l1, &l2, num) > 0)
		printf("The first %d bytes of data1 are greater than data2\n", num);
	else
		printf("The first %d bytes of data1 are less than or equal to data2\n", num);
}

void test5() {
	const long l1 = 0b00000011000;
	const long l2 = 0b00000001000;
    int num = 0;
	if(memcmp(&l1, &l2, num))
		printf("Zero length bit strings aren't equal\n");
	else
		printf("Zero length bit strings are equal\n");
}

int main(int argc, char ** argv) {

	char * test;

	if (argc <2) {
		printf("Usage: test_resizable_table test1|test2|...test5\n");
		exit(1);
	}

	test = argv[1];
	printf("Running %s\n", test);
	if (test[4]=='1' ) {
		test1();
	}
	else if (test[4]=='2' ) {
		test2();
	}
	else if (test[4]=='3' ) {
		test3();
	}
	else if (test[4]=='4' ) {
		test4();
	}
	else if (test[4]=='5' ) {
		test5();
	}
	else {
		printf("Test not found!!n");
		exit(1);
	}

	return 0;

}
