#include <iostream>
using namespace std;

// defining the struct element for the linked list
struct LLNode
{
  int data;
  LLNode *next;
};

// defining the class for linked list        
class linkedlist{
  // the head and tail pointer to the linked list
  LLNode *list;
  LLNode *tail;

  public:
  // constructor
  linkedlist(){
    // the first element is a dummy head
    LLNode *head = (LLNode *)malloc(sizeof(LLNode));
    head->data = 0;
    head->next = NULL;
    list = head;
    tail = head;
  }

  // insert element x into the list
  void insert(int x)
  {
    //TODO: write code to insert element x to the end of the linkedlist
    LLNode *p = (LLNode *)malloc(sizeof(LLNode)); //make the new llnode
    p->data = x; //set the data
    p->next = NULL;
    if(tail != NULL)
    {
      tail->next = p; //set the tails next to our new node
      tail = p; //rereference the tail to our new node
    } else { //we can assume this is an empty list
      list = p; //this is the list!
      tail = p;
    }
    printf("inserting %d\n", x);
  }

  // remove the first occurrence of element x from the list
  void remove(int x)
  {
    // TODO : write code to remove element x from the list. Also print
    // printf("%d is NOT in the list\n",x); if x is not in the list
    
    if(list == NULL)
    { //there is no list 
      printf("%d is NOT in the list\n",x); //we couldnt find the node
    }
    LLNode *one = list; //one starts out at the first element
    LLNode *two = one->next; //two starts out at the second
    //this way allows one to follow the other, we need the previous node to deref the next

    ////first things first lets check one
    //if(one->data == x)
    //{
      ////how convenient! lets just clean up one and the set list to two.
      //list = two; 
      ////tail will still be set to two if its the only remaining element
      //if(tail == one) tail = NULL; //but if one is then we should deref it because that could create a strange issue where we keep inserting at end of a headless list
      //free(one); //make sure to clean up after ourselves!
      //printf("removing %d\n", x);
      //return;
    //}

    while(two != NULL){
      if(two->data == x){
        //remove then return
        if(two == tail) //need to move the tail as well
        {
          tail = one;
        }
        one->next = two->next; //remove two from the list, handles two being a tail because next will just be null
        free(two);
        printf("removing %d\n", x);
        return; //no reason to continue
      }
      //move these to the next node
      one = one->next;
      two = two->next;
    }
    printf("%d is NOT in the list\n",x); //we couldnt find the node
  }

  void print()
  {
    LLNode *p = list->next;

    while(p != NULL){
      cout << p->data << ", ";
      p = p->next;
    }

    cout << endl;
  }

};        

int main()
{
  linkedlist myList;

  cout << "sample tests for code:" << endl;
  myList.insert(2);
  myList.insert(1);
  myList.insert(3);
  myList.insert(4);
  myList.print(); //2,1,3,4
  myList.remove(3);
  myList.remove(1);
  myList.remove(1); //failed remove
  myList.remove(2);
  myList.remove(4);
  myList.remove(4); //failed remove
  myList.print(); // *empty*

  //TODO: write your own tests for the code
  myList.insert(1);
  myList.print(); //1
  myList.insert(2); 
  myList.remove(2);
  myList.remove(2); //failed remove 
  myList.remove(1);
  myList.remove(1); //failed remove
  myList.remove(2);//failed remove
  myList.print(); // *empty*
  myList.insert(1);
  myList.insert(1);
  myList.insert(1);
  myList.remove(1);
  myList.remove(1);
  myList.remove(1);
  myList.print(); //*empty*

  myList.insert(1);
  myList.insert(2);
  myList.insert(3);
  myList.insert(4);
  myList.insert(0);
  myList.print(); //1,2,3,4,0
  myList.remove(0);
  myList.print(); //1,2,3,4
  return 0;
}
