package assignment3;

import java.util.ArrayList;

/***
 * Implements the node object for a Tree. A TreeNode is represented by a key of generic type T, points to its parent node
 * and to its children. 
 * 
 * @author Sephora
 *
 * @param <T>
 */
public abstract class TreeNode<T> {
	private final T key;
	private TreeNode<T> parent;
	protected ArrayList<TreeNode<T>> children;

	public final static int BRANCHING_FACTOR = 27; // all letters of the english alphabet plus the hyphen

	public TreeNode(T key) {
		this.key = key;
		children = new ArrayList<TreeNode<T>>();
	}


	/**
	 * Checks whether the subtree starting from this TreeNode contains a node
	 * with specific key
	 * 
	 * @param key, a T object, node to search in the tree induced by this node
	 * @return the node corresponding to the key if it exists in the subtree, null
	 *         otherwise
	 */
	public TreeNode<T> findNode(T key) {
		return findNodeRecursive(this, key);
	}

	private TreeNode<T> findNodeRecursive(TreeNode<T> current, T key) {
		if (current.key.equals(key))
			return current;

		TreeNode<T> found = null;
		for (TreeNode<T> child : current.getChildren()) {
			found = findNodeRecursive(child, key);
			if (found != null) {
				return found;
			}
		}
		return null;
	}

	/**
	 * Checks if this node is equals to the TreeNode node.
	 * 
	 * @param node
	 *            a TreeNode to be compared against this node
	 * @return true if the this node and the TreeNode node have the same key
	 *         and the same parent.
	 */
	public boolean equals(TreeNode<T> node) {
		return node.getKey().equals(key) && node.getParent() == parent;
	}

	public String toString() {
		return String.valueOf(key);
	}

	public T getKey() {
		return key;
	}

	public TreeNode<T> getParent() {
		return parent;
	}

	public void setParent(TreeNode<T> parent) {
		this.parent = parent;
	}

	public ArrayList<TreeNode<T>> getChildren() {
		return children;
	}
	
	public abstract boolean isEndOfWord();


}
