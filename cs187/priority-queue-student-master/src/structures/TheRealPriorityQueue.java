package structures;
import java.util.Comparator;
import java.util.Iterator;

import structures.PriorityQueue;
public class TheRealPriorityQueue<P, V> implements PriorityQueue<P,V>{
	private final Comparator<P> comp;
	private int size;
	private ArrayHeap<P, V> ah;
	public TheRealPriorityQueue(Comparator<P> comp){
		size = 0;
		if (comp == null)
			throw new NullPointerException();
		this.comp = comp;
		ah = new ArrayHeap<P, V>(comp);
	}
	/**
	 * Enqueues the specified {@code value} into this {@link PriorityQueue} with
	 * the specified {@code priority}. This runs in O(log(size)) time. For
	 * convenience this method returns the modified {@link PriorityQueue}.
	 * 
	 * @param priority
	 *            the priority of this enqueue
	 * @param value
	 *            the value being enqueued
	 * @return the modified {@link PriorityQueue}.
	 * @throws NullPointerException
	 *             if {@code prioirty} is {@code null} or {@code value} is
	 *             {@code null}.
	 */
	public PriorityQueue<P, V> enqueue(P priority, V value){
		if(priority == null || value == null) throw new NullPointerException();
		ah.add(priority, value);
		return this;
	}

	/**
	 * Removes the value with the lowest priority in this {@link PriorityQueue}
	 * and then returns it. This runs in O(log(size)) time.
	 * 
	 * @return the value with the lowest priority in this {@link PrioirtyQueue}
	 * @throws IllegalStateException
	 *             if this {@link PriorityQueue} is empty.
	 */
	public V dequeue(){
		return ah.remove();
	}

	/**
	 * Returns the value with the lowest priority in this {@link PriorityQueue}.
	 * 
	 * @return the value with the lowest priority in this {@link PriorityQueue}.
	 * @throws IllegalStateException
	 *             if this {@link PriorityQueue} is empty.
	 */
	public V peek(){
		return ah.peek();
	}

	/**
	 * Returns an {@link Iterator} over all of the entries in this
	 * {@link PriorityQueue}. The order of these elements is unspecified and a
	 * call to {@link Iterator#remove()} results in an
	 * {@link UnsupportedOperationException}.
	 * 
	 * @return an {@link Iterator} over all of the values in this
	 *         {@link PriorityQueue}.
	 */
	public Iterator<Entry<P, V>> iterator(){
		return ah.asList().iterator();
	}

	/**
	 * Returns the {@link Comparator} that is used to determine the ordering of
	 * {@link Entry}s in this {@link PriorityQueue}.
	 * 
	 * @return the {@link Comparator} that is used to determine the ordering of
	 *         {@link Entry}s in this {@link PriorityQueue}.
	 */
	public Comparator<P> getComparator(){
		return comp;
	}

	/**
	 * Returns the total number of elements in this {@link PriorityQueue}
	 * 
	 * @return the total number of elements in this {@link PriorityQueue}
	 */
	public int size(){
		return size;
	}

	/**
	 * Returns {@code true} if this {@link PrioirtyQueue} has no elements and
	 * {@code false} otherwise.
	 * 
	 * @return {@code true} if this {@link PrioirtyQueue} has no elements and
	 *         {@code false} otherwise.
	 */
	public boolean isEmpty(){
		return (size==0);
	}

}
