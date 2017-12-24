#include "file.h"
/* how we print your linked list to test if it is correct or not*/
void print(){
	node *ptr = head;
	while(ptr != NULL){
                printf("%d->",ptr->data);
                ptr = ptr->next;
        }
	printf("\n");
}
/* function to add a node to the list for you use in fwrite*/
void append(node * n){
	n->next = head->next;
	head->next = n;
}
/*function to get the last node for you to allow you to save it a file*/
node * getLast(){
	if(!head->next){
		return NULL;	
	}
	node * ptr = head->next->next; 
	node * tmp = head->next;
	node * prev = head;
	while(ptr != NULL){
		prev = tmp;	
		tmp = ptr;
		ptr = ptr->next;
	}
	prev->next = NULL;
	return tmp;	
}
/* read a node from file until you reach an EOF. Each node you read please save to the linked list with append()*/ 
void read_node(char * filename){
	FILE *myfile = fopen(filename, "rb");
	node* buffer = (node*)malloc(sizeof(node));
//buffer is a temporary placeholder in memory which data can be dumped and then processing can be done

//fread return the number of items read, when end of file is reached, return is 0
	if(myfile){
	while(fread(buffer, sizeof(node), 1, myfile)!= 0){ 
	   append(buffer);//save to the linked list	

//make new address for the new node or else it will keep replacing one address
	   buffer = (node*)malloc(sizeof(node));
	}
	}
	fclose(myfile);
}

/* USING get last keep writing to file until getLast() returns NULL
 * Please use fwrite in binary mode to do this
 * Make sure that you are setting node->next = NULL*/
void write_node(char * filename){
	FILE *myfile = fopen(filename, "wb");
	node* n = getLast();
	if(myfile){
	while(n != NULL){//keep looping until it returns NULL
	   	fwrite(n, sizeof(node), 1, myfile);//fwrite return the number of items written
		n->next = NULL;//next pointer could have been freed or changed the next time we run the program
		free(n);
		n = getLast();//continue to getLast
	}
}
	fclose(myfile);
}


