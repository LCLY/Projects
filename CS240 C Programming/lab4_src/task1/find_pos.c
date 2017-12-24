#include <stdio.h>
#include <stdlib.h>
#include "prototypes.h"
int is_pow_of_two(unsigned int n)
{
	int temp = n;
	if(!(temp&(temp-1)))
		return 1;
	else
		return 0;

      // Complete this function 
}



int find_pos(unsigned int num)
{
   	int check_Power = is_pow_of_two(num);
	int position = 0;
	
	int i = 0;
	if(check_Power == 1){
		while(num){
			num = num >> 1;
			position++;
		}
		return position;
	
	}else{
		return -1;
	}
			
   // Complete this function  
}
