package Assignment2;

/**
 * simple TreeNode class that takes only int values for simplicity and as both
 * problems defined for int values.
 * 
 * @author Merna
 *
 */
public class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode parent;

	public TreeNode(int val, TreeNode parent) {
		this.val = val;
		this.left = null;
		this.right = null;
		this.parent = parent;
	}
}
