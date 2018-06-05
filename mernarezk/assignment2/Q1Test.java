package Assignment2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

class Q1Test {
	Q1 tester = new Q1();
	BinaryTree tree = new BinaryTree();

	@Test
	void testGetAncestors() {
		tree.insert(3);
		tree.insert(4);
		tree.insert(1);
		tree.insert(5);
		tree.insert(7);
		tree.insert(6);
		tree.insert(2);
		tree.insert(8);

		BinaryTree tree2 = new BinaryTree();
		tree2.insert(3);
		tree2.insert(4);
		tree2.insert(1);
		tree2.insert(5);
		tree2.insert(7);
		tree2.insert(6);
		tree2.insert(2);
		tree2.insert(8);
		List<Integer> l1 = tester.getAncestors(tree.root, 7);
		List<Integer> l2 = tester.getAncestors(tree2.root, 7);
		assertEquals(new HashSet<Integer>(l1), new HashSet<Integer>(l2));

	}

}
