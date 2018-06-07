package assignment2;

/**
 * This class implements a Binary Tree. Each node in the tree is an object BinaryTreeNode<T> that is represented by a key 
 * of generic type T and points to its children and to its parent. It is assumed that no two nodes have the same key. 
 * 
 * @author Sephora
 *
 * @param <T>
 */
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

public class BinaryTree<T> {
	private BinaryTreeNode<T> root;
	private HashSet<T> keys = new HashSet<T>();
	private T openDelimiter;
	private T closeDelimiter;
	
	/**
	 * This constructor initialises a tree with a single node, the root node.
	 * @param root
	 */
	public BinaryTree(T rootKey) {
		BinaryTreeNode<T> root = new BinaryTreeNode<T>(rootKey);
		this.root = root;
		keys.add(rootKey);
	}
	
	/**
	 * This constructor initialises a tree with from a list of node keys given in-order.
	 * It uses user defined delimiters to specify the tree hierarchy. For examples, if the 
	 * delimiters are "(" and ")", the simple tree having "a" as a root and "b" and "c" as children
	 * is described by the list ["a", "(", "(","b", ")", "(", "c", ")",")"]   
	 * @param inOrderKeys	T[], the in-order list of node keys
	 * @param openDelimiter the open delimiter, a type T object
	 * @param closeDelimiter the close delimiter, a type T object
	 */
	public BinaryTree(T[] inOrderKeys, T openDelimiter, T closeDelimiter) {
		this.openDelimiter = openDelimiter;
		this.closeDelimiter = closeDelimiter;
		
		if (inOrderKeys.length == 0){
			root = null;
		}
		
		else {
			T rootKey = inOrderKeys[0];
			if (isOpenDelimiter(rootKey) || isCloseDelimiter(rootKey)) {
				throw new IllegalArgumentException("The first element in the in order list should be the root key, not a delimiter");
			}
		
			// add the root to the tree
			BinaryTreeNode<T> root = new BinaryTreeNode<T>(rootKey);
			this.root = root;
			keys.add(rootKey);
			
			// add the remaining nodes
			if (!addFromInOrderList(inOrderKeys))
				throw new IllegalArgumentException("Invalid in order list!");
		}
	}
	
	private boolean addFromInOrderList(T[] inOrderKeys) {
		Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();
		stack.push(root);
		
		for (int i = 1; i < inOrderKeys.length; i++) {
			BinaryTreeNode<T> current = stack.peek();
			if (inOrderKeys[i].equals(openDelimiter)) {
				if (inOrderKeys.length == i + 1)
					throw new IllegalArgumentException("Invalid in order list!");
				addChild(current, inOrderKeys[i+1]);
				stack.push(find(inOrderKeys[i+1]));
				i++;
			}
			else if (inOrderKeys[i].equals(closeDelimiter)) {
				stack.pop();
			}
		}
		
		stack.pop(); // pop the root
		
		if (!stack.empty()) return false;
		
		return true;
	}

	private boolean isOpenDelimiter(T delimiter) {
		return delimiter.equals(openDelimiter);
	}
	
	private boolean isCloseDelimiter(T delimiter) {
		return delimiter.equals(closeDelimiter);
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
			System.out.println("A root node has no ancestors!");
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
		addToRandomSubTree(root, newNode);
		keys.add(newNodeKey);
	}
	
	
	private boolean addChild(BinaryTreeNode<T> node, BinaryTreeNode<T> child) {
		if (node.getChildren().size() < BinaryTreeNode.BRANCHING_FACTOR) {
			child.setParent(node);
			node.getChildren().add(child);
			return true;
		}
		return false;
	}

	/**
	 * Adds a child node to a BinaryTreeNode<T> node provided the branching factor hasn't been reached
	 * 
	 * @param child
	 *            a BinaryTreeNode<T> object to be added as a child
	 * @return true if the node was successfully added, false otherwise
	 */
	public boolean addChild(BinaryTreeNode<T> node, T childKey) {
		if (root.findNode(node.getKey()) == null)
			throw new IllegalArgumentException("The tree does not contain a node with key " + node.getKey());	
		if (keys.contains(childKey))
			return false;
		BinaryTreeNode<T> child = new BinaryTreeNode<T>(childKey);
		return addChild(node, child);
	}
	
	/**
	 * Adds a child node to a BinaryTreeNode<T> node provided the branching factor hasn't been reached
	 * 
	 * @param child
	 *            a BinaryTreeNode<T> object to be added as a child
	 * @return true if the node was successfully added, false otherwise
	 */
	public boolean addChild(T nodeKey, T childKey) {
		BinaryTreeNode<T> node = root.findNode(nodeKey);
		if (node == null)
			throw new IllegalArgumentException("The tree does not contain a node with key " + nodeKey);	
		if (keys.contains(childKey))
			return false;
		BinaryTreeNode<T> child = new BinaryTreeNode<T>(childKey);
		return addChild(node, child);
	}


	private void addToRandomSubTree(BinaryTreeNode<T> node, BinaryTreeNode<T> newNode) {
		// if the this node doesn't have children, then just make newNode a child of
		// this node
		if (node.getChildren().isEmpty())
			addChild(node, newNode);
		
		// otherwise randomly select a child and add newNode to its subtree OR directly
		// add newNode to the current tree if branching factor hasn't been reached
		else {
			int randomChildIndex = ThreadLocalRandom.current().nextInt(0, BinaryTreeNode.BRANCHING_FACTOR);
			if (node.getChildren().size() < BinaryTreeNode.BRANCHING_FACTOR && randomChildIndex >= node.getChildren().size())
				addChild(node, newNode);
			else
				addToRandomSubTree(node.getChildren().get(randomChildIndex), newNode);
		}

	}

	/**
	 * Adds a new BinaryTreeNode at a randomly selected end location in the subtree induced
	 * by BinaryTreeNode<T> node. We define "end location" as an empty spot in the children list of any node in a tree.
	 * 
	 * @param newNode
	 */
	public void addToRandomSubTree(BinaryTreeNode<T> node, T newNodeKey) {
		if (root.findNode(node.getKey()) == null)
			throw new IllegalArgumentException("The tree does not contain a node with key " + node.getKey());	
		if (keys.contains(newNodeKey))
			throw new IllegalArgumentException("A node with key " + newNodeKey +" already exists in the tree");

		BinaryTreeNode<T> newNode = new BinaryTreeNode<T>(newNodeKey);
		addToRandomSubTree(node, newNode);
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

	public HashSet<T> getKeys() {
		return keys;
	}
}
