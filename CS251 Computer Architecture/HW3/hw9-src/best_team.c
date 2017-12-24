#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "best_team.h"

// Position meanings
const char * position_name[5] = {"Point Guard", 
    "Shooting Guard", 
    "Small Forward",
    "Power Forward",
    "Center"};
struct Player * _first = NULL;

Team * initializeTeam() {
    // TODO: malloc a new team with 0 players and _first to NULL
	
    Team * new = (Team*) malloc(sizeof(Team));
	
	return new;
}

// Returns something like "02,12,3,8,5,6,4,3,Russell" from file
// Returns 1 line from file for every function call
// Second call will return second line, etc.
char * inputFile(FILE *fd) {
    char * buffer = (char *) malloc(200 * sizeof(char));
    char * bufferptr = buffer;
    *bufferptr = fgetc(fd);
    while (*bufferptr != EOF && *bufferptr != '\0' && *bufferptr != '\n') {
        bufferptr++;
        *bufferptr = fgetc(fd);
    }
    *bufferptr = '\0';
    return buffer;
}

Player * parsePlayer(char * str) {
    // TODO: malloc size of a player, add the details from string to Player type
	Player * members = (Player*)malloc(sizeof(Player));   
	const char x[2] = ",";
	char *token;
	token = strtok(str,x);
	(*members)._shirtNum = atoi(token);
	
	token = strtok(NULL,x);
	(*members)._age = atoi(token);
	
	token = strtok(NULL,x);
	(*members)._pos = atoi(token);
	
	token = strtok(NULL,x);
	(*members)._stats._pass = atoi(token);
	
	token = strtok(NULL,x);
	(*members)._stats._shoot = atoi(token);
	
	token = strtok(NULL,x);
	(*members)._stats._speed = atoi(token);
	
	token = strtok(NULL,x);
	(*members)._stats._block = atoi(token);
	
	token = strtok(NULL,x);
	(*members)._stats._height = atoi(token);
	
	token = strtok(NULL,x);
	(*members)._name = (char*)malloc(200*sizeof(char));
	strcpy(members-> _name,token);

    return members;
}

void loadData(FILE * fd) {
    // TODO: load all data from file to allPlayers
    // Don't forget to free the buffer returned by inputFile
	char* temp = inputFile(fd);
	struct Player *new = (Player*)malloc(sizeof(Player));
	
	while(strcmp(temp, "") != 0) {
		new = parsePlayer(temp);
		new -> _next = NULL;
		free(temp);
		temp = inputFile(fd);
		
		if (_first == NULL) {
			_first = new;
			allPlayers = new;
		} else {
			allPlayers->_next = new;
			allPlayers = new;
		}

	}
}

Player * findBestPlayer(int pos) {
    // TODO: iterate through allPlayers
    // Returns the pointer to the best player for the specified pos
	allPlayers = _first;
	
	struct Player * best = (Player *) malloc (sizeof(Player));
	
	while(allPlayers != NULL) {
		
		if(allPlayers->_pos == pos) {
			if (allPlayers -> _stats._pass > best ->_stats._pass || 
				allPlayers -> _stats._shoot > best ->_stats._shoot ||
				allPlayers -> _stats._speed > best ->_stats._speed ||
				allPlayers -> _stats._block > best ->_stats._block ||
				allPlayers -> _stats._height > best ->_stats._height) {
					best = allPlayers;
			}
		}
		
		allPlayers = allPlayers -> _next;
	}
	
    return best;
	  
}

int buildBestTeam(Team * t) {
    // TODO: utilize findBestPlayer(pos) to build the best team 
    // order of players has to be PG SG SF PF C
	
	(*t)._players[0] = findBestPlayer(PG);
	(*t)._players[1] = findBestPlayer(SG);
	(*t)._players[2] = findBestPlayer(SF);
	(*t)._players[3] = findBestPlayer(PF);
	(*t)._players[4] = findBestPlayer(C);
	(*t)._numOfPlayers = 5;
	
    return t-> _numOfPlayers;
    
}

void freePlayer(Player * p) {
    // TODO: free allocated memory in p
	free(p -> _name);
	free(p -> _next);
	free(p);
}

void freeTeam(Team * t) {
    // TODO: free allocated memory in t, utilize freePlayer(p)
	int i;
	
	for (i = 0; i < 5; i++) {
		free(t -> _players[i]);
		}
		t->_numOfPlayers = 0;
		free(t);
}

void print(Team * t) {
    printf("=========================\n");
    printf("      The Best Team      \n");
    printf("=========================\n\n");
    int i;
    for (i = 0; i < t->_numOfPlayers; i++) {
        printf("Name     : %s\n", t->_players[i]->_name);
        printf("Age      : %d\n", t->_players[i]->_age);
        printf("Shirt #  : %d\n", t->_players[i]->_shirtNum);
        printf("Position : %s\n\n", position_name[t->_players[i]->_pos]);
    }
}
