package config;

import structures.BTN;
import structures.BTU;
import structures.BST;
import structures.BinarySearchTree;
import structures.BinaryTreeNode;
import structures.BinaryTreeUtility;


/**
 * This class acts as a configuration file which tells the testing framework
 * which implementation you want us to use when we grade your assignment.
 * 
 * @author miklau
 * 
 */
public class Configuration {

	/**
	 * Your 8 digit University of Massachusetts Identification Number. This
	 * is the value on your UMass ID Card. We need this to associate your submission
	 * with your moodle account when we submit grades
	 */
	public static final String STUDENT_ID_NUMBER = "28950694";
	

	public static <T> BinaryTreeNode<T> createBinaryTreeNode(BinaryTreeNode<T> left, T elem, BinaryTreeNode<T> right){
		BTN<T> temp = new BTN<T>(elem);
		temp.setLeftChild(left);
		temp.setRightChild(right);
		return temp;
	}
	
	public static BinaryTreeUtility createBinaryTreeUtility(){
		return new BTU();
	}
	
	public static <T extends Comparable<? super T>> BinarySearchTree<T> createBinarySearchTree(){
		return new BST<T>();
	}
	

}
