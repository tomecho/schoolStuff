#include "stdio.h"
#include "string.h"
int main(void) {
    // Disable stdout buffering
    setvbuf(stdout, NULL, _IONBF, 0);
    char cmd[] = "cmd arg1 arg2";
    char *cmdTokens[20];
    
    
      char *part = strtok(cmd, " ");
  int i=0;
  while(part != NULL)
  {
    cmdTokens[i] = part;
    part = strtok(NULL, " "); 
    i++;
  }



    for (i = 0; i < 3; ++i) 
        printf("%s\n", cmdTokens[i]);

    return 0;
}
