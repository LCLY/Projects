#include "Queue_List.h"

/*HINT: After freeing anything with free(), set its pointer to NULL.
 This may solve some difficult-to-find bugs */

/*********************************************/
/*                INITITALIZE                */
/*********************************************/

/*-------------------------------------------------------
*
*	Function returns a Queue type.  Should be called once per 'queue'.
*	This function sets initial values for each member variable in the <list> instance.
*	(typically to NULL or 0)
*/
Queue newQueueList(){//use this as an example for syntax
	Queue list;
	list = (Queue)malloc(sizeof(struct QueueList));
	list->_head = NULL;
	list->_tail = NULL;
	list->_count = 0;
	//TODO:  Finish this initialization
	return list;
}


/*-------------------------------------------------------
*
*	Function returns a QueuedCar type.  Should be called once per 'order'.
*	This function sets initial values for each member variable in the <node> instance.
*	(typically to NULL or 0)
*/
QueuedCar newQueueNode(){
	QueuedCar node;
	node = (QueuedCar)malloc(sizeof(struct QueueNode));
	node->_next = NULL;
	node->_prev = NULL;
	node->_color = NULL;
	node->_make = NULL;
	node->_model = NULL;
	node->_buyer = NULL;
	node->_leather = 0;
	//TODO:  Finish this initialization
	return node;
}

/*********************************************/
/*                  SETTERS                  */
/*********************************************/

/*-------------------------------------------------------
*
*	Function takes in a QueuedCar (car) and a char* (color).
*	It sets the variable <color> in (car) to be the value (color)
*	
*/
void setCarColor(QueuedCar car, char color[]){//Use malloc and strcpy() to set values
//(This space will need to be freed in deleteNode)
	//TODO:  Implement this function
	car->_color = malloc(sizeof(strlen(color) * sizeof(char)));
	strcpy(car->_color, color);
}


/*-------------------------------------------------------
*
*	Function takes in a QueuedCar (car) and a char* (make).
*	It sets the variable <make> in (car) to be the value (make)
*	
*/
void setCarMake(QueuedCar car, char make[]){//Use malloc and strcpy() to set values
//(This space will need to be freed in deleteNode)
	//TODO:  Implement this function
	car->_make = malloc(sizeof(strlen(make) * sizeof(char)));
	strcpy(car->_make, make);
}


/*-------------------------------------------------------
*
*	Function takes in a QueuedCar (car) and a char* (model).
*	It sets the variable <model> in (car) to be the value (model)
*	
*/
void setCarModel(QueuedCar car, char model[]){//Use malloc and strcpy() to set values
//(This space will need to be freed in deleteNode)
	//TODO:  Implement this function
	car->_model = malloc(sizeof(strlen(model) * sizeof(char)));
	strcpy(car->_model, model);
}


/*-------------------------------------------------------
*
*	Function takes in a QueuedCar (car) and a char* (name).
*	It sets the variable <buyer> in (car) to be the value (name)
*	
*/
void setCarBuyer(QueuedCar car, char name[]){//Use malloc and strcpy() to set values
//(This space will need to be freed in deleteNode)
	//TODO:  Implement this function
	car->_buyer = malloc(sizeof(strlen(name) * sizeof(char)));
	strcpy(car->_buyer , name);
}


/*-------------------------------------------------------
*
*	Function takes in a QueuedCar (car).
*	It sets the variable <leather> in (car) to be 1;
*	
*/
void addLeather(QueuedCar car){
	//TODO:  Implement this function
	car->_leather = 1;
}


/*-------------------------------------------------------
*
*	Function takes in a QueuedCar (car).
*	It sets the variable <leather> in (car) to be 0
*	
*/
void removeLeather(QueuedCar car){
	//TODO:  Implement this function
	car->_leather = 0;
}



/*********************************************/
/*                  GETTERS                  */
/*********************************************/

/*-------------------------------------------------------
*
*	Function takes in a QueuedCar (car).
*	It returns a pointer to the value <color> in (car)
*	
*/
char* getCarColor(QueuedCar car){
	//TODO:  Implement this function
	return car->_color;
}


/*-------------------------------------------------------
*
*	Function takes in a QueuedCar (car).
*	It returns a pointer to the value <make> in (car)
*	
*/
char* getCarMake(QueuedCar car){
	//TODO:  Implement this function
	return car->_make;

}


/*-------------------------------------------------------
*
*	Function takes in a QueuedCar (car).
*	It returns a pointer to the value <model> in (car)
*	
*/
char* getCarModel(QueuedCar car){
	return car->_model;
	//TODO:  Implement this function
}


/*-------------------------------------------------------
*
*	Function takes in a QueuedCar (car).
*	It returns a pointer to the value <buyer> in (car)
*	
*/
char* getCarBuyer(QueuedCar car){
	return car->_buyer;
	//TODO:  Implement this function
}


/*-------------------------------------------------------
*
*	Function takes in a QueuedCar (car).
*	It returns the value of <leather> in (car)
*	
*/
int getCarLeather(QueuedCar car){
	return car->_leather;
	//TODO:  Implement this function
}


/*********************************************/
/*                OPERATIONS                 */
/*********************************************/

/*---------------------------------------------------------------------------
*
*	-Function takes in a QueuedCar (car) and Queue (list).
*	-This function will enqueue(add to the tail) into (list).
*	-Be sure to update next, prev, _head, and tail, if applicable.
*	-Be sure to do error checking and check for edge cases - For example,
*		the case where _head == tail in (list); the case where list is empty; etc.
*
*	If (car) is NULL, just return;
*	
*/
void enqueue(Queue list, QueuedCar car){
	//TODO:  Implement this function
	if(car == NULL){
		return;
	}

	if(list->_head == NULL){
		list->_head = car;
		list->_tail = car;
		list->_count++;

	} else {
		list->_tail->_next = car;
		car->_prev = list->_tail;
		//car->_next = list->_head;
		//list->_head->_prev = car;
		list->_tail = car;
		list->_count++;
	}

}


/*---------------------------------------------------------------------
*
*	Function takes in a Queue (list)
*	-It removes and returns the QueuedCar node
*		which was at the _head of the queue
*	-Be sure to update pointers correctly.
*	-Be sure to update (list)->_head.
*	-Be sure to update (list)->count.
*	-Do not free the node that is dequeued.
*	
*	
*/
QueuedCar dequeue(Queue list){
	if(list->_head == NULL){
		return NULL;
	}

	//TODO:  Implement this function
	struct QueueNode* _temp = list->_head;
	//list->_tail->_next = list->_head->_next;
	//list->_head->_prev->_next = list->_head->_next; same thing 
	//list->_head->_next = list->_tail;
	//list->_head->_next->_prev = list->_tail;
	list->_head = list->_head->_next;
	list->_count--;
	return _temp;
}


/*-----------------------------------------------------------------------------------
*
*	-Function takes in a Queue (list).
*	-It returns the QueuedCar node which is currently being pointed to by list->_head
*	-Do not dequeue the node.  Do not free it.
*	-If the list is empty, return NULL
*/
QueuedCar queuePeek(Queue list){
	//TODO:  Implement this function
	
	if(list->_head == NULL){ //both are NULL
	return NULL;
	}
	return list->_head;
}


/*---------------------------------------------------------------------------------------
*
*	Function takes in a QueuedCar (car)
*	-Free any member variables that used malloc()
*	-Free (car) itself
*	
*/
void deleteNode(QueuedCar car){
	//TODO:  Implement this function
	free(car->_color);
	free(car->_make);
	free(car->_model);
	free(car->_buyer);
	free(car);
}


/*--------------------------------------------------------
*	
*	-Function takes in a Queue (list)
*	-Removes all nodes from the Queue(updating pointers after each removal)
*	-After each node removal, free the member variables
		 in that node, then free that node.
*	-After (list) is empty,  free (list)
*	
*/
void deleteQueue(Queue list){
	//TODO:  Implement this function
	
	while(list->_count > 0){
		QueuedCar _temp = dequeue(list);
		deleteNode(_temp); 	
	}
	free(list);
}

/*-------------------------------------------------------------------
*
*	Remove (car) from (list) if (car) is in (list).  This means make sure the 
*		_next and _prev pointers are updated correctly.
*	If (list) == NULL, return immediately.
*	After removing the node, be sure to set its _next and _prev pointers to NULL
*	Remember to check for edge cases:
*		-(car) is _head
*		-(car) is _tail
*		-(car) is only item in list
*		etc.
*/
void removeNode(Queue list, QueuedCar car){
	//EXTRA CREDIT TODO:  Implement this function
	
	if(list == NULL){
		return;
	}

	if(list->_head == NULL || car == NULL){
		return;
	}
	//head	
	if(car == list->_head){
		list->_head = car->_next;
		car->_prev = NULL;
		car->_next = NULL;
		
	} else if (car->_prev != NULL){ //Not the first node
				
		car->_prev->_next = car->_next;
		car->_next = NULL;
		car->_prev = NULL;

	}else if(list->_count == 1){
		
		car->_next = NULL;
		car->_prev = NULL;
		
	}/*else{ //in between
		QueuedCar _current = list->_head->_next;
		while(_current != list->_head){
			if(strcmp(car->_model , _current->_model) == 0 && strcmp(car->_make , _current->_make) == 0 
			&& strcmp(car->_color , _current->_color) == 0 && strcmp(car->_buyer , _current->_buyer) == 0){
				
				_current->_prev->_next = _current->_next;
				_current->_next->_prev = _current->_prev;
				_current->_next = NULL;
				_current->_prev = NULL;
				//deleteNode(_current);
				//list->_tail->_next = NULL;
				//list->_tail->_prev = NULL;				
				break;
			}
			_current = _current->_next;
		}

	}*/
	else if(car->_next != NULL){ //NOT the LAST
		car->_next->_prev = car->_prev;
		car->_next = NULL;
		car->_prev = NULL;
	
	}
	
	

}
