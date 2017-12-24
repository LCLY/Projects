flags= -std=gnu99 -g -Wall -Werror -D_DEFAULT_SOURCE
CC=  gcc
src= *.c
Target= word_count

all:$(src)
	$(CC) $(flags) -o $(Target) $(src)

clean:
	rm $(Target) *.out total.txt
