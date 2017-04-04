#include "ProducerConsumer.h"

//TODO: add BoundedBuffer, locks and any global variables here 
BoundedBuffer *BB;
pthread_mutex_t prlock; //producer lock
pthread_mutex_t colock; //consumer lock
pthread_mutex_t wrlock; //write lock
int items_cnt = 0;
int items_des; //desired facebook
int prsleep; //sleep inervals
int cosleep;

void InitProducerConsumer(int p, int c, int psleep, int csleep, int items){
  //TODO: constructor to initialize variables declared
  //also see instruction for implementation
  if(p<1||c<1) { //check for invalid params
    cout << "the minimum threads is 1 \n";
    return;
  }

  //create our debug file
  fstream fs;
  fs.open("output.txt", ios::out);
  fs.close();

  //init a few things
  BB = new BoundedBuffer(items);
  items_des = items;
  prsleep = psleep;
  cosleep = csleep;
  srand(time(NULL)); //this will be used for producer

  //mutex init
  pthread_mutex_init(&prlock,NULL);
  pthread_mutex_init(&colock,NULL);
  pthread_mutex_init(&wrlock,NULL);

  pthread_t pThread[p];
  for(int i=0;i<p;i++){ //start producers
    pthread_create(&(pThread[i]), NULL, producer, (void*) pThread[i]); 
  }
  
  pthread_t cThread[c];
  for(int i=0;i<c;i++){ //start consumers
    pthread_create(&(cThread[i]), NULL, consumer, (void*) cThread[i]); 
  }

  //join all the theads
  for(int i=0;i<p;i++) pthread_join(pThread[i], NULL);
  for(int i=0;i<c;i++) pthread_join(cThread[i], NULL);
}


void *producer(void* threadID){
  //TODO: producer thread, see instruction for implementation
  while(items_cnt < items_des) { //produce a few items
    usleep(prsleep); //sleep for a while
    pthread_mutex_lock(&prlock); //single process at once
      if(items_cnt < items_des) { //check again
        int val = rand();
        BB->append(val);//add a random val
        pthread_mutex_lock(&wrlock); //single process at once
          FILE *fp;
          fp = fopen("output.txt", "a+");
          fprintf(fp, "Producer #%d, time = %lld, producing data item #%d, item value=%d \n",(int*) threadID,(long long) time(NULL),++items_cnt,val);
          fclose(fp);
        pthread_mutex_unlock(&wrlock); //let other operations happen
      } else {
        pthread_mutex_unlock(&prlock); //let other operations happen
        pthread_exit(NULL); //we're done!
      }
    pthread_mutex_unlock(&prlock); //let other operations happen
  }
  pthread_exit(NULL); //we're done!
}

void *consumer(void* threadID){
  //TODO: consumer thread, see instruction for implementation
  while(!BB->isEmpty() || items_cnt < items_des ) { //consume a few items
    usleep(cosleep); //sleep for a while
    pthread_mutex_lock(&colock); //single process at once
      if(!BB->isEmpty()) { //check again
        int val = BB->remove();
        pthread_mutex_lock(&wrlock); //single process at once
          FILE *fp;
          fp = fopen("output.txt", "a+");
          fprintf(fp, "Consumer #%d, time = %lld, consuming data item value=%d \n",(int*) threadID,(long long) time(NULL),val);//remove it and write to file
          fclose(fp);
        pthread_mutex_unlock(&wrlock); //let other operations happen
      }
    pthread_mutex_unlock(&colock); //let other operations happen
  }
  pthread_exit(NULL); //we're done!
}
