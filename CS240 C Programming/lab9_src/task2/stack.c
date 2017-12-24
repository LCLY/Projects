#include "stack.h"
#include <stdlib.h>
#include <stdio.h>

int i = 0;
int size = 10;
int* temp;
int* temp2;
void Stack(){

  stack = (int*)malloc(size * sizeof(int));

}

void push(int count){

  stack[i] = count;
  i++;

  if(i == size){
    size = size * 2;
    temp = (int*)malloc(size * sizeof(int));
    int j;
    for(j = 0 ; j < size; j++){
     temp[j] = stack[j];
    }
    
    free(stack);
    stack = temp;
   }

}

int pop(){
  if(i == 0){
      return -1;
  }else{
     int result = stack[i];
     stack[i] = '\0';
     i--;

  if(i < size/2){
    size = size / 2;
    temp2 = (int*)malloc(size * sizeof(int));
     int k;
     for(k = 0 ; k < size; k++){
        temp2[k] = stack[k];
      }
      
       free(stack);
       stack = temp2;
   }
    return result;
  }

}
