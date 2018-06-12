package Assignment2;

/**
 * Simple Binary Tree class that takes int values since BOTH problems are
 * defined by taking in values and for testing simplicity methods params in
 * assignments take direct int's rather than node references.
 */
public class BinaryTree {
	TreeNode root;
	/**
	 * Method that takes only preorder and inorder representation of the tree and constructs
	 * a Binary Tree of this.
	 * @param preorder representation of the tree
	 * @param inorder inorder representation of the tree
	 */
	public void constructTree(int preorder[], int inorder[]) {
		this.root = constructTreeHelper(0, 0, inorder.length - 1, preorder, inorder, null);
	}

	public TreeNode constructTreeHelper(int preStart, int inStart, int inEnd, int preorder[], int inorder[],
			TreeNode prev) {
		if (preStart > preorder.length - 1 || inStart > inEnd)
			return null;
		TreeNode root = new TreeNode(preorder[preStart], prev);
		int index = 0;
		for (int i = inStart; i <= inEnd; i++)
			if (inorder[i] == root.val)
				index = i;
		root.left = constructTreeHelper(preStart + 1, inStart, index - 1, preorder, inorder, root);
		root.right = constructTreeHelper(preStart + index - inStart + 1, index + 1, inEnd, preorder, inorder, root);
		return root;
	}
	/**
	 * Traverse whole tree in an inorder fashion to verify insertions were correct
	 * 
	 * @param root of the tree
	 */
	public void inOrder(TreeNode root) {

		if (root != null) {
			inOrder(root.left);
			System.out.println(root.val);
			inOrder(root.right);
		}
	}
}
