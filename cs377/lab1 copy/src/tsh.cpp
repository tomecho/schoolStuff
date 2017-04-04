#include "tsh.h"
#include <signal.h>
#include<string.h>
#include <unistd.h>
using namespace std;

void simple_shell::parse_command(char *cmd, char **cmdTokens) {
  // TODO: tokenize the command string into arguments
  
  //first trim off the \n from fgets
  int nl = strlen(cmd)-1;
  if(nl>0 && cmd[nl] == '\n')
    cmd[nl] = '\0';

  //now tokenize that string
  char *part = strtok(cmd, " ");
  int i=0;
  while(part != NULL)
  {
    cmdTokens[i] = part;
    part = strtok(NULL, " "); 
    i++;
  }
  cmd = NULL; //clear cmd
}

void simple_shell::exec_command(char **argv)
{
  // TODO: fork a child process to execute the command. 
  //		 parent process should wait for the child process to complete and reap it
  int pid = fork(); //create a child
  if(pid == 0) //child
  {
    execvp(argv[0], argv); //execute that program
  }
  else
  {
    for(int i=0;i<20;i++) //reset array of args
      argv[i] = NULL;
    sleep(1); //wait for child to start
    waitpid(pid, NULL, WUNTRACED|WNOHANG); //wait for child to hand or die
    kill(pid, SIGKILL); //filicide it
  }
}

bool simple_shell::isQuit(char *cmd){
  // TODO: check for the command "quit" that terminates the shell
  return strcmp(cmd,"quit") == 0; //compare command with quit
}
