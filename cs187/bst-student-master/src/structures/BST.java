package structures;
import java.util.Iterator;

import structures.InOrderIterator;
import structures.BinarySearchTree;
public class BST<T extends Comparable<? super T>> implements BinarySearchTree<T>{
	private int size;
	private BinaryTreeNode<T> root;
	public BST(){
		root = null;
		size = 0;
	}
	/**
	 * Adds {@code toAdd} to this {@link BinarySearchTree}. For convenience,
	 * this method returns the modified {@link BinarySearchTree}. This method
	 * runs in O(size) time. However, if this {@link BinarySearchTree} is
	 * balanced, this method runs in O(log(size)) time.
	 * 
	 * A successful call to this method will always result in size increasing by
	 * one.
	 * 
	 * @param toAdd
	 *            the element to add to this {@link BinarySearchTree}
	 * @return For convenience, this method returns the modified
	 *         {@link BinarySearchTree}.
	 * @throws NullPointerException
	 *             if {@code toAdd} is {@code null}
	 */
	public BinarySearchTree<T> add(T toAdd){
		if(toAdd == null) throw new NullPointerException();
		if(root == null) {
			root = new BTN<T>(toAdd);
			size++;
			return this;
		}
		BinaryTreeNode<T> next = root;
		while(next.hasLeftChild() || next.hasRightChild()){ //while next has children
			if(!next.hasRightChild() && toAdd.compareTo(next.getData()) > 0) break;
			if(!next.hasLeftChild() && toAdd.compareTo(next.getData()) <= 0) break;
			if(next.hasLeftChild()){
				if(toAdd.compareTo(next.getData()) <= 0) next = next.getLeftChild();
			}
			if(next.hasRightChild()){
				if(toAdd.compareTo(next.getData()) > 0) next = next.getRightChild();
			}
		}
		if(toAdd.compareTo(next.getData()) > 0) next.setRightChild(new BTN<T>(toAdd));
		if(toAdd.compareTo(next.getData()) <= 0) next.setLeftChild(new BTN<T>(toAdd));
		size++;
		return this;
	}

/**
	 * Searches this {@link BinarySearchTree} for {@code toFind} and returns
	 * {@code true} if there is at least one instance of {@code toFind} in this
	 * {@link BinarySearchTree} and {@code false} otherwise.  This method
	 * runs in O(size) time. However, if this {@link BinarySearchTree} is balanced,
	 * this method runs in O(log(size)) time.
	 * 
	 * @param toFind
	 *            the element to search for
	 * @return {@code true} if there is an instance of {@code toFind} in this
	 *         {@link BinarySearchTree} and {@code false otherwise.
	 * @throws NullPointerException if {@code toFind} is {@code null}
	 */
	public boolean contains(T toFind){
		if(toFind == null) throw new NullPointerException();
		if(size == 0) return false;
		if(root == null) return false;
		InOrderIterator<T> itr = new InOrderIterator<T>(root);
		while(itr.hasNext()){
			if(itr.next().compareTo(toFind) == 0) return true;
		}
		return false;
	}

	/**
	 * Removes {@code toRemove} from this {@link BinarySearchTree} if at least
	 * one element is considered comparably equivalent (compareTo(toRemove) ==
	 * 0). Returns {@code true} if this {@link BinarySearchTree} was modified
	 * and {@code false} otherwise. This method runs in O(size) time. However,
	 * if this {@link BinarySearchTree} is balanced, this method runs in
	 * O(log(size)) time.
	 * 
	 * @param toRemove
	 *            the element to be removed
	 * @return {@code true} if this {@link BinarySearchTree} was modified and
	 *         {@code false} otherwise
	 */
	
	private void addNode(BinaryTreeNode<T> node){
		T toAdd = node.getData();
		if(toAdd == null) throw new NullPointerException();
		if(root == null) {
			root = new BTN<T>(toAdd);
		}
		BinaryTreeNode<T> next = root;
		while(next.hasLeftChild() || next.hasRightChild()){ //while next has children
			if(!next.hasRightChild() && toAdd.compareTo(next.getData()) > 0) break;
			if(!next.hasLeftChild() && toAdd.compareTo(next.getData()) < 0) break;
			if(next.hasLeftChild()){
				if(toAdd.compareTo(next.getData()) <= 0) next = next.getLeftChild();
			}
			if(next.hasRightChild()){
				if(toAdd.compareTo(next.getData()) > 0) next = next.getRightChild();
			}
		}
		if(toAdd.compareTo(next.getData()) > 0) next.setRightChild(node);
		if(toAdd.compareTo(next.getData()) <= 0) next.setLeftChild(node);
	}
	public boolean remove(T toRemove){
		if(toRemove == null) return true;
		if(root == null) {
			return false;
		}
		if(!contains(toRemove)) {
			return false;
		}
		System.out.println(size);
		BinaryTreeNode<T> next = root;
		while(next.getData() != toRemove){
			if(toRemove.compareTo(next.getData()) <= 0) next = next.getLeftChild();
			else if(toRemove.compareTo(next.getData()) > 0) next = next.getRightChild();
			else break;
		}
		if(next == root) root = null;
		if(next.hasLeftChild()) addNode(next.getLeftChild());
		if(next.hasRightChild()) addNode(next.getRightChild());
		next = null;
		size--;
		return true;
	}

	/**
	 * Returns the number of elements in this {@link BinarySearchTree}
	 * 
	 * @return the number of elements in this {@link BinarySearchTree}
	 */
	public int size(){
		return size;
	}

	/**
	 * Returns {@code true} if this {@link BinarySearchTree} contains no
	 * elements and {@code false} otherwise.
	 * 
	 * @return {@code true} if this {@link BinarySearchTree} contains no
	 *         elements and {@code false} otherwise.
	 */
	public boolean isEmpty(){
		return (size==0);
	}

	/**
	 * Returns the minimum value in this {@link BinarySearchTree}.
	 * 
	 * @return the minimum value in this {@link BinarySearchTree}.
	 * @throws IllegalStateException
	 *             if this {@link BinarySearchTree} is empty.
	 */
	public T getMinimum(){
		if(root == null) throw new IllegalStateException();
		BinaryTreeNode<T> temp = root;
		while(temp.hasLeftChild()){ //gets furthest left
			temp = temp.getLeftChild();
		}
		return temp.getData();
	}

	/**
	 * Returns the maximum value in this {@link BinarySearchTree}.
	 * 
	 * @return the maximum value in this {@link BinarySearchTree}.
	 * @throws IllegalStateException
	 *             if this {@link BinarySearchTree} is empty.
	 */
	public T getMaximum(){
		if(root == null) throw new IllegalStateException();
		BinaryTreeNode<T> temp = root;
		while(temp.hasRightChild()){ //gets furthest left
			temp = temp.getRightChild();
		}
		return temp.getData();
	}

	/**
	 * Returns a {@link BinaryTreeNode} that is consistent with the internal
	 * shape of this {@link BinarySearchTree}. If changes to the this {@link BinarySearchTree}
	 * are made after a call to this method, using the returned {@link BinaryTreeNode} is
	 * undefined.
	 * 
	 * @return a {@link BinaryTreeNode} that is consistent with the shape of
	 *         this {@link BinarySearchTree}.
	 * 
	 * @throws IllegalStateException
	 *             if this {@link BinarySearchTree} is empty.
	 */
	public BinaryTreeNode<T> toBinaryTreeNode(){
		if(root == null) throw new IllegalStateException();
		return root;
	}

	/**
	 * <p>
	 * Returns a new {@link Iterator} that will traverse this tree in-order.
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
	 * @return a new {@link Iterator} that will traverse this tree in-order.
	 */
	public Iterator<T> iterator(){
		return new InOrderIterator<T>(root);
	}

}
