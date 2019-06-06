package config;

import java.util.Comparator;

import structures.ArrayHeap;
import structures.AbstractArrayHeap;
import structures.MaxQueue;
import structures.MinQueue;
import structures.PriorityQueue;

/**
 * The {@link Configuration} class is a set of static definitions we will use to
 * grade your assignment.
 * 
 * @author jcollard
 * 
 */
public class Configuration {

	/**
	 * Your 8 digit student ID number. This must be completed for Deadline #1
	 * and Deadline #2
	 */
	public static final String STUDENT_ID = "28950694";

	/**
	 * Given a {@link Comparator} returns the {@link AbstractArrayHeap} to be
	 * graded.
	 * 
	 * @param comparator
	 *            the comparator that will be used
	 * @return the {@link AbstractArrayHeap} implementation to be graded.
	 */
	public static <P, V> AbstractArrayHeap<P, V> getArrayHeap(Comparator<P> comparator) {
		return new ArrayHeap<P, V>(comparator);
	}

	/**
	 * Returns a {@link PriorityQueue} that uses integer values for priority and
	 * dequeues values who have the smallest priority.
	 * 
	 * @return a {@link PriorityQueue} that uses integer values for priority and
	 *         dequeues values who have the smallest priority.
	 */
	public static <V> PriorityQueue<Integer, V> getMinQueue() {
		return new MinQueue<V>();
	}

	/**
	 * Returns a {@link PrioirtyQueue} that uses integer values for priority and
	 * dequeues values who have the largest priority.
	 * 
	 * @return a {@link PrioirtyQueue} that uses integer values for priority and
	 *         dequeues values who have the largest priority.
	 */
	public static <V> PriorityQueue<Integer, V> getMaxQueue() {
		return new MaxQueue<V>();
	}

}
