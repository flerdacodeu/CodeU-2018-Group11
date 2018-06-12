package assignment_2;

public class Node {

	private Node left;
	private Node right;
	private Node parent;
	private int value;

	public Node(int value) {
		this.left = null;
		this.right = null;
		this.value = value;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public int getValue() {
		return value;
	}

	public void setParent(Node current) {
		this.parent = current;

	}

	public Node getParent() {
		return parent;
	}

}
