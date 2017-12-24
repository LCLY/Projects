#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main(){
    char* printinteger(const char**, char);

    char a = 'w';
    char b = 'x';
    char c = 'y';
    char d = 'z';

    char* firstString = "apple";
    char* secondString = "orange";
    char* thirdString = "banana";
    char* forthString = "honeydew";

    int hex = 1;
    int dec = 1;

    myprintf("Print characters: %c, %c, %c, %c\n", a, b, c, d);
    myprintf("Print strings: %s, %s, %s, %s\n", firstString, secondString, thirdString, forthString);
    myprintf("Print hexadecimals: %x, %x, %x\n", hex, hex, hex);
    myprintf("Print decimals: %d, %d, %d\n", dec, dec, dec);
}