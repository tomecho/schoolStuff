// tests.cpp
#include "../src/BoundedBuffer.cpp"
#include <gtest/gtest.h>
#include <iostream>
#include <fstream>
#include <string>

//Test checking if bounded buffer is empty
TEST(PCTest, Test1) { 
    BoundedBuffer *BB = new BoundedBuffer(5);
    EXPECT_TRUE(BB->isEmpty());
    
    delete BB;
}

//Test checking append() and remove() from buffer 
TEST(PCTest, Test2){
    BoundedBuffer *BB = new BoundedBuffer(5);
    BB->append(0);
    ASSERT_EQ(0,BB->remove());

    delete BB;
}

//test is empty
TEST(PCTest, Test4){
    BoundedBuffer *BB = new BoundedBuffer(5);
    BB->append(0);
    EXPECT_FALSE(BB->isEmpty());
    delete BB;
}

//test filled buffer
TEST(PCTest, Test3){
    BoundedBuffer *BB = new BoundedBuffer(3);
    cout << "append 0" << "\n";
    BB->append(0); //0
    cout << "append 1" << "\n";
    BB->append(1); //0 1
    cout << "append 2" << "\n";
    BB->append(2); //filled after this 0 1 2
    cout << "remove 0" << "\n";
    ASSERT_EQ(0,BB->remove());  // _ 1 2
    cout << "append 3" << "\n"; //head shifts here
    BB->append(3); //3 1 2 
    ASSERT_EQ(1,BB->remove()); //3 1 _
    ASSERT_EQ(2,BB->remove()); //3 _ _
    ASSERT_EQ(3,BB->remove()); //_ _ _

    delete BB;
}

//TODO: add test cases here

// TEST(test_case_name, test_name) {
//  ... test body ...
// }


int main(int argc, char **argv) {
    testing::InitGoogleTest(&argc, argv);
    return RUN_ALL_TESTS();
}
