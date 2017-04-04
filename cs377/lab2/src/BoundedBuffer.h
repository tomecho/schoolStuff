#ifndef _BOUNDEDBUFFER_H
#define _BOUNDEDBUFFER_H

#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <iostream>

using namespace std;

class BoundedBuffer {
  // DO NOT modify public function names
  public:
    BoundedBuffer(int N);
    void append(int data);
    int remove();
    bool isEmpty();
  private:
    //TODO: add any variables needed including buffer array, locks and conditional variables
    int *buff;
    int size,rd_pnt,wr_pnt,count;
    pthread_mutex_t lock;
    pthread_cond_t full,empty;
    void myPrint(int *buff);
};

#endif
