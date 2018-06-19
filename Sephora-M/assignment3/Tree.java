package assignment3;

/**
 * This class implements a general Tree. Each node in the tree is an object TreeNode<T> that is represented by a key 
 * of generic type T and points to its children and to its parent. It is assumed that no two nodes have the same key. 
 * 
 * @author Sephora
 *
 * @param <T>
 */

import java.util.concurrent.ThreadLocalRandom;

public abstract class Tree<T> {
	protected TreeNode<T> root;
	
	/**
	 * This constructor initialises a tree with a single node, the root node.
	 * @param root
	 */
	public Tree(TreeNode<T> root) {
		this.root = root;
	}

	
	/**
	 * Adds a child to a specific node provided its branching factor hasn't been reached
	 * @param node
	 * @param child
	 * @return
	 */
	public boolean addChild(TreeNode<T> node, TreeNode<T> child) {
		if (root.findNode(node.getKey()) == null)
			throw new IllegalArgumentException("The tree does not contain a node with key " + node.getKey());
		if (node.getChildren().size() < TreeNode.BRANCHING_FACTOR) {
			child.setParent(node);
			node.getChildren().add(child);
			return true;
		}
		return false;
	}


	/**
	 * Adds a new TreeNode at a randomly selected end location in the subtree induced
	 * by TreeNode<T> node. We define "end location" as an empty spot in the children list of any node in a tree.
	 * 
	 * @param newNode
	 */
	public void addToRandomSubTree(TreeNode<T> node, TreeNode<T> newNode) {
		if (root.findNode(node.getKey()) == null)
			throw new IllegalArgumentException("The tree does not contain a node with key " + node.getKey());	

		// if the this node doesn't have children, then just make newNode a child of
		// this node
		if (node.getChildren().isEmpty())
			addChild(node, newNode);
		
		// otherwise randomly select a child and add newNode to its subtree OR directly
		// add newNode to the current tree if branching factor hasn't been reached
		else {
			int randomChildIndex = ThreadLocalRandom.current().nextInt(0, TreeNode.BRANCHING_FACTOR);
			if (node.getChildren().size() < TreeNode.BRANCHING_FACTOR && randomChildIndex >= node.getChildren().size())
				addChild(node, newNode);
			else
				addToRandomSubTree(node.getChildren().get(randomChildIndex), newNode);
		}

	}

	/**
	 * Checks whether the tree contains a node with specific key
	 * @param key, a T object, node to search in the binary tree 
	 * @return the node corresponding to the key if it exists in the tree, null otherwise
	 */
	public TreeNode<T> find(T key) {
		return root.findNode(key);
	}

	public TreeNode<T> getRoot() {
		return root;
	}

}
