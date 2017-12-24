#include <stdio.h>
int main(int argc, char  *argv[]){

  char temp = argv[1][1];
  argv[1][1]= argv[2][1];
  argv[2][1]= temp;
  printf("%s\n%s\n",argv[1],argv[2]);

}
