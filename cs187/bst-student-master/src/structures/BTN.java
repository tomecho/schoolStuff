package structures;

public class BTN<T> implements BinaryTreeNode<T>{
	BinaryTreeNode<T> leftChild;
	BinaryTreeNode<T> rightChild;
	private T data;
	
	public BTN(T data){
		if(data == null) throw new NullPointerException();
		this.data = data;
		leftChild=null;
		rightChild=null;
	}
	@Override
	public T getData() {
		return data;
	}

	@Override
	public void setData(T data) {
		if(data == null) throw new NullPointerException();
		this.data = data;
	}

	@Override
	public boolean hasLeftChild() {
		return (leftChild != null);
	}

	@Override
	public boolean hasRightChild() {
		return (rightChild != null);
	}

	@Override
	public BinaryTreeNode<T> getLeftChild() {
		if(!hasLeftChild()) throw new IllegalStateException();
		return leftChild;
	}

	@Override
	public BinaryTreeNode<T> getRightChild() {
		if(!hasRightChild()) throw new IllegalStateException();
		return rightChild;
	}

	@Override
	public void setLeftChild(BinaryTreeNode<T> left) {
		leftChild = left;
	}

	@Override
	public void setRightChild(BinaryTreeNode<T> right) {
		rightChild = right;
	}

}
