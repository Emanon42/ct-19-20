/*gen:debug*/
/*gen:expect/*
address `cp` after malloc:
268697600

Perform *cp = 'q'
    (should not change address of cp)

address `cp` after assign:
268697600

value `*cp`:
q
/*gen:expect*/
#include "minic-stdlib.h"

void main() {
    char* cp;
    cp = (char*) mcmalloc(sizeof(char));
    *cp = 'q';
    print_c(*cp); // should be q
}
