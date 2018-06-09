package assignment_2;

import java.util.ArrayDeque;
import java.util.Queue;

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
	 * search the tree for a node using bfs
	 * 
	 * @param node
	 *            is the root of binary tree
	 * @param k
	 *            key we want to search for
	 * @return the node with that key k
	 */
	public Node searchTree(Node node, int k) {
		Queue<Node> queue = new ArrayDeque<>();
		Node current = null;
		queue.add(node);
		while (!queue.isEmpty()) {
			current = queue.poll();
			if (current.getValue() == k)
				return current;
			if (current.getLeft() != null) {
				queue.add(current.getLeft());
			}
			if (current.getRight() != null) {
				queue.add(current.getRight());
			}
		}
		return null;
	}

}
