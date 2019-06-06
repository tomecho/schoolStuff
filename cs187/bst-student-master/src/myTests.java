import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;





import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import structures.BinarySearchTree;
import structures.BinaryTreeNode;
import structures.BinaryTreeUtility;
import config.Configuration;


public class myTests {
	private BinarySearchTree<Integer> tree;
	private static final int SPEED_TEST = 1 << 12;
	@Before
	public void setUp() throws Exception {
		tree = Configuration.createBinarySearchTree();
		assertNotNull("It looks like you did not set createBinarySearchTree in your configuration file.", tree);
	}
	@Test (timeout = 1000)
	public void testRandomAddRemoveAndSize() {
		Random r = new Random(42);
		List<Integer> valuesAdded = new LinkedList<Integer>();
		
		for(int i = 0; i < SPEED_TEST; i++){
			assertEquals("Tree should have i elements in it.", i, tree.size());
			int next = r.nextInt(SPEED_TEST);
			//System.out.println(next);
			valuesAdded.add(next);
			assertEquals("Add should return tree for convenience.", tree, tree.add(next));
			assertTrue("After add, contains should return true.", tree.contains(next));
		}
		
		assertEquals(SPEED_TEST, tree.size());
		for(Integer i : valuesAdded){
			System.out.println(i);
			assertTrue("Could not remove previously added node.", tree.remove(i));

		}
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());		
	}
}
