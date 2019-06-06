package structures;
import java.util.Comparator;

import structures.AbstractArrayHeap;
public class ArrayHeap<P, V> extends AbstractArrayHeap<P, V>{
	public ArrayHeap(Comparator<P> comparator){
		super(comparator);
	}
	/**
	 * Given an index of some "node" returns the index to that "nodes" left
	 * child.
	 * 
	 * @param index
	 *            an index in this {@link AbstractArrayHeap}
	 * @return the index of the specified "nodes" left child
	 * @throws IndexOutOfBoundsException
	 *             if {@code index} is less than 0
	 */
	protected int getLeftChildOf(int index){
		if(index<0) throw new IndexOutOfBoundsException();
		return (2*index)+1;
	}

	/**
	 * Given an index of some "node" returns the index to that "nodes" right
	 * child.
	 * 
	 * @param index
	 *            a "nodes" index
	 * @return the index of the specified "nodes" right child
	 * @throws IndexOutOfBoundsException
	 *             if {@code index} is less than 0
	 */
	protected int getRightChildOf(int index){
		if(index<0) throw new IndexOutOfBoundsException();
		return (2*index)+2;
	}
	/**
	 * Given an index of some "node" returns the index to that "nodes" parent.
	 * 
	 * @param index
	 *            a "nodes" index
	 * @return the index of the specified "nodes" parent
	 * @throws IndexOutOfBoundsException
	 *             if {@code index} is less than 1
	 */
	protected int getParentOf(int index){
		if(index<=0) throw new IndexOutOfBoundsException();
		return (index-1)/2;	
	}

	/**
	 * <p>
	 * This results in the entry at the specified index "bubbling up" to a
	 * location such that the property of the heap are maintained. This method
	 * should run in O(log(size)) time.
	 * </p>
	 * <p>
	 * Note: When add is called, an Entry is placed at the end of the internal
	 * array and then this method is called on that index.
	 * </p>
	 * 
	 * @param index
	 *            the index to bubble up
	 */
	protected Entry<P, V> getValue(int index){
		return asList().get(index);		
	}
	
	protected void bubbleUp(int index){
		if(index==0) return; //cant bubbleUp the root
		int parent = getParentOf(index);
		if(getComparator().compare(getValue(index).getPriority(), getValue(parent).getPriority()) <= 0)  return; //they are in the right spot
		else swap(index, parent);
		bubbleUp(parent);  //parent and child values will be swaped keeping index so were reallying evaluating the original one in its new position
	}

	/**
	 * <p>
	 * This method results in the entry at the specified index "bubbling down"
	 * to a location such that the property of the heap are maintained. This
	 * method should run in O(log(size)) time.
	 * </p>
	 * <p>
	 * Note: When remove is called, if there are elements remaining in this
	 * {@link AbstractArrayHeap} the bottom most element of the heap is placed
	 * at the 0th index and bubbleDown(0) is called.
	 * </p>
	 * 
	 * @param index
	 */
	protected void bubbleDown(int index){
		int left = getLeftChildOf(index);
		int right = getRightChildOf(index);
		boolean done = false;
		if(left < size()){
			if(getComparator().compare(getValue(left).getPriority(), getValue(index).getPriority()) > 0) {
				//System.out.println("swaping " + getValue(index).getValue()+ " for " + getValue(left).getValue());
				swap(index, left);
				done = true;
				if(getLeftChildOf(left) < size()) bubbleDown(left);
			}
		}
		if(!done){
			if(right < size()){
				if(getComparator().compare(getValue(right).getPriority(), getValue(index).getPriority()) <= 0) {
					//System.out.println("swaping " + getValue(index).getValue()+ " for " + getValue(left).getValue());
					swap(index, right); 
					if( getRightChildOf(right) < size()) bubbleDown(right);
				}
			}
		}
		return;
	}
}
