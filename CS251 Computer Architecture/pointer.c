#include "stdlib.h"
#include "stdio.h"
 void main(){
 	int x = 5;
 	int *p;
 	int **q;
 	int ***r;
 	printf("&x is %d\n\n",&x);
 	p = &x;
 	printf("*p is %d\n",*p);
 	printf("p is %d\n",p); 
 	printf("&p is %d\n\n",&p);
 	q = &p;
 	printf("**q is %d\n", *(*q));
 	printf("*q is %d\n", *q);
 	printf("q is %d\n", q);
 	printf("&q is %d\n\n", &q);
 	r = &q;
 	printf("***r is %d\n",***r);
 	printf("**r is %d\n",**r);
 	printf("*r is %d\n", *r);
 	printf("r is %d\n", r);
 	printf("&r is %d\n\n", &r);
 	*q = p;
 	printf("**q is %d\n", *(*q));
 	printf("*q is %d\n", *q);
 	printf("q is %d\n", q);
 	printf("&q is %d\n\n", &q);
 	***r = 2;
 	x = 4;
 	***r = ***r+**q+*p;
 	printf("***r = %d\n",***r);
 	printf("**q = %d\n", **q);
 	printf("*q = %d\n", *q);
 }