package assignment_2;

public class Q_01 {

	/**
	 * first we search the binary tree to get the node with specific key and
	 * then tracing this node back to the root to get its ancestors
	 * 
	 * @param root
	 *            of the binary tree
	 * @param k
	 *            key of node which we print its ancestors
	 */
	public void printAncestors(Node root, int k) {
		Node nodeK = searchTree(root, k);
		if (nodeK == null) {
			throw new Error("That key doesn't exist in the binary tree");
		}
		System.out.println("Ancestors of node " + k + " :");
		while (nodeK.getParent() != null) {
			nodeK = nodeK.getParent();
			System.out.print(nodeK.getValue() + " ");
		}
		System.out.println();

	}

	/**
	 * search the tree for a node, first we traverse the left subtree until we
	 * get null then we go to the right sub trees
	 * 
	 * @param node
	 *            is the root of binary tree
	 * @param k
	 *            key we want to search for
	 * @return the node with that key k
	 */
	public static Node searchTree(Node node, int k) {
		if (node == null)
			return null;
		if (node.getValue() == k)
			return node;
		Node found = searchTree(node.getLeft(), k);
		if (found != null)
			return found;
		else
			return searchTree(node.getRight(), k);

	}

}
