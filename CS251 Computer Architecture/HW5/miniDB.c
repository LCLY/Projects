#include "miniDB.h"

void addCmd(char* cmd){
	char* tmp_Cmd = malloc(512* sizeof(char));
	strcpy(tmp_Cmd, cmd);
	char* token = malloc(512* sizeof(char));
	token = strtok(tmp_Cmd + 5, ",");
	Node* tmpNode = (Node*)malloc(sizeof(Node));

	tmpNode -> title = malloc(512 * sizeof(char));
	tmpNode -> date = malloc(512 * sizeof(char));
	tmpNode -> director = malloc(512 * sizeof(char));
	tmpNode -> id = malloc(512 * sizeof(char));
	tmpNode -> next = (Node *) malloc(sizeof(Node));
	tmpNode -> next = NULL;

	strcpy(tmpNode -> title, token);
	token = strtok(NULL, ","); 
	strcpy(tmpNode -> date, token + 1);
	token = strtok(NULL, ","); 
	strcpy(tmpNode -> director, token + 1);
	token = strtok(NULL, ",\r\n"); 
	strcpy(tmpNode -> id, token + 1);

	if(!exist(tmpNode)){
		addToLinkedList(tmpNode);
	}

	write();
}

int exist(Node* node){
	Node* tmp = head;
	while(tmp != NULL){
		if(strcmp(node->id , tmp->id) == 0){
			return 1;
		}
		tmp = tmp->next;
	}

	return 0;
}

void addToLinkedList(Node* node){
	Node* tmp = head;
	if(tmp == NULL){
		head = node;
		count = 1;
	}else{
		while(tmp->next != NULL){
			tmp = tmp -> next;
		}
		tmp->next = node;
		count++;
	}
}

void write(){
	FILE * data = fopen(dataBase, "w");
	Node* tmp = head;

	while(tmp != NULL){
		char* str = malloc(512* sizeof(char));
		strcpy(str, tmp -> title);
		strcat(str, ", ");
		strcat(str, tmp -> date);
		strcat(str, ", ");
		strcat(str, tmp -> director);
		strcat(str, ", ");
		strcat(str, tmp -> id);
		strcat(str, "\n");

		fwrite(str, sizeof(char), strlen(str), data);
		tmp = tmp -> next;
	}
	fclose(data);
}

void readCmd(char* cmd){
	char* tmp = malloc(512* sizeof(char));
	strcpy(tmp, cmd);
	char* token = malloc(512* sizeof(char));
	token = strtok(tmp, ",\n");

	if (strcmp(token, "ADD") == 0) {
		addCmd(cmd);
	} else if (strcmp(token, "EDIT") == 0) {
		editCmd(cmd);
	} else if (strcmp(token, "REMOVE") == 0) {
		removeCmd(cmd);
	} else if (strcmp(token, "LOOKUP") == 0) {
		lookupCmd(cmd);
	} else if (strcmp(token, "DISPLAY") == 0) {
		displayCmd(cmd);
	}	
}

void editCmd(char* cmd){
	char* tmp = malloc(512* sizeof(char));
	strcpy(tmp, cmd);
	char* token = malloc(512* sizeof(char));
	token = strtok(tmp + 6, ",");
	Node* node = head;

	while (node != NULL) {
		if (strcmp(node -> id, token) == 0) {

			token = strtok(NULL, ",");

			if (strcmp(token + 1, "TITLE") == 0) {
				token = strtok(NULL, ",\n");
				node -> title = malloc(512 * sizeof(char));
				strcpy(node -> title, token + 1);
				break;
			} else if (strcmp(token + 1, "DATE") == 0) {
				token = strtok(NULL, ",\n");
				node -> date = malloc(512 * sizeof(char));
				strcpy(node -> date, token + 1);
				break;
			} else if (strcmp(token + 1, "DIRECTOR") == 0) {
				token = strtok(NULL, ",\n");
				node -> director = malloc(512 * sizeof(char));
				strcpy(node -> director, token + 1);
				break;
			} else if (strcmp(token + 1, "ID") == 0) {
				token = strtok(NULL, ",\n");
				node -> id = malloc(512 * sizeof(char));
				strcpy(node -> id, token + 1);
				break;
			}
		}

		node = node -> next;
	}

	write();

}

void removeCmd(char* cmd){
	char* tmp = malloc(512* sizeof(char));
	strcpy(tmp, cmd);
	char* token = malloc(512* sizeof(char));
	prev = malloc(sizeof(Node));
	token = strtok(tmp + 8, ",\n");
	Node* node = head;

	while(node != NULL){
		if(strcmp(node->id, token) == 0){
			prev->next = node->next;
			count--;
			break;
		}
		prev = node;
		node = node -> next;
	}
	write();
}

void lookupCmd(char* cmd){
	char* tmp = malloc(512* sizeof(char));
	strcpy(tmp, cmd);
	writeCmd(cmd);
	char* token = malloc(512* sizeof(char));
	token = strtok(tmp + 8, ",");
	Node* node = head;

	if(strcmp(token, "TITLE") == 0){
		token = strtok(NULL, ",\n");
		while(node != NULL){
			if(strcmp(node->title, token + 1) == 0){
				writeOutput(node, 1);
			}
			node = node -> next;
		}

	} else if(strcmp(token, "DATE") == 0){
		token = strtok(NULL, ",\n");
		while(node != NULL){
			if(strcmp(node->date, token + 1 ) == 0){
				writeOutput(node, 1);
			}
			node = node -> next;
		}
	} else if(strcmp(token, "DIRECTOR") == 0){
		token = strtok(NULL, ",\n");
		while(node != NULL){
			if(strcmp(node->director, token + 1) == 0){
				writeOutput(node, 1);
			}
			node = node -> next;
		}
	} else if(strcmp(token, "ID") == 0){
		token = strtok(NULL, ",\n");
		while(node != NULL){
			if(strcmp(node->id, token + 1) == 0){
				writeOutput(node , 1);
			}
			node = node -> next;
		}
	}

}

void writeOutput(Node* tmp, int num){
	FILE * of = fopen(output, "a+");//a = append
	int i;
	if(num > count){
		num = count;
	}// if exceeds number, number become number

	for(i = 0 ; i < num ; i++){
		char* str = malloc(512* sizeof(char));
		strcpy(str, tmp -> title);
		strcat(str, ", ");
		strcat(str, tmp -> date);
		strcat(str, ", ");
		strcat(str, tmp -> director);
		strcat(str, ", ");
		strcat(str, tmp -> id);
		strcat(str, "\n");
		
		fseek(of, 0, SEEK_END);
		fputs(str, of);
		tmp = tmp -> next;
	}
	fclose(of);
}

void displayCmd(char* cmd){
	char * tmp = malloc(512 * sizeof(char));
	strcpy(tmp, cmd);
	writeCmd(cmd);
	char * token = malloc(512 * sizeof(char));
	token = strtok(tmp + 9, " ,\n");
	Node * sortList = head;

	if (strcmp(token, "TITLE") == 0) {
		token = strtok(NULL, " ,\r\n");

		if (atoi(token)) {
			token = strtok(NULL, " ,\r\n");
			writeOutput(sort(sortList, 0, 1), atoi(token));
		} else {
			token = strtok(NULL, " ,\r\n");
			writeOutput(sort(sortList, 0, 0), atoi(token));
		}
	} else if (strcmp(token, "DATE") == 0) {
		token = strtok(NULL, " ,\r\n");

		if (atoi(token)) {
			token = strtok(NULL, " ,\r\n");
			writeOutput(sort(sortList, 1, 1), atoi(token));
		} else {
			token = strtok(NULL, " ,\r\n");
			writeOutput(sort(sortList, 1, 0), atoi(token));
		}
	} else if (strcmp(token, "DIRECTOR") == 0) {
		token = strtok(NULL, " ,\r\n");

		if (atoi(token)) {
			token = strtok(NULL, " ,\r\n");
			writeOutput(sort(sortList, 2, 1), atoi(token));
		} else {
			token = strtok(NULL, " ,\r\n");
			writeOutput(sort(sortList, 2, 0), atoi(token));
		}
	} else if (strcmp(token, "ID") == 0) {
		token = strtok(NULL, " ,\r\n");

		if (atoi(token)) {
			token = strtok(NULL, " ,\r\n");
			writeOutput(sort(sortList, 3, 1), atoi(token));
		} else {
			token = strtok(NULL, " ,\r\n");
			writeOutput(sort(sortList, 3, 0), atoi(token));
		}
	}
}

Node* sort(Node* list, int cases, int order){
	Node * tmp = list;
	prev = malloc(sizeof(Node));
	int i;

	if(cases == 0){
		prev = tmp;
		tmp = tmp -> next;
		if(order){
			for(i = 0; i < count ; i++){
				while(tmp != NULL){
					if(cmp(prev->title, tmp->title)){
						bubbleSwap(prev,tmp);
					}
					prev = tmp;
					tmp = tmp -> next;
				}
				tmp = list;
			}
		}else{
			for(i = 0 ; i < count ; i++){
				while(tmp != NULL){
					if(!cmp(prev->title, tmp->title)){
						bubbleSwap(prev, tmp);
					}
					prev = tmp;
					tmp = tmp -> next;
				}
				tmp = list;
			}
		}

	} else if (cases == 1){
		prev = tmp;
		tmp = tmp -> next;
		if(order){
			for(i = 0; i < count ; i++){
				while(tmp != NULL){
					if(cmpDate(prev->date, tmp->date)){
						bubbleSwap(prev,tmp);
					}
					prev = tmp;
					tmp = tmp -> next;
				}
				tmp = list;
			}
		}else{
			for(i = 0 ; i < count ; i++){
				while(tmp != NULL){
					if(!cmp(prev->date, tmp->date)){
						bubbleSwap(prev, tmp);
					}
					prev = tmp;
					tmp = tmp -> next;
				}
				tmp = list;
			}
		}
	}else if(cases == 2){
		prev = tmp;
		tmp = tmp -> next;
		if(order){
			for(i = 0; i < count ; i++){
				while(tmp != NULL){
					if(cmp(prev->director, tmp->director)){
						bubbleSwap(prev,tmp);
					}
					prev = tmp;
					tmp = tmp -> next;
				}
				tmp = list;
			}
		}else{
			for(i = 0 ; i < count ; i++){
				while(tmp != NULL){
					if(!cmp(prev->director, tmp->director)){
						bubbleSwap(prev, tmp);
					}
					prev = tmp;
					tmp = tmp -> next;
				}
				tmp = list;
			}
		}
	}else if(cases == 3){
		prev = tmp;
		tmp = tmp -> next;
		if(order){
			for(i = 0; i < count ; i++){
				while(tmp != NULL){
					if(cmp(prev->id, tmp->id)){
						bubbleSwap(prev,tmp);
					}
					prev = tmp;
					tmp = tmp -> next;
				}
				tmp = list;
			}
		}else{
			for(i = 0 ; i < count ; i++){
				while(tmp != NULL){
					if(!cmp(prev->id, tmp->id)){
						bubbleSwap(prev, tmp);
					}
					prev = tmp;
					tmp = tmp -> next;
				}
				tmp = list;
			}
		}
	}
	return list;

}

void bubbleSwap(Node* a, Node* b){
	char * tmpTitle = malloc(512 * sizeof(char));
	char * tmpDate = malloc(512 * sizeof(char));
	char * tmpDirector = malloc(512 * sizeof(char));
	char * tmpID = malloc(512 * sizeof(char));

	strcpy(tmpTitle, a -> title);
	strcpy(tmpDate, a -> date);
	strcpy(tmpDirector, a -> director);
	strcpy(tmpID, a -> id);

	a -> title = malloc(512 * sizeof(char));
	a -> date = malloc(512 * sizeof(char));
	a -> director = malloc(512 * sizeof(char));
	a -> id = malloc(512 * sizeof(char));

	strcpy(a -> title, b -> title);
	strcpy(a -> date, b -> date);
	strcpy(a -> director, b -> director);
	strcpy(a -> id, b -> id);

	b -> title = malloc(512 * sizeof(char));
	b -> date = malloc(512 * sizeof(char));
	b -> director = malloc(512 * sizeof(char));
	b -> id = malloc(512 * sizeof(char));

	strcpy(b -> title, tmpTitle);
	strcpy(b -> date, tmpDate);
	strcpy(b -> director, tmpDirector);
	strcpy(b -> id, tmpID);

}
int cmp(char* a, char* b){
	int i;
	int len;
	int result;
	
	if(strlen(a) < strlen(b)){
		len = strlen(a);
	}else{
		len = strlen(b);
	}

	for(i = 0 ; i < len ; i++){
		if(a[i] > b[i]){
			result = 0;
			break;
		}else if(b[i] > a[i]){
			result = 1;
			break;
		}
	}
	return result;
}

int cmpDate(char* a, char* b){
	char * tmp_A = malloc(512 * sizeof(char));
	char * tmp_B = malloc(512 * sizeof(char));

	strcpy(tmp_A, a);
	strcpy(tmp_B, b);

	int month_A = atoi(strtok(tmp_A, "/"));
	int date_A = atoi(strtok(NULL, "/"));
	int year_A = atoi(strtok(NULL, "/"));
	int month_B = atoi(strtok(tmp_B, "/"));
	int date_B = atoi(strtok(NULL, "/"));
	int year_B = atoi(strtok(NULL, "/"));

	if (year_A > year_B)
		return 0;

	if (year_B > year_A)
		return 1;

	if (month_A > month_B)
		return 0;

	if (month_B > month_A)
		return 1;

	if (date_A > date_B)
		return 0;

	if (date_B > date_A)
		return 1;

	return 0;
}

void writeCmd(char* cmd){
	FILE* of = fopen(output, "a+");
	fseek(of, 0, SEEK_END);
	fputs(cmd, of);
	fclose(of);
}

int main(int argc, char* argv[]){
	dataBase = malloc(512* sizeof(char));
	command = malloc(512* sizeof(char));
	output = malloc(512* sizeof(char));

	strcpy(dataBase,argv[1]);
	strcpy(command, argv[2]);
	strcpy(output, argv[3]);

	FILE* df = fopen(argv[1], "r");
	char* tmp = malloc(512* sizeof(char));
	if(df){
		while(fgets(tmp, 512, df)){
			char* a = malloc(512* sizeof(char));
			strcpy(a, "ADD, ");
			strcat(a, tmp);
			addCmd(a);//put into linked list
		}
		fclose(df);
	}

	FILE * cmdFile = fopen(command, "r");
	char* tmp2 = malloc(512* sizeof(char));

	if(cmdFile){
		while(fgets(tmp2, 512, cmdFile)){
			readCmd(tmp2);
		}
		fclose(cmdFile);
	}
	return 0;
}

