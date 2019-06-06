package structures;

import comparators.IntegerComparator;

public class MaxQueue<V> extends TheRealPriorityQueue<Integer, V> {
	public MaxQueue(){
		super(new IntegerComparator());
	}
}
