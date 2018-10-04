The location of the bug is at line 87 in main.c
line 87: char buf[256] = {0};

Since the buffer can only store 256 characters, to cause buffer overflow,
I store strings that exceed the buffer size in total into the nyan_says object
so it will overrun the buffer's boundary

To fix this problem, we should check when the buffer is full, then reallocate the buffer
array into a larger allocated space. We can use realloc() function to initialize the buffer
and increase its size when the buffer is full.


