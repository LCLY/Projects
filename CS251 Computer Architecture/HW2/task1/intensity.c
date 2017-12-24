#include <stdio.h>
#include "../lib/imageio.h"
#include <string.h>
#include <stdlib.h>
int main(int argc,char * argv[]){
	
	IMAGE i = image_open(argv[1]);
	if(i == NULL)
		return -1;
	
	IMAGE i_copy = image_clone(i);
	
	if(i_copy == NULL)
		return -1;
	
	int * i_pixels = image_pixels(i_copy);
	
	int width = image_width(i);
	int height = image_height(i);
	int pixels = width * height;
	
	int x;
	for(x = 0 ; x < pixels ; x++){
		int pixel = i_pixels[x];
		unsigned char* pixPointer = (unsigned char*)&pixel;
		int j;
		for(j = 0 ; j < 3 ; j++){
			pixPointer[j] = pixPointer[j] * atof(argv[2]);
			if(pixPointer[j] > 255){
				pixPointer[j] = 255;
			}else if(pixPointer[j] < 0){
				pixPointer[j] = 0;
			}
		}
		i_pixels[x] = pixel;
	}
		
	char slash = '/';
	char * new_Array;
	char dest[100];
	
	strcpy(dest , argv[1]);
	new_Array = strrchr(dest , slash);
	new_Array = new_Array + 1;
	new_Array[strlen(new_Array) - 4] = '\0';
	strcat(new_Array, "_int.jpg");
	if(image_save(i_copy, new_Array))
		return -1;
	image_close(i_copy);
	
	return 0;
}