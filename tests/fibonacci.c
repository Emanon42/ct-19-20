#include "minic-stdlib.h"
struct blaf{
    int blyat;
};

struct bla{
    int cyka;
    struct blaf sf;
};
int bla;

void main() {
  int n;
  int first;
  int second;
  int next;
  int c;
  int shadow;
  int bla;
  char t;
  int ssf[190];
  {
    int shadow;
    {
        char shadow;
        {
            char* shadow;
        }
    }
  }
  if(1){
    return wef
  }

  // read n from the standard input
  n = read_i();

  first = 0;
  second = 1;

  print_s((char*)"First ");
  print_i(n);
  print_s((char*)" terms of Fibonacci series are : ");

  c = 0;
  while (c < n) {
    if ( c <= 1 )
      next = c;
    else
      {
	next = first + second;
	first = second;
	second = next;
      }
    print_i(next);
    print_s((char*)" ");
    c = c+1;
  }
}
