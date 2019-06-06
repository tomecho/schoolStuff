package structures;
import structures.BinaryTreeNode;
import structures.BinaryTreeUtility;

import java.util.Iterator;

public class BTU implements BinaryTreeUtility {
	/**
	 * <p>
	 * Creates a new {@link Iterator} that traverses the specified
	 * {@link BinaryTreeNode} in pre-order.
	 * </p>
	 * <p>
	 * The remove method of the returned {@link Iterator} is not supported and
	 * any call to it will result in an {@link UnsupportedOperationException}.
	 * </p>
	 * <p>
	 * This method is guaranteed to return in O(1) time. That is, it should not
	 * compute the entire order of traversal ahead of time.
	 * </p>
	 * <p>
	 * If the data in the underlying {@link BinaryTreeNode} or its children is
	 * modified, using the {@link Iterator} is unknown.
	 * </p>
	 * 
	 * @param root
	 *            the root {@link BinaryTreeNode} to use for this
	 *            {@link Iterator}
	 * @return a new {@link Iterator} that traverses the specified
	 *         {@link BinaryTreeNode} in pre-order.
	 * @throws NullPointerException
	 *             if root is {@code null}
	 * @see http://en.wikipedia.org/wiki/Tree_traversal#Pre-order
	 */
	public <T> Iterator<T> getPreOrderIterator(BinaryTreeNode<T> root){
		if(root == null) throw new NullPointerException();
		return new PreOrderIterator<T>(root);
	}

	/**
	 * <p>
	 * Creates a new {@link Iterator} that traverses the specified
	 * {@link BinaryTreeNode} in in-order.
	 * </p>
	 * <p>
	 * The remove method of the returned {@link Iterator} is not supported and
	 * any call to it will result in an {@link UnsupportedOperationException}.
	 * </p>
	 * <p>
	 * This method is guaranteed to return in O(1) time. That is, it should not
	 * compute the entire order of traversal ahead of time.
	 * </p>
	 * <p>
	 * If the data in the underlying {@link BinaryTreeNode} or its children is
	 * modified, using the {@link Iterator} is unknown.
	 * </p>
	 * 
	 * @param root
	 *            the root {@link BinaryTreeNode} to use for this
	 *            {@link Iterator}
	 * @return a new {@link Iterator} that traverses the specified
	 *         {@link BinaryTreeNode} in in-order.
	 * @throws NullPointerException
	 *             if root is {@code null}
	 * @see http://en.wikipedia.org/wiki/Tree_traversal#In-order_.28symmetric.29
	 */
	public <T> Iterator<T> getInOrderIterator(BinaryTreeNode<T> root){
		if(root == null) throw new NullPointerException();
		return new InOrderIterator<T>(root);
	}

	/**
	 * <p>
	 * Creates a new {@link Iterator} that traverses the specified
	 * {@link BinaryTreeNode} in post-order.
	 * </p>
	 * <p>
	 * The remove method of the returned {@link Iterator} is not supported and
	 * any call to it will result in an {@link UnsupportedOperationException}.
	 * </p>
	 * <p>
	 * This method is guaranteed to return in O(1) time. That is, it should not
	 * compute the entire order of traversal ahead of time.
	 * </p>
	 * <p>
	 * If the data in the underlying {@link BinaryTreeNode} or its children is
	 * modified, using the {@link Iterator} is unknown.
	 * </p>
	 * 
	 * @param root
	 *            the root {@link BinaryTreeNode} to use for this
	 *            {@link Iterator}
	 * @return a new {@link Iterator} that traverses the specified
	 *         {@link BinaryTreeNode} in post-order.
	 * @throws NullPointerException
	 *             if root is {@code null}
	 * @see http://en.wikipedia.org/wiki/Tree_traversal#Post-order
	 */
	public <T> Iterator<T> getPostOrderIterator(BinaryTreeNode<T> root){
		if(root == null) throw new NullPointerException();
		return new PostOrderIterator<T>(root);
	}

	/**
	 * <p>
	 * Given a {@link BinaryTreeNode} computes the maximum distance that can be
	 * traveled from {@code root} to some child {@link BinaryTreeNode}. For
	 * example:
	 * </p>
	 * 
	 * <pre>
	 * noChildren ->  A
	 * 
	 * getDepth(noChildren); // returns 0
	 * </pre>
	 * 
	 * <pre>
	 * someChildren ->  A
	 *                /   \ 
	 *               B     C <- depth 1
	 *                    / 
	 *                   D   <- depth 2
	 *                  / \
	 *                 E   F <- depth 3
	 *                 
	 * getDepth(someChildren); // returns 3
	 * </pre>
	 * 
	 * @param root
	 *            the root {@link BinaryTreeNode} to examine
	 * @throws NullPointerException
	 *             if root is {@code null}
	 * @return the maximum distance that can be traveled from {@code root}
	 */
	public <T> int getDepth(BinaryTreeNode<T> root){
		if(root == null) throw new NullPointerException();
		if(!(root.hasLeftChild() || root.hasRightChild())) return 0; // base case, no more children
		int left = 0, right = 0;
		if(root.hasLeftChild()) left = 1 + getDepth(root.getLeftChild());
		if(root.hasRightChild()) right = 1 + getDepth(root.getRightChild());
		return (left > right) ? left : right; //will return sub tree with furthest depth
	}

	/**
	 * A binary tree is said to be balanced if for each node in the tree the
	 * depth of each nodes children differs by at most some tolerance. Given a
	 * {@link BinaryTreeNode} and a tolerance, returns {@code true} if
	 * {@code root} is balanced and {@code false} otherwise.
	 * 
	 * @param root
	 *            the root {@link BinaryTreeNode} to examine
	 * @param int the maximum tolerance to decide if the tree is balanced
	 * @return {@code true} if {@code root} is balanced and {@code false}
	 *         otherwise.
	 * @throws NullPointerException
	 *             if {@code root} is {@code null}
	 * @throws IllegalArgumentException
	 *             if {@code tolerance} is less than 0
	 */
	public <T> int getShort(BinaryTreeNode<T> root){
		if(root == null) throw new NullPointerException();
		if(!(root.hasLeftChild() || root.hasRightChild())) return 0; // base case, no more children
		int left = 0, right = 0;
		if(root.hasLeftChild()) left = 1 + getDepth(root.getLeftChild());
		if(root.hasRightChild()) right = 1 + getDepth(root.getRightChild());
		return (left > right) ? right : left; //will return sub tree with furthest depth
	}
	
	public <T> boolean isBalanced(BinaryTreeNode<T> root, int tolerance){
		if(root == null) throw new NullPointerException();
		if(tolerance < 0) throw new IllegalArgumentException();
		if(!(root.hasLeftChild() || root.hasRightChild())) return true; //no more children
		
		if(root.hasLeftChild() && root.hasRightChild()){ //return false if either sub tree is not balanced
			if(!isBalanced(root.getRightChild(), tolerance)) return false;
			if(!isBalanced(root.getLeftChild(), tolerance)) return false;
		} else if(root.hasLeftChild() && !root.hasRightChild()){ //following if blocks are for branches that go off alone
			//System.out.println("called " + root.getData());
			if (getDepth(root.getLeftChild()) > tolerance-1) return false;
			//return isBalanced(root.getLeftChild(), tolerance-1);
		} else if(root.hasRightChild() && !root.hasLeftChild()){
			//System.out.println("called " + root.getData());
			if (getDepth(root.getRightChild()) > tolerance-1) return false;
			//return isBalanced(root.getRightChild(), tolerance-1);
		}
		return true;
	}

	/**
	 * <p>
	 * A {@link BinaryTreeNode} is said to be a Binary Search Tree if all of the
	 * following properties are true:
	 * <li>The left subtree of a node contains only nodes with values less than
	 * or equal to the value stored in {@code root}.</li>
	 * <li>The right subtree of a node contains only nodes with values that are
	 * greater than the value stored in {@code root}.</li>
	 * <li>The left and right subtree each must also be a binary search tree.</li>
	 * </p>
	 * <p>
	 * Given a {@link BinaryTreeNode}, returns {@code true} if {@code root} is a
	 * Binary Search Tree and {@code false} otherwise.
	 * </p>
	 * T
	 * @param root
	 *            the {@link BinaryTreeNode} to examine
	 * @return {@code true} if {@code root} is a Binary Search Tree and
	 *         {@code false} otherwise.
	 * @throws NullPointerException
	 *             if {@code root} is {@code null}
	 */
	public <T extends Comparable<? super T>> boolean isBST(BinaryTreeNode<T> root){
		if(!(root.hasLeftChild() || root.hasRightChild())) return true; //no more children
		if(root.hasLeftChild() && root.hasRightChild()){
			if(root.getLeftChild().getData().compareTo(root.getRightChild().getData()) > 0) return false;
		}
		boolean left= true, right = true;
		if(root.hasLeftChild()) {
			if(root.getData().compareTo(root.getLeftChild().getData()) < 0) return false;
			left = isBST(root.getLeftChild());
		}
		if(root.hasRightChild()) {
			if(root.getData().compareTo(root.getRightChild().getData()) > 0) return false;
			right = isBST(root.getRightChild());
		}
		if(!left || !right) return false;
		else return true;
	}
}
