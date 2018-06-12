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
		int inorder[] = new int[] { 4, 2, 5, 1, 6, 3 };
		int preorder[] = new int[] { 1, 2, 4, 5, 3, 6 };
		tree.constructTree(preorder, inorder);

		BinaryTree tree2 = new BinaryTree();
		tree2.constructTree(preorder, inorder);
		List<Integer> l1 = tester.getAncestorsWithParent(tree.root, 6);
		List<Integer> l2 = tester.getAncestorsWithParent(tree2.root, 6);
		assertEquals(new HashSet<Integer>(l1), new HashSet<Integer>(l2));

	}

}
