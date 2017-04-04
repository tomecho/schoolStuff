# Once you have written your version of the shell, write a design document that documents your design choices here.

#  Describe your design and design decisions here

## BoundedBuffer
Using slides from the lecture I implemented my bounded buffer using 1 lock and two condition variables (one for empty buffer and one for full buffer)
I also wrote a function to print the buffer which is useful for debugging

## ProducerConsumer
In the initializer I load up all the global variables, initialize the mutex's and then start the threads then join them.
Producer first checks if it has created enough items, if not it sleeps then enters a protected area, generates a value, enters another protected area for writing to the file, does the file operation then loops (first closing the mutex's). Upon exiting the look it will reach a thread kill.
Consumer checks if the buffer has items or if there are still items to be created, then it sleeps for the desired time, enters a protected section, removes something from the bounded buffer, enters the write protection then writes and loops (closing both protected sections). If the condition is false the consumer quits.

# Describe the behavior of your program for the three input scenarios here.

1. The producer thread and consumer thread alternate consuming and producing.
2. The producer thread doesnt have to sleep as much and produces a bunch of items before consumer can start consuming, it only catches up after the producer finishes.
3. Sometimes two producers will produce before a single consumer can consume but this changes between runs because of the dynamic nature of multithreading.  The mutex's make sure nothing colides.

# Briefly describe you test case and testing efforts here

I wrote two test cases one for testing not empty and another one for testing a lifecycle of a bounded buffer, adding items and removing items.  The lifecycle test also tested adding a couple, removing one and adding another.


# Briefly describe your debugging efforts here

Using cout mostly I see which parts of the program I got to.  I did use gdb and set one breakpoint but i seemed a little silly when my cout statements were much faster and I didnt need advanced gdb functions
