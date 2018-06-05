package assignment_2;

import java.util.ArrayDeque;
import java.util.Queue;

public class BinaryTree {
	private Node head;

	public BinaryTree(int headValue) {
		head = new Node(headValue);
	}

	public Node getBinaryTree() {
		return head;
	}

	/**
	 * @param node
	 *            to be added assuming the binary tree is complete we find the
	 *            right place using bfs and then add the new node
	 */
	public void addNode(Node node) {
		Queue<Node> queue = new ArrayDeque<>();
		boolean isLeft = false;
		boolean isRight = false;
		Node current = null;
		queue.add(head);
		while (!queue.isEmpty()) {
			current = queue.poll();
			if (current.getLeft() != null) {
				queue.add(current.getLeft());
			} else {
				isLeft = true;
				break;
			}
			if (current.getRight() != null) {
				queue.add(current.getRight());
			} else {
				isRight = true;
				break;
			}
		}
		if (isLeft)
			current.setLeft(node);
		if (isRight)
			current.setRight(node);

	}

	public void print(Node node) {
		if (node != null) {
			print(node.getLeft());
			print(node.getRight());
			System.out.print(node.getValue());
		}
	}

}
