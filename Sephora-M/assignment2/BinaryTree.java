package assignment2;

/**
 * This class implements a Binary Tree. Each node in the tree is an object BinaryTreeNode<T> that is represented by a key 
 * of generic type T and points to its children and to its parent. It is assumed that no two nodes have the same key. 
 * 
 */
import java.util.HashSet;
import java.util.NoSuchElementException;

public class BinaryTree<T> {
	private BinaryTreeNode<T> root;
	private HashSet<T> keys = new HashSet<T>();
	
	public BinaryTree(BinaryTreeNode<T> root) {
		if (root.getParent() != null)
			throw new IllegalArgumentException("The root node cannot have a non null parent!");
		this.root = root;
		keys.add(root.getKey());
	}
	
	
	/** Q1
	 * Prints all the ancestors of the key in the binary tree
	 * @param key 
	 */
	public void printAncestors(T key) {
		BinaryTreeNode<T> node = find(key);
		if (node == null)
			throw new NoSuchElementException("No element with key "+ key + " found in the tree");
		
		if(node.getParent() == null) {
			System.out.println("A root node node has no ancestors!");
			return;
		}
	
		System.out.println("Ancestors of node " + key + " are [" + recursivePrintAncestors(node.getParent())+"]");
	}

	private String recursivePrintAncestors(BinaryTreeNode<T> node) {
		String ancestors = "";
		if (node.getParent() == null)
			ancestors += (node.toString());
		else {
			ancestors += node.toString() + ", " + recursivePrintAncestors(node.getParent());
		}
		return ancestors;
	}
	
	/**
	 * Q2
	 * Return the common ancestor between nodes associated with keys key1 and key2 provided it exists.
	 * @param key1 T object
	 * @param key2 T object
	 * @return common ancestor between nodes of key1 and key2 if both keys exist in the tree. Throws 
	 * NoSuchElementException if one of the key is not in the tree.
	 */
	public BinaryTreeNode<T> commonAncestor(T key1, T key2){
		/*
		 * The logic of this algoritm is as follows:
		 * starting from node at key1, we recursevly go up the tree and search for key2 in the subtrees
		 * induced by the parent key1.
		 */
		BinaryTreeNode<T> node1 = find(key1);
		if (node1 == null) {
			throw new NoSuchElementException("No element with key "+ key1 + " found in the tree");
		}
		
		// we start by searching key2 in the subtree induced by key1
		if(node1.findNode(key2) != null) {
			return node1;
		}
		
		return recursiveCommonAncestor(node1, key2);
	}
	
	private BinaryTreeNode<T> recursiveCommonAncestor(BinaryTreeNode<T> ancestor, T key2) {
		if (ancestor.getKey() == key2)
			return ancestor;
		
		BinaryTreeNode<T> parent = ancestor.getParent();
		
		if (parent == null) {
			throw new NoSuchElementException("No element with key "+ key2 + " found in the tree");
		}
		if (parent.getKey() == key2) {
			return parent; // check if the parent itself is not key2 to avoid unnecessarily searching its chidren's subtrees
		}
		for (BinaryTreeNode<T> otherChild : parent.getChildren()) {
			if (otherChild != ancestor) {	// we can omit the current ancestor's subtree as it was already searched 
				if(otherChild.findNode(key2) != null) {
					return parent;
				}
			}
		}
		return recursiveCommonAncestor(parent, key2);

	}


	/**
	 * Adds to the tree a new BinaryTreeNode at a randomly selected leaf or existing node
	 * provided such an element doesn't already exist in the tree
	 * @param newNodeKey the key of the new node to be added
	 */
	public void add(T newNodeKey) {
		if (keys.contains(newNodeKey)){
			throw new IllegalArgumentException("A node with key " + newNodeKey +" already exists in the tree");
		}
		BinaryTreeNode<T> newNode = new BinaryTreeNode<T>(newNodeKey);
		root.addToSubTree(newNode);
		keys.add(newNodeKey);
	}
	
	/**
	 * Checks whether the tree contains a node with specific key
	 * @param key, a T object, node to search in the binary tree 
	 * @return the node corresponding to the key if it exists in the tree, null otherwise
	 */
	public BinaryTreeNode<T> find(T key) {
		return root.findNode(key);
	}

	public BinaryTreeNode<T> getRoot() {
		return root;
	}
}