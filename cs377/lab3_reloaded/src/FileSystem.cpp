#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fstream>

using namespace std;

//block size = 1KB
#define block_size 1024

//structure for the inode
struct idxNode
{
  char name[8]; //file name
  int size;     // file size (in number of blocks)
  int blockPointers[8]; // direct block pointers
  int used;             // 0 => inode is free; 1 => in use
};

class myFileSystem
{
  private:
    fstream disk;
    char *bitmap;

    void read_blocks() {
      //disk.clear();
      disk.seekg(0, ios::beg);
      char buff[128];
      disk.read(buff, 128);
      //for(int i=0;i<128;i++)
        //printf("%d",buff[i]);
      bitmap = buff;
    }

    void print_blocks() {
      read_blocks();

      //for(int i=0;i<128;i++)
        //printf("%d\n",bitmap[i]);
    }
  public:

    myFileSystem(char diskName[16])
    {
      // open the file with the above name
      // this file will act as the "disk" for your file system
      disk.open(diskName);  //dunno if i should have this flag
      //print_blocks();
    }

    int create_file(char name[8], int size)
    {

      //create a file with this name and this size

      // high level pseudo code for creating a new file

      // Step 1: check to see if we have sufficient free space on disk by
      // reading in the free block list. To do this:
      // move the file pointer to the start of the disk file

      disk.clear();
      disk.seekg(0, ios::beg);
      char buff[128];
      disk.read(buff, 128);
      for(int i=0;i<128;i++)
        printf("%d ",buff[i]);

      disk.clear();
      disk.seekg(0, ios::beg);

      // Read the first 128 bytes (the free/in-use block information)
      read_blocks(); //update free block list

      // Scan the list to make sure you have sufficient free blocks to
      // allocate a new file of this size
      int free_count=0;
      for(int i=0; i<128;i++){
        if(free_count >= size) break;
        if(bitmap[i] == 0) free_count++;
      }
      if(free_count < size) return 0; //couldnt do it
      //else we can and lets go on!

      // Step 2: we look  for a free inode om disk
      disk.clear();
      disk.seekg(128,ios::beg); //move to the inodes
      // Read in a inode

      struct idxNode free_node;
      int inode_index=-1;
      // check the "used" field to see if it is free
      // If not, repeat the above two steps until you find a free inode
      for(int i=0;i<16;i++){ //check each inode
        struct idxNode inode; //temp
        disk.read((char *) &inode.name, sizeof(inode.name)); //read each part of the struct
        disk.read((char *) &inode.size, sizeof(inode.size));
        disk.read((char *) &inode.blockPointers, sizeof(inode.blockPointers));
        disk.read((char *) &inode.used, sizeof(inode.used));
        if(inode.used == 0) { //try to use this one
          free_node = inode;
          inode_index=i;
          break;
        }
      }

      if(inode_index == -1) return 0; //couldnt find one
      //we have an inode if we get here

      // Set the "used" field to 1
      free_node.used = 1;
      // Copy the filename to the "name" field
      strcpy(free_node.name,name);
      // Copy the file size (in units of blocks) to the "size" field
      free_node.size = size;

      // Step 3: Allocate data blocks to the file
      // for(i=0;i<size;i++)
      int blocksSet = 0;
      for(int i=0;i<size;i++){
        // Scan the block list that you read in Step 1 for a free block
        for(int j=0;j<128;j++) { //find our open block
          if(bitmap[j] == 0) {
            //use this one
            // Once you find a free block, mark it as in-use (Set it to 1)
            bitmap[j] = 1;
            // Set the blockPointer[i] field in the inode to this block number.
            free_node.blockPointers[i] = j;
            blocksSet++;
            break; //reloop
          }
        }
      }

      if(blocksSet != size) return 0; //error

      // Step 4: Write out the inode and free block list to disk
      //  Move the file pointer to the start of the file
      disk.clear();
      disk.seekg(0,ios::beg);
      // Write out the 128 byte free block list
      disk.write(bitmap, 128);
      // Move the file pointer to the position on disk where this inode was stored
      disk.seekg(128+48*inode_index,ios::beg); //TODO make sure this is the write index
      // Write out the inode
      disk.write((char *) &free_node,sizeof(free_node));
      return 1;

    } // End Create



    int delete_file(char name[8])
    {
      // Delete the file with this name

      // Step 1: Locate the inode for this file
      // Move the file pointer to the 1st inode (129th byte)
      // Read in an inode
      // If the inode is free, repeat above step.
      // If the inode is in use, check if the "name" field in the
      //   inode matches the file we want to delete. If not, read the next
      //   inode and repeat

      // Step 2: free blocks of the file being deleted
      // Read in the 128 byte free block list (move file pointer to start
      //   of the disk and read in 128 bytes)
      // Free each block listed in the blockPointer fields as follows:
      // for(i=0;i< inode.size; i++)
      // freeblockList[ inode.blockPointer[i] ] = 0;

      // Step 3: mark inode as free
      // Set the "used" field to 0.

      // Step 4: Write out the inode and free block list to disk
      // Move the file pointer to the start of the file
      // Write out the 128 byte free block list
      // Move the file pointer to the position on disk where this inode was stored
      // Write out the inode

      return 0;
    } // End Delete


    int ls(void)
    {
      // List names of all files on disk

      // Step 1: read in each inode and print!
      // Move file pointer to the position of the 1st inode (129th byte)
      disk.clear();
      disk.seekg(129, ios::beg);
      for(int i=0; i<16; i++){

      }
      // for(i=0;i<16;i++)
      // REad in a inode
      // If the inode is in-use
      // print to list.txt the "name" and "size" fields from the inode
      // end for
      return 0;

    } // End ls

    int read(char name[8], int blockNum, char buf[1024])
    {

      // read this block from this file

      // Step 1: locate the inode for this file
      // Move file pointer to the position of the 1st inode (129th byte)
      disk.seekg(129, ios::beg);
      // Read in an inode
      // If the inode is in use, compare the "name" field with the above file
      // If the file names don't match, repeat

      // Step 2: Read in the specified block
      // Check that blockNum < inode.size, else flag an error
      // Get the disk address of the specified block
      // That is, addr = inode.blockPointer[blockNum]
      // Move the file pointer to the block location (i.e., to byte #
      //   addr*1024 in the file)

      // Read in the block => Read in 1024 bytes from this location
      //   into the buffer "buf"
      return 0;
    } // End read


    int write(char name[8], int blockNum, char buf[1024])
    {

      // write this block to this file

      // Step 1: locate the inode for this file
      // Move file pointer to the position of the 1st inode (129th byte)
      // Read in a inode
      // If the inode is in use, compare the "name" field with the above file
      // If the file names don't match, repeat

      // Step 2: Write to the specified block
      // Check that blockNum < inode.size, else flag an error
      // Get the disk address of the specified block
      // That is, addr = inode.blockPointer[blockNum]
      // Move the file pointer to the block location (i.e., byte # addr*1024)

      // Write the block! => Write 1024 bytes from the buffer "buff" to
      //   this location

      return 0;
    } // end write

    int close_disk()
    {
      // close the file(disk) opened in the constructor
      // this is to check the persistency of file system
      disk.close();
      return 0;
    }

};