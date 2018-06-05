package Assignment2;

public class Q2 {
	/**
	 * Returns reference to lowest common ancestor of p and q vals. This method
	 * assumes that there are no duplicates in the tree also assumes that both p and
	 * q are always present in the tree. if either p or q matches with current root
	 * val, then we return that this is our current LCA other wise we try to search
	 * for it in left subtree and right subtree if any of them return non-null
	 * answer and other returns null then current root is LCA.
	 * 
	 * @param root
	 * @param p
	 *            first value that we need its common ancestor.
	 * @param q
	 *            second value that we need its common ancestor.
	 * @return
	 */
	public static TreeNode commonAnscestor(TreeNode root, int p, int q) {
		if (root == null || p == root.val || q == root.val)
			return root;
		TreeNode left = commonAnscestor(root.left, p, q);
		TreeNode right = commonAnscestor(root.right, p, q);
		if (left != null && right != null)
			return root;
		return left == null ? right : left;
	}
}
