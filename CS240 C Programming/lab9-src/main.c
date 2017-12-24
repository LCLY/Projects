#include <stdio.h>
#include <malloc.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <string.h>

#define READABLE_FILE "file_to_read.txt" /* File to be read during read operations */
#define BYTES_TO_READ_WRITE 819200 /* 800 KB */
#define MAX_BUFFER  1048576 /* 1 MB*/

/* Declare global variables for write operations here */
int fd_write = 1;

/* Declare global variables for read operations here */
int fd_read;

char buf_write[MAX_BUFFER] ;
char buf_read[MAX_BUFFER] ;
char* wp;
char* rp;
int buf_write_size = 0;
int buf_read_size = 0;
int readcount;
/* Function for write without buffer */
void mywritec(char ch){
    write(fd_write, &ch, 1);
}

/* Functions for write with buffer */
void mywritebufsetup(int n){
    if(n <= MAX_BUFFER){
        buf_write_size = n;
        wp = buf_write;
    }
}

void myputc(char ch){
    *(wp++) = ch;
    if(wp == &(buf_write[buf_write_size])){
        write(fd_write,&buf_write,buf_write_size);
        wp = buf_write;
    }
}

void mywriteflush(void){
    char* temp = buf_write;
    while(temp < wp){
        write(fd_write, temp, 1);
        temp++;
    }
    wp= buf_write;
}

/* Function for read without buffer */
int myreadc(void){
    char temp;
    int result;
    result = read(fd_read, &temp, 1);
    if(result == 0){
        result = -1;
    }
    return (int)temp;
}

/* Functions for read with buffer */
void myreadbufsetup(int n){
    if(n >= 0 && n <= MAX_BUFFER) {
        buf_read_size = n;
        read_count = 0;
    }
}

int mygetc(void){
    if(read_count <= 0){
        read_count = read(fd_read,&buf_read,buf_read_size);
        rp = buf_read;
        if(read_count == 0)
            return -1;
    }
    rp++;
    read_count--;
    return (int)*(rp - 1);
                      
}



/* Main function starts */
int main()
{
    clock_t begin, end;
    int option, n, temp;
    const char *a="Writing byte by byte\n";
    const char *b="Writing using buffers\n";
    unsigned long i, bytes_to_write = BYTES_TO_READ_WRITE, bytes_to_read = BYTES_TO_READ_WRITE;
    char ch;

    while(1)
    {
        printf("\n 1 - Write without buffering \n 2 - Write with buffering");
        printf("\n 3 - Read without buffering \n 4 - Read with buffering");
        printf(("\n 5 - Exit \n Enter the option: "));
        scanf("%d", &option);
        switch(option)
        {
            case 1: /* Write without buffer */
                begin = clock();
                for (i=0;i<bytes_to_write;i++)
                {
                    ch = a[i%strlen(a)];
                    mywritec(ch);
                }
                end = clock();
                printf("\n Time to write without buffering: %f secs\n",(double)(end - begin)/CLOCKS_PER_SEC);
                break;

            case 2:  /* Write with buffer */
                printf("\n Enter the buffer size in bytes: ");
                scanf("%d", &n);
                mywritebufsetup(n);
                begin = clock();
                for (i=0;i<bytes_to_write;i++)
                {
                    ch = b[i%strlen(b)];
                    myputc(ch);
                }
                mywriteflush();
                end = clock();
                printf("\n Time to write with buffering: %f secs\n",(double)(end - begin)/CLOCKS_PER_SEC);
                break;

            case 3:  /* Read without buffer */
                fd_read = open(READABLE_FILE, O_RDONLY);
                if(fd_read < 0)
                {
                    printf("\n Error opening the readable file \n");
                    return 1;
                }
                begin = clock();
                for (i=0;i<bytes_to_read;i++)
                {  /* you may need to modify this slightly to print the character received and also check for end of file */
                    if(myreadc() == -1)
                    {
                        printf("\n End of file \n");
                        break;
                    }
                }
                end = clock();
                if(close(fd_read))
                {
                    printf("\n Error while closing the file \n ");
                }
                printf("\n Time to read without buffering: %f secs\n",(double)(end - begin)/CLOCKS_PER_SEC);
                break;

            case 4:  /* Read with buffer */
                printf("\n Enter the buffer size in bytes: ");
                scanf("%d", &n);
                myreadbufsetup(n);
                fd_read = open(READABLE_FILE, O_RDONLY);
                if(fd_read < 0)
                {
                    printf("\n Error opening the readable file \n");
                    return 1;
                }
                begin = clock();
                for (i=0;i<bytes_to_read;i++)
                { /* you may need to modify this slightly to print the character received and also check for end of file */
                    if(mygetc() == -1)
                    {
                        printf("\n End of file \n");
                        break;
                    }
                }
                end = clock();
                if(close(fd_read))
                {
                    printf("\n Error while closing the file \n ");
                }
                printf("\n Time to read with buffering: %f secs\n",(double)(end - begin)/CLOCKS_PER_SEC);
                break;

            default:
                return 0;
        }
    }
}