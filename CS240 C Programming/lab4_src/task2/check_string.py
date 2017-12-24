with open('mystring.c', 'r') as inF:
    for line in inF:
        if 'string.h' in line and 'mystring.h' not in line:
            print("DO NOT USE ANY STRING FUNCTIONS OR YOU MAY LOSE POINTS")
            exit()
