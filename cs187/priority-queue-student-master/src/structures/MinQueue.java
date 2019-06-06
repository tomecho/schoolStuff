package structures;
import comparators.MinIntegerComparator;
public class MinQueue<V> extends TheRealPriorityQueue<Integer, V>{
	public MinQueue(){
		super(new MinIntegerComparator());
	}
}
