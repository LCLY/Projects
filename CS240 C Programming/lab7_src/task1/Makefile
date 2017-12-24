flags= -std=gnu99 -g -Wall -Werror
CC=  gcc
src= test_memcmp.c memcmp.c
Target=memcmp

all:$(src)
	$(CC) $(flags) -o $(Target) $(src)

clean:
	rm $(Target) *.out total.txt
