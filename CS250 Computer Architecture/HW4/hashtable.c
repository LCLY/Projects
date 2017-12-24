#include <stdlib.h>
#include <string.h>

#include "hashtable.h"

static hashtable_ent_t* add_new(hashtable_ent_t** entry, const char* key) {
    if (entry == NULL || key == NULL) return NULL;
    *entry = (hashtable_ent_t*)malloc(sizeof(hashtable_ent_t));
    if (*entry == NULL) return NULL;
    (*entry)->key = strdup(key);
    if ((*entry)->key == NULL) {
        free(*entry);
        return NULL;
    }
    (*entry)->next = NULL;
    return *entry;
}

/*
 * Helper function that does most of the implemention work
 */
static hashtable_ent_t* locate(hashtable_t* ht, const char* key, int create_if_missing) {
    if (ht == NULL || key == NULL) return NULL;
    int h = hash(key) % ht->table_len;
    hashtable_ent_t* node = ht->table[h];
    if (node != NULL) {
        /* Search until we find a match or hit the end */
        while (node->next != NULL && strcmp(key, node->key)) node = node->next;
        if (!strcmp(key, node->key)) {
            return node;
        } else if (node->next == NULL && create_if_missing) {
            /* If we failed to find the key, we can create an entry in place */
            return add_new(&node->next, key);
        }
    } else if (create_if_missing) {
        return add_new(&ht->table[h], key);
    }
    return NULL;
}

hashtable_t *create_hashtable(int max_size) {
   //TODO
   if(max_size <= 0){
    return NULL;
   }
   hashtable_t* ht = (hashtable_t*)malloc(sizeof(hashtable_t));
   if(ht == NULL){
    return NULL:
   }
   ht->table_len = max_size;
   ht->table = (hashtable_ent_t**)malloc(max_size*sizeof(hashtable_ent_t*));

   if(ht->table == NULL){
    free(ht);
    return NULL;
   }
   
   int i;
   for(i = 0 ; i < ht->table_len ; i++){
        ht->table[i] = NULL;
   }
   return ht;
}

void free_hashtable(hashtable_t *ht) {
    //TODO
    int i;
    for(i = 0 ; i < ht->table_len ; i++){
        if(ht->table[i] != NULL){
            free(ht->table[i]);
            ht->table[i] = ht->table[i]->next;
        }

    }
    free(ht);
}

int get(hashtable_t *ht, const char *key, double *value) {
    //TODO
    if(ht == NULL || key == NULL || value == NULL){
        return -1;
    }
    hashtable_ent_t* x = locate(ht, key, 0);

    if(x == NULL){
        value == NULL;
        return -1;
    }

    *value = node -> value;
    return 0;


}

int set(hashtable_t *ht, const char *key, double value) {
   //TODO
    if(ht == NULL || key == NULL){
        return -1;
    }

    hashtable_ent_t* x = locate(ht, key , 1);
    if(x == NULL){
        return -1;
    }

    x->value = value;

    return 0;

}


int key_exists(hashtable_t *ht, const char *key) {
//TODO
    if(ht == NULL || key == NULL){
        return -1;
    }
    if(locate(ht , key , 0) == NULL){
        return 0;
    }
    return 1;

}

int remove_key(hashtable_t *ht, const char *key) {
//TODO
    if(ht == NULL || key == NULL){
        return -1;
    }
    int i = hash(key) % ht->table_len;
    hashtable_ent_t* temp = ht->table[i];

    if(temp != NULL){
        if(strcmp(key , temp->key) == 0){
            ht->table[i] = temp->next;
            free(temp->key);
            free(temp);
            return 0;
        }
        while(temp -> next != NULL){
            while(strcmp(key, temp->next->key)){
                temp = temp -> next;
            }
        }

        if(strcmp(key, temp->next->key) == 0){
            hashtable_ent_t* newTemp = temp -> next;
            temp -> next = newTemp -> next;
            free(newTemp->key);
            free(newTemp);
            return 0;
        }
    }
    return -1;
    
}