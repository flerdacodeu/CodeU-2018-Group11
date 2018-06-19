package assignment3;

/**
 * TODO documentation
 * @author uceesm1
 *
 */
public class Trie extends Tree<Character>{
	public static final  Character ROOT_SYMBOL = '0';

	
	public Trie(Character rootKey) {
		super(new TrieNode(rootKey,false));
		if(!rootKey.equals(ROOT_SYMBOL))
			throw new IllegalArgumentException("The root key of a trie should be equalled to " + ROOT_SYMBOL);
		TrieNode root = new TrieNode(rootKey, false);
		this.root = root;
	}
	
	public void addWord(String word) {
		// TODO
	}
	
	public boolean isWord(String word) {
		// TODO
		return false;
	}
	
	public boolean isPrefix(String prefix) {
		// TODO
		return false;
	}

}
