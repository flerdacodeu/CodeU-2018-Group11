package assignment3;

/**
 * Implementation of a TrieNode which extends the parent class TreeNode<Character>. 
 * It contains a isEndOfWord boolean attribute which is true if the node corresponds to the end of 
 * a Trie word.
 * @author Sephora-M
 *
 */
public class TrieNode extends TreeNode<Character> {
	private boolean isEndOfWord = false;

	public TrieNode(Character key, boolean isEndOfWord) {
		super(key);
		this.isEndOfWord =  isEndOfWord;
	}

	/**
	 * Adds a TrieNode with specific key to the current node's list of children.
	 * If the current node already contains a child with key childKey, then it will only 
	 * updates its isEndOfWord attribute accordingly.
	 * @param childKey
	 * @param isEndOfWord
	 * @return
	 */
	public TrieNode addChild(Character childKey, boolean isEndOfWord) {
		TrieNode foundChild = hasChild(childKey);
		if (foundChild != null) {
			foundChild.setEndOfWord(isEndOfWord);
			return foundChild;
		} else {
			TrieNode child = new TrieNode(childKey, isEndOfWord);
			child.setParent(this);
			this.children.add(child);
			return child;
		}
	}

	private TrieNode hasChild(Character childKey) {
		for (TreeNode<Character> child : this.children) {
			if (child.getKey() == childKey)
				return (TrieNode) child;
		}
		return null;
	}
	
	public boolean isEndOfWord() {
		return isEndOfWord;
	}
	
	/**
	 * Update the isEndOfWord attribute of an existing node. This implementations does not support
	 * word suppression, therefore a isEndOfWord of value 'true' cannot be changed.
	 * @param isEndOfWord
	 */
	public void setEndOfWord(boolean isEndOfWord) {
		if(!isEndOfWord()) // can only change from false to true
			this.isEndOfWord = isEndOfWord;
	}
	
}
