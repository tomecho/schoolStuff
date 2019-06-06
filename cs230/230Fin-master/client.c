#include <stdio.h>
#include "csapp.h"
#include <string.h>

int mul(int x, int y){
  return x*y;
}

int main(){
  int sockfd;
  char buff[255];
  //sockfd = open_clientfd("localhost", 1337);
  sockfd = open_clientfd("flamingo.jacelridge.com", 48579);
  if(sockfd) printf("connection established to flamingoridge\n");
  if(send(sockfd, "28950694", 8, 0)) //send spire
    printf("sent spire\n");
  else printf("error sending spire\n");
  if(recv(sockfd, buff, 255,0)) //recieve problem
    printf("recieved problem\n");
  else printf("error recieving problem\n");

  int x, y;
  sscanf(buff, "MUL %d %d", &x, &y);
  printf("recieved %d * %d \n", x,y);
  int len = sprintf(buff, "%d", mul(x,y));

  if(send(sockfd, buff, len, 0)) //send answer
    printf("sending answer %d\n", mul(x,y));
  else printf("failed to send answer\n");

  if(recv(sockfd, buff, 255,0)) //recieve verdict
    printf("verdict recieved: ");
  else printf("failed to recieve verdict\n");

  printf("%s\n", buff); //print verdict
  close(sockfd);
}
