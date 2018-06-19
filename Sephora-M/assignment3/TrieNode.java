package assignment3;

/**
 * TODO documentation
 * @author uceesm1
 *
 */
public class TrieNode extends TreeNode<Character> {
	private boolean isEndOfWord = false;
	
	public TrieNode(Character key, boolean isEndOfWord) {
		super(key);
		this.isEndOfWord =  isEndOfWord;
	}

	public boolean isEndOfWord() {
		return isEndOfWord;
	}
	
	public TrieNode findChild(Character childKey) {
		for(TreeNode<Character> child : this.children) {
			if (child.getKey() == childKey)
				return (TrieNode) child;
		}
		return null;
	}
}
