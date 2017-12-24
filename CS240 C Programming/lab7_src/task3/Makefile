flags= -std=gnu99 -g -Wall -Werror
CC=  gcc
src= test_lngchr.c lngchr.c
Target=lngchr

all:$(src)
	$(CC) $(flags) -o $(Target) $(src)

clean:
	rm $(Target) *.out total.txt
