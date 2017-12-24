#include <stdio.h>

int isBigEndian(char* p) {
	//TODO: Implement your function here
	if(p[2] == 'D'){
		return 1;
	}else{
		return 0;
	}
}

/**
 * Hint:
 * 0x40490FDB is PI in little endian format
 * 0xDB0F4940 is PI in big endian format
 * Would different representation affect the value of PI?
 */
int main(int argc, char* argv[])
{
	if (argc < 2){
		printf("Usage: endian input\n");
		return 1;
	}
	if (isBigEndian(argv[1])){
		printf("It's Big Endian\n");
	}
	else {
		printf("It's Little Endian\n");
	}

	return 0;
}
