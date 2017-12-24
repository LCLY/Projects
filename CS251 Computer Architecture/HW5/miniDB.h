#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Node{
	char* title;
	char* date;
	char* director;
	char* id;
	struct  Node* next;

}Node;

Node * head;
int count;
char* dataBase;
char* command;
char* output;
Node* prev;

int exist (Node * node);
void addToLinkedList(Node * n);
void readCmd(char * cmd);
void addCmd(char * cmd);
void editCmd(char * cmd);
void removeCmd(char * cmd);
void lookupCmd(char* cmd);
void displayCmd(char * cmd);
void writeCmd(char * cmd);
void write();
void writeOutput(Node * temp, int num);
Node * sort(Node * list, int cases, int order);
void bubbleSwap(Node * a, Node * b);
int cmp(char * a, char * b);
int cmpDate(char * a, char * b);