// tests.cpp
#include "../src/FileSystem.cpp"
#include <gtest/gtest.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fstream>

using namespace std;

//Sample test cases:

TEST(FSTest, create_disk) {
  myFileSystem f ((char*) "disk0");
  f.close_disk();
}

TEST(FSTest, create_big_file_test) {
  myFileSystem f ((char*) "disk0");
  int code = f.create_file((char*)"test1.c",25);
  f.close_disk();
  ASSERT_EQ(1,code);
}

//test create_file return code 1 for success
TEST(FSTest, create_file_test) {
  myFileSystem f ((char*) "disk0");
  int code = f.create_file((char*)"testy.c",1);
  f.close_disk();
  ASSERT_EQ(1,code);
}

//test delete_file return code -1 for failure
TEST(FSTest, delete_file_test) {
  myFileSystem f ((char*) "disk1");
  int code = f.delete_file((char*)"test.c");
  f.close_disk();
  ASSERT_EQ(-1,code);
}

//TODO: add test cases here


// TEST(test_case_name, test_name) {
//  ... test body ...
// }


int main(int argc, char **argv) {
  testing::InitGoogleTest(&argc, argv);
  return RUN_ALL_TESTS();
}
