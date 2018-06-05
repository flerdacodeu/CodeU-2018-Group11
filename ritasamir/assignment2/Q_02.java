package assignment_2;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class Q_02 {
	Q_01 ancestors;
	Stack<Node> stack;


	/**
	 * @param head
	 *            is the root of the binary tree
	 * @param key1
	 * @param key2
	 * @return node which represent the common ancestor for both key1 and key2
	 */
	public Node getCommonAncestor(Node head, int key1, int key2) {
		ancestors = new Q_01();
		ancestors.getAncestors(head, key1);
		stack = ancestors.getStackOfAncestors();
		while (!stack.isEmpty()) {
			Node node = stack.pop();
			if (isChildOf(node, key2)) {
				return node;
			}
		}
		return null;
	}

	/**
	 * check if the key is one of the node children
	 * 
	 * @param node
	 * @param key
	 * @return true if the key exist among the children of the node
	 */
	private boolean isChildOf(Node node, int key) {
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(node);
		while (!queue.isEmpty()) {
			node = queue.poll();
			if (node.getValue() == key)
				return true;
			if (node.getLeft() != null) {
				queue.add(node.getLeft());
			}
			if (node.getRight() != null) {
				queue.add(node.getRight());
			}
		}
		return false;

	}

}
