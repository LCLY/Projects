
#include <assert.h>
#include <stdlib.h>
#include <stdio.h>
#include "memcpy.h"



int bad_memcmp(void * d1, void * d2, size_t n) {
	int i;
	for(i = 0; i < n; i++)
		if(((char *)d1)[i] != ((char *)d2)[i])
			return ((char *)d1)[i] - ((char *)d2)[i];
	return 0;
}

/* test case 1 */
void test1(){

	char * s1 = "computer science";
	char * s2 = (char *) malloc(10 * sizeof(char));
    int num = 10;
	memcpy(s2, s1, num);
	if(bad_memcmp(s1, s2, num))
		printf("The first %d bytes of \"%s\" doesn't equal \"%s\"\n", num, s1, s2);
	else
		printf("The first %d bytes of \"%s\" equal \"%s\"\n", num, s1, s2);
}

/* test case 2 */
void test2() {
	char s1[3] = {'\000', '\201', '\234'};
	char s2[3] = {'\000', '\200', '\234'};
    int num = 2;
	memcpy(s2, s1, num);
	num = 3;
	if(bad_memcmp(s1, s2, num))
		printf("The first %d bytes of data1 doesn't equal data2\n", num);
	else
		printf("The first %d bytes of data1 equal data2\n", num);
}

void test3() {
	long l1 = 0b11101000000;
	long l2 = 0b00011111000;
    int num = 8;
	memcpy(&l2, &l1, num);
	if(bad_memcmp(&l1, &l2, num))
		printf("The first %d bytes of %ld aren't equal to %ld\n", num, l1, l2);
	else
		printf("The first %d bytes of %ld are equal to %ld\n", num, l1, l2);
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
