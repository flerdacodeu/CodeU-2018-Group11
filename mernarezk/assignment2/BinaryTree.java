package Assignment2;

/**
 * Simple Binary Tree class that takes int values since BOTH problems are
 * defined by taking in values and for testing simplicity methods params in
 * assignments take direct int's rather than node references.
 */
public class BinaryTree {
	TreeNode root;

	/**
	 * For testing and demonstration purposes the isnertion method happens int the
	 * Binary Search tree because it's more imagineable how the tree structure will
	 * look like.
	 * 
	 * @param val
	 *            to be inserted
	 */
	public void insert(int val) {
		TreeNode newNode = new TreeNode(val);
		if (root == null) {
			root = newNode;
			return;
		}
		TreeNode current = root;
		while (true) {
			if (current.val < val) {
				if (current.right == null) {
					current.right = newNode;
					return;
				}
				current = current.right;
			} else {
				if (current.left == null) {
					current.left = newNode;
					return;
				}
				current = current.left;
			}
		}
	}

	/**
	 * Traverse whole tree in an inorder fashion to verify insertions were correct
	 * 
	 * @param root
	 *            of the tree
	 */
	public void inOrder(TreeNode root) {

		if (root != null) {
			inOrder(root.left);
			System.out.println(root.val);
			inOrder(root.right);
		}
	}
}
