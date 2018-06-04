package assignment2;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/***
 * Implements the node object for a BinaryTree. A BinaryTreeNode is represented by a key of generic type T, points to its parent node
 * and to its children. 
 * 
 * @author uceesm1
 *
 * @param <T>
 */
public class BinaryTreeNode<T> {
	private T key;
	private BinaryTreeNode<T> parent;
	private ArrayList<BinaryTreeNode<T>> children;

	public final static int BRANCHING_FACTOR = 2; // this implementation supports any type of tree, for binary tree,
													// keep this constant factor to 2

	public BinaryTreeNode(T key) {
		this.key = key;
		children = new ArrayList<BinaryTreeNode<T>>();
	}

	public BinaryTreeNode(T key, BinaryTreeNode<T> parent) {
		this.key = key;
		this.parent = parent;
		children = new ArrayList<BinaryTreeNode<T>>();
	}

	public BinaryTreeNode(T key, BinaryTreeNode<T> parent, ArrayList<BinaryTreeNode<T>> children) {
		this.key = key;
		this.parent = parent;
		this.children = children;
		for (BinaryTreeNode<T> child : children) {
			child.parent = this;
		}
	}

	/**
	 * Adds a child node provided the branching factor hasn't been reached
	 * 
	 * @param child
	 *            a BinaryTreeNode<T> object to be added as a child
	 * @return true if the node was successfully added, false otherwise
	 */
	public boolean addChild(BinaryTreeNode<T> child) {
		if (children.size() < BRANCHING_FACTOR) {
			child.setParent(this);
			children.add(child);
			return true;
		}
		return false;
	}

	public boolean addChild(T childKey) {
		BinaryTreeNode<T> child = new BinaryTreeNode<T>(childKey);
		return addChild(child);
	}

	/**
	 * Adds a new BinaryTreeNode at a randomly selected end location in the subtree induced
	 * by this node. We define "end location" as an empty spot in the children list of any node in a tree.
	 * 
	 * @param newNode
	 */
	public void addToSubTree(BinaryTreeNode<T> newNode) {
		// if the this node doesn't have children, then just make newNode a child of
		// this node
		if (children.isEmpty())

			addChild(newNode);
		// otherwise randomly select a child and add newNode to its subtree OR directly
		// add newNode to the current tree if branching factor hasn't been reached
		else {
			int randomChildIndex = ThreadLocalRandom.current().nextInt(0, BRANCHING_FACTOR);
			if (children.size() < BRANCHING_FACTOR && randomChildIndex >= children.size())
				addChild(newNode);
			else
				children.get(randomChildIndex).addToSubTree(newNode);
		}

	}

	public void addToSubTree(T newNodeKey) {
		BinaryTreeNode<T> newNode = new BinaryTreeNode<T>(newNodeKey);
		addToSubTree(newNode);
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

	public void setKey(T key) {
		this.key = key;
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

	public void setChildren(ArrayList<BinaryTreeNode<T>> children) {
		this.children = children;
	}

}
