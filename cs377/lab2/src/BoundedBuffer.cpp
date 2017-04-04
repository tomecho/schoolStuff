#include "BoundedBuffer.h"

BoundedBuffer::BoundedBuffer(int N){
  //TODO: constructor to initiliaze all the varibales declared in BoundedBuffer.h 
  buff = new int[N];
  size = N;
  count = 0;
  wr_pnt = 0;
  rd_pnt = 0;
  pthread_mutex_init(&lock, NULL);
  pthread_cond_init(&full, NULL);
  pthread_cond_init(&empty, NULL);
}

void BoundedBuffer::append(int data){
  //TODO: append a data item to the circular buffer
  pthread_mutex_lock(&lock); //single process at once
    while(count==size)
      pthread_cond_wait(&empty,&lock); //wait for something to be removed
    buff[wr_pnt] = data; //write the thing
    wr_pnt = (wr_pnt+1) % size; //this will set it to pnt+1 or zero effectivly
    count++; //increment count
    myPrint(buff);
    pthread_cond_signal(&full); //let the remove thread knows its cool and can remove something now
  pthread_mutex_unlock(&lock); //let other write operations happen
}

int BoundedBuffer::remove(){
  //TODO: remove and return a data item from the circular buffer
  pthread_mutex_lock(&lock); //only one remove at a time
    while(count==0) //if its empty
      pthread_cond_wait(&full,&lock); //wait for something to be appended
    int item = buff[/*(wr_pnt-size)%size];*/rd_pnt];
    rd_pnt = (rd_pnt+1) % size;
    //int x = rd_pnt-count;
    //if(x < 0) x=count-pnt;
    //int item = buff[x % size]; //remove it
    count--; //there is one less item now
    pthread_cond_signal(&empty); //let the remove thread knows its cool and can add something now
  pthread_mutex_unlock(&lock); //now the other removers can start
  return item; //return our item
}

void BoundedBuffer::myPrint(int *buff){
  for(int i=0;i<3;i++)
    cout << buff[i] << "\n";
  cout << "\n";
}

bool BoundedBuffer::isEmpty(){
  //TODO: check is the buffer is empty
  return count==0;
}
