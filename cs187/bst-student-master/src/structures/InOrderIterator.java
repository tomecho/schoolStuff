package structures;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
public class InOrderIterator<T> implements Iterator<T> {
	
	private final Deque<BinaryTreeNode<T>> stack;
	
	public InOrderIterator(BinaryTreeNode<T> root){
		stack = new LinkedList<BinaryTreeNode<T>>();
		BinaryTreeNode<T> temp = root;
		stack.push(temp);
		while(temp.hasLeftChild()){ //gets furthest left
			temp = temp.getLeftChild();
			stack.push(temp);
		}
	}
	@Override
	public boolean hasNext() {
		return !stack.isEmpty();
	}
	
	//look at stack top if it has children go down as left as possible next should be up one and then the right 
	@Override
	public T next() {
		BinaryTreeNode<T> out = stack.pop(); //at first will be leftest
		if(out.hasRightChild()){//for cases when it is a parent\
			BinaryTreeNode<T> temp = out.getRightChild();
			stack.push(temp);
			while(temp.hasLeftChild()){ //gets furthest left
				temp = temp.getLeftChild();
				stack.push(temp);
			}
		}
		return out.getData();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
