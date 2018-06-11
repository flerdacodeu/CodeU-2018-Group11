package assignment2;

import java.util.ArrayList;

/***
 * Implements the node object for a BinaryTree. A BinaryTreeNode is represented by a key of generic type T, points to its parent node
 * and to its children. 
 * 
 * @author Sephora
 *
 * @param <T>
 */
public class BinaryTreeNode<T> {
	private final T key;
	private BinaryTreeNode<T> parent;
	private ArrayList<BinaryTreeNode<T>> children;

	public final static int BRANCHING_FACTOR = 2; // this implementation supports any type of tree, for binary tree,
													// keep this constant factor to 2

	public BinaryTreeNode(T key) {
		this.key = key;
		children = new ArrayList<BinaryTreeNode<T>>();
	}


	/**
	 * Checks whether the subtree starting from this BinaryTreeNode contains a node
	 * with specific key
	 * 
	 * @param key, a T object, node to search in the binary tree induced by this node
	 * @return the node corresponding to the key if it exists in the subtree, null
	 *         otherwise
	 */
	public BinaryTreeNode<T> findNode(T key) {
		return findNodeRecursive(this, key);
	}

	private BinaryTreeNode<T> findNodeRecursive(BinaryTreeNode<T> current, T key) {
		if (current.key.equals(key))
			return current;

		BinaryTreeNode<T> found = null;
		for (BinaryTreeNode<T> child : current.getChildren()) {
			found = findNodeRecursive(child, key);
			if (found != null) {
				return found;
			}
		}
		return null;
	}

	/**
	 * Checks if this node is equals to the BinaryTreeNode node.
	 * 
	 * @param node
	 *            a BrinaryTreeNode to be compared against this node
	 * @return true if the this node and the BinaryTreeNode node have the same key
	 *         and the same parent.
	 */
	public boolean equals(BinaryTreeNode<T> node) {
		return node.getKey().equals(key) && node.getParent() == parent;
	}

	public String toString() {
		return String.valueOf(key);
	}

	public T getKey() {
		return key;
	}

	public BinaryTreeNode<T> getParent() {
		return parent;
	}

	public void setParent(BinaryTreeNode<T> parent) {
		this.parent = parent;
	}

	public ArrayList<BinaryTreeNode<T>> getChildren() {
		return children;
	}


}
