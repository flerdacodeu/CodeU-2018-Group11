package assignment5;

import java.util.ArrayList;

/***
 * Implements the node object for a Directed Graph node. A DirectedGraphNode is represented by a key of generic type T, points to its parents
 * and to its children. 
 * 
 * @author Sephora
 *
 * @param <T>
 */
public class DirectedGraphNode<T> {
	private final T key;
	private ArrayList<DirectedGraphNode<T>> parents;
	protected ArrayList<DirectedGraphNode<T>> children;

	public final static int BRANCHING_FACTOR = Integer.MAX_VALUE; // makes no assumption on the size of the alphabet

	public DirectedGraphNode(T key) {
		this.key = key;
		children = new ArrayList<DirectedGraphNode<T>>();
		parents = new ArrayList<DirectedGraphNode<T>>();
	}


	/**
	 * Checks whether the subtree starting from this DirectedGraphNode contains a node
	 * with specific key
	 * 
	 * @param key, a T object, node to search in the tree induced by this node
	 * @return the node corresponding to the key if it exists in the subtree, null
	 *         otherwise
	 */
	public DirectedGraphNode<T> findNode(T key) {
		return findNodeRecursive(this, key);
	}
	
	/**
	 * Looks for a child node with a specific key in the direct children of the current node.
	 * @param childKey a Character, the key of the child to search
	 * @return the DirectedGraphNode corresponding to the child with key childKey if it exists, null otherwise
	 */
	public TrieNode findChild(T childKey) {
		for(DirectedGraphNode<T> child : this.children) {
			if (child.getKey() == childKey)
				return (TrieNode) child;
		}
		return null;
	}
	
	private DirectedGraphNode<T> findNodeRecursive(DirectedGraphNode<T> current, T key) {
		if (current.key.equals(key))
			return current;

		DirectedGraphNode<T> found = null;
		for (DirectedGraphNode<T> child : current.getChildren()) {
			found = findNodeRecursive(child, key);
			if (found != null) {
				return found;
			}
		}
		return null;
	}

	/**
	 * Checks if this node is equals to the DirectedGraphNode node.
	 * 
	 * @param node
	 *            a DirectedGraphNode to be compared against this node
	 * @return true if the this node and the TreeNode node have the same key
	 *         and the same parent.
	 */
	public boolean equals(DirectedGraphNode<T> node) {
		return node.getKey().equals(key) && node.getParents() == parents;
	}

	public String toString() {
		return String.valueOf(key);
	}

	public T getKey() {
		return key;
	}

	public ArrayList<DirectedGraphNode<T>> getParents() {
		return parents;
	}

	public void addParent(DirectedGraphNode<T> parent) {
		this.parents.add(parent);
	}

	public ArrayList<DirectedGraphNode<T>> getChildren() {
		return children;
	}

	public ArrayList<T> getChildrensKeys(){
		ArrayList<T> childrensKeys = new ArrayList<T>();
		
		for(DirectedGraphNode<T> child : children) {
			childrensKeys.add(child.getKey());
		}
		
		return childrensKeys;
	}

	public boolean removeChild(DirectedGraphNode<T> node) {
		for(DirectedGraphNode<T> child : children) {
			if (child.equals(node)) {
				children.remove(node);
				return true;
			}
		}
		return false;
	}
	
	public boolean removeParent(DirectedGraphNode<T> node) {
		
		for (DirectedGraphNode<T> parent : parents) {
			if (parent.equals(node)) {
				parents.remove(node);
				return true;
			}
		}

		return false;
	}
	


}
