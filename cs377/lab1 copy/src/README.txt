# Once you have written your version of the shell, write a design document that documents your design choices here.

### void simple_shell::parse_command(char *cmd, char **cmdTokens) ###
first removes the \n at the end of the line
then tokenizes cmd into the array of strings cmdTokens

### void simple_shell::exec_command(char **argv) ###
execute the command, clear the cmdTokens, and wait until child is done, then clean it up and return

### bool simple_shell::isQuit(char *cmd) ###
returns true if the command is "quit"
