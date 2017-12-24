int myprintf(const char* format, ...){
   char* temp = (char*) format;
   int count_Char = 0;
   int count_Argument = 0;

   while(temp[count_Char] != 0){
	if(temp[count_Char] == '\n'){
           putchar('\n');
	}

	if(temp[count_Char] == '%' && temp[count_Char+1] != 0 &&
	   temp[count_Char+1] != '%' && temp[count_Char] != '\n'){
	    
	    if(temp[count_Char+1] == 'c'){
		++count_Argument;
		printc(&format+count_Argument);
		count_Char++;
	    }


	    if(temp[count_Char+1]== 's'){
		++count_Argument;
		prints(&format+count_Argument);
		count_Char++;
	    }

	    if(temp[count_Char+1] == 'x' || temp[count_Char+1] == 'd'){
		++count_Argument;
 	  	if(temp[count_Char+1] == 'x'){
		    printinteger(&format+count_Argument, 'x');
		}
		if(temp[count_Char+1] == 'd'){
	  	    printinteger(&format+count_Argument, 'd');
		}
		count_Char++;
	    }

	}else if(temp[count_Char] == '%'){
	    putchar('%');
	}else{
	    putchar(temp[count_Char]);
	   
	}
	count_Char++;

   }

}

int printc(void* argument){
    char* temp = (char*) argument;
    int count_Char = 0;
    while(temp[count_Char] != 0){
	putchar(temp[count_Char]);
	count_Char++;
    }
}

int prints(void** argument){
    char* temp = (char*)*argument;
    int count_Char = 0;
    while(temp[count_Char] != 0){
    	putchar(temp[count_Char]);
    	count_Char++;
    }

}


