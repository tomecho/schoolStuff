package structures;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
public class PostOrderIterator<T> implements Iterator<T> {
	private final Deque<BinaryTreeNode<T>> stack;
	//private BinaryTreeNode<T> root;
	public PostOrderIterator(BinaryTreeNode<T> root){
		stack = new LinkedList<BinaryTreeNode<T>>();
		//BinaryTreeNode<T> temp = root;
		//this.root = root;
		loadStack(root);
		/*stack.push(temp); //put the root dead last
		while(temp.hasLeftChild()){ //we want furthest left out first
			temp = temp.getLeftChild();
			stack.push(temp);
		}*/
	}
	private void loadStack(BinaryTreeNode<T> current){
		if(current == null) return;//base case
		if(current.hasLeftChild()) loadStack(current.getLeftChild()); //load lefts
		if(current.hasRightChild()) loadStack(current.getRightChild()); //load rights
		stack.addLast(current);
	}
	@Override
	public boolean hasNext() {
		return !stack.isEmpty();
	}
	@Override
	public T next() {
		/*BinaryTreeNode<T> out = stack.peek(); //needs to be leftest deepest element that hasnt been called yet
		if(out == root) {
			return stack.pop().getData(); 
		}
		if(out.hasRightChild()){ //first next should never have any more left children
			BinaryTreeNode<T> temp = out.getRightChild(); //dont pop it because at some point it will be needed
			stack.push(temp);
			while(temp.hasLeftChild() || temp.hasRightChild()){
				if(temp.hasLeftChild()){
					temp = temp.getLeftChild();
					stack.push(temp);
				} else if(temp.hasRightChild()){ //dont load right if found left
					temp = temp.getRightChild();
					stack.push(temp);
				}
			}
		}
		return stack.pop().getData();*/
		return stack.pop().getData();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
