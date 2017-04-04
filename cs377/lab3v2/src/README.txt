# Once you have written your version of the shell, write a design document that documents your design choices here.

This lab was done in a group of 2.
1. Thomas Peck tapeck@umass.edu
2. Rafal Bielech rbielech@umass.edu

#  Describe your design and design decisions here



# Describe the behavior of your program for the three input scenarios here.






# Briefly describe you test case and testing efforts here
Two test cases were added on top of the existing test cases in the tests.cpp file to test the program's
ability to detect errors that occured. The first test case created a file, followed by deletion of a file in the
disk that not exists. Return value of -1 should be expected and was returned appropriately.
On the second test case, the closing function was checked. The file system was closed and then delete function was called but
since the file system was closed, the delete should result in -1 being returned.
Lastly, two files with the same name cannot be created as testing in the last test. The second create_file should return value of
-1 as that file was not successfully created.



# Briefly describe your debugging efforts here
Debugging was done through the use of GDB and Gradescope examination. Moreover, test cases were
utilized in order to see the print out of the disk's contents. GDB was used to purge the program
of segmentation errors that occurred during the course of the assignment. GDB was helpful to step through the
program and see the contents in memory.
