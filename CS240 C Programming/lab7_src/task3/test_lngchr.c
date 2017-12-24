
#include <assert.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "lngchr.h"

/* test case 1 */
void test1(){
	long l = 255;
	char c = 255;
	char * p = lngchr(&l, c);
	char * a = memchr(&l, c, 8);
	if(p == a)
		printf("lngchr found the character at the proper place\n");
	else
		printf("lngchr found the character at the improper place reurned %lx should have returned %lx\n", (long)p, (long)a);
}

/* test case 2 */
void test2() {
	long l = 255;
	char c = 1;
	char * p = lngchr(&l, c);
	char * a = memchr(&l, c, 8);
	if(p == a)
		printf("lngchr found the character at the proper place\n");
	else
		printf("lngchr found the character at the improper place reurned %lx should have returned %lx\n", (long)p, (long)a);
}

void test3() {
	long l = 16711680;
	char c = 255;
	char * p = lngchr(&l, c);
	char * a = memchr(&l, c, 8);
	if(p == a)
		printf("lngchr found the character at the proper place\n");
	else
		printf("lngchr found the character at the improper place reurned %lx should have returned %lx\n", (long)p, (long)a);
}

int main(int argc, char ** argv) {

	char * test;

	if (argc <2) {
		printf("Usage: test_resizable_table test1|test2|...test3\n");
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
	else {
		printf("Test not found!!n");
		exit(1);
	}

	return 0;

}
