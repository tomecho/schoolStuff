#include<stdio.h>
#include<unistd.h>
#include "sys/wait.h"
#include<iostream>
using namespace std;
int main(int i)
{
  i = 4000;
  if(i < 1) return 1;
  int count=0;
  while(count < i)
  {
    int rc = fork();
    count++;
    if(rc > 0) //parent does this
    {
      waitpid(rc, NULL, WNOHANG|WUNTRACED); //wait for child process
      cout << "I am descendent " << count << "\n";
      return 0;
    }
  }
  return 0;
}
