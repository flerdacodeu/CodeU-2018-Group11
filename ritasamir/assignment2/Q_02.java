package assignment_2;

public class Q_02 {

	/**
	 * @param head
	 *            the root of the binary tree
	 * @param key1
	 * @param key2
	 * @return node which represent the common ancestor for both key1 and key2
	 */
	public Node getCommonAncestor(Node head, int key1, int key2) {
		Node node = Q_01.searchTree(head, key1);
		// checks if the first key dosen't exist then return null
		if (node == null) {
			return null;
		}
		// for every ancestor of the key1 from bottom to up,if key2 exist on
		// the subtree of this ancestor then it is the least common ancestor

		while (node.getParent() != null) {
			Node visitedChild = node;
			node = node.getParent();
			if (node.getValue() == key2) {
				return node;
			} else if (node.getLeft() != visitedChild
					&& Q_01.searchTree(node.getLeft(), key2) != null) {
				return node;
			} else if (node.getRight() != visitedChild
					&& Q_01.searchTree(node.getRight(), key2) != null) {
				return node;
			}
		}
		return null;
	}

}
