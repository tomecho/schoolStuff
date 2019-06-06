package structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import config.Configuration;

public class MinQueueTest {

	PriorityQueue<Integer, String> queue;
	
	@Before
	public void setup() {
		assertNotNull(
				"It looks like you forgot to set your min queue implementation in the configuration file.",
				Configuration.getMinQueue());
		queue = Configuration.getMinQueue();
	}

	@Test (timeout = 100)
	public void testQueue() {
		queue.enqueue(100, "Low priority");
		queue.enqueue(50, "Medium priority");
		queue.enqueue(25, "High priority");
		queue.enqueue(0, "Highest priority");
		assertEquals("Highest priority", queue.dequeue());
		assertEquals("High priority", queue.dequeue());
		assertEquals("Medium priority", queue.dequeue());
		assertEquals("Low priority", queue.dequeue());
	}

}
