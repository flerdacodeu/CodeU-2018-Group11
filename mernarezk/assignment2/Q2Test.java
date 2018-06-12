package Assignment2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Q2Test {
	Q2 tester = new Q2();
	TreeNode result;

	@Test
	void testCommonAncestor() {

		BinaryTree tree = new BinaryTree();
		int inorder[] = new int[] { 4, 2, 5, 1, 6, 3 };
		int preorder[] = new int[] { 1, 2, 4, 5, 3, 6 };
		tree.constructTree(preorder, inorder);
		result = tester.commonAncestor(tree.root, 4, 5);
		assertEquals(result.val, 2);
		 result = tester.commonAncestor(tree.root, 6, 8);
		 assertEquals(result.val, 7);
	}

}
