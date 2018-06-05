package Assignment2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Q2Test {
	Q2 tester = new Q2();
	TreeNode result;

	@Test
	void testCommonAncestor() {

		BinaryTree tree = new BinaryTree();
		tree.insert(3);
		tree.insert(4);
		tree.insert(1);
		tree.insert(5);
		tree.insert(7);
		tree.insert(6);
		tree.insert(2);
		tree.insert(8);
		result = tester.commonAnscestor(tree.root, 5, 2);
		assertEquals(result.val, 3);
		result = tester.commonAnscestor(tree.root, 6, 8);
		assertEquals(result.val, 7);
	}

}
