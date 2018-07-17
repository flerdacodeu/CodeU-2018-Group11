package assignment5;

/**
 * This class implements a Directed Graph. Each node in the graph is an object DirectedGraphNode<T> that is represented by a key 
 * of generic type T and points to its children and to its parents. It is assumed that no two nodes have the same key. 
 * 
 * @author Sephora
 *
 * @param <T>
 */


public class DirectedGraph<T> {
	protected DirectedGraphNode<T> root;
	
	/**
	 * This constructor initialises a tree with a single node, the root node.
	 * @param root
	 */
	public DirectedGraph(DirectedGraphNode<T> root) {
		this.root = root;
	}

	
	/**
	 * Adds a child to a specific node provided its branching factor hasn't been reached
	 * @param node
	 * @param child
	 * @return
	 */
	public boolean addChild(DirectedGraphNode<T> node, DirectedGraphNode<T> child) {
		if (root.findNode(node.getKey()) == null)
			throw new IllegalArgumentException("The tree does not contain a node with key " + node.getKey());
		if (node.getChildren().size() < DirectedGraphNode.BRANCHING_FACTOR) {
			child.addParent(node);
			node.getChildren().add(child);
			return true;
		}
		return false;
	}


	/**
	 * Checks whether the tree contains a node with specific key
	 * @param key, a T object, node to search in the binary tree 
	 * @return the node corresponding to the key if it exists in the tree, null otherwise
	 */
	public DirectedGraphNode<T> find(T key) {
		return root.findNode(key);
	}

	public DirectedGraphNode<T> getRoot() {
		return root;
	}

	/**
	 * Removes the subtree starting at a specific node from the tree
	 * @param node
	 * 			TreeNode, where to cut off the subtree
	 * @return
	 */
	public DirectedGraphNode<T> remove(DirectedGraphNode<T> node) {
		DirectedGraphNode<T> subTree = find(node.getKey());
		if (subTree == null)
			throw new IllegalArgumentException("The tree does not contain a node with key " + node.getKey());
		
		for (DirectedGraphNode<T> parent : subTree.getParents())
			parent.removeChild(subTree);
		subTree.addParent(null);
		return subTree;
	}

}
