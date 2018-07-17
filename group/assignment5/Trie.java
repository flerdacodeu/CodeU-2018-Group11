package assignment5;

/**
 * Implement a Trie which extends the more general Tree<Character> data structure. This implementation is
 * meant for building English words dictionaries. A word starts from the first level of the tree and goes 
 * down. All leaf nodes are end of words, but not all end of words are located at the leaves - e.g. a 
 * prefix to a word can also be a valid dictionary word. 
 * @author Sephora-M
 *
 */
public class Trie extends DirectedGraph<Character>{
	public static final  Character ROOT_SYMBOL = '0';

	
	public Trie() {
		super(new TrieNode(ROOT_SYMBOL,false));
		TrieNode root = new TrieNode(ROOT_SYMBOL, false);
		this.root = root;
	}
	
	public Trie(String[] words) {
		super(new TrieNode(ROOT_SYMBOL,false));
		TrieNode root = new TrieNode(ROOT_SYMBOL, false);
		this.root = root;
		addWords(words);
	}
	
	/**
	 * Adds a word to the tree
	 * @param word String, word to be added
	 */
	public void addWord(String word) {
		word = word.toLowerCase();
		TrieNode currentLetter = (TrieNode) this.root;
		for (int i=0; i < word.length() ; i++) {
			currentLetter = currentLetter.addChild(word.charAt(i), (i==word.length()-1));
		}
		
	}

	/**
	 * Adds a list of words to the tree in the given order
	 * @param words String[], list of words to be added
	 */
	public void addWords(String[] words) {
		for (String word : words) 
			addWord(word);
	}
	
	
	/**
	 * Checks whether a word is contained in the Trie
	 * @param word String, word to be found
	 * @return true if the word is in the dictionnary, false otherwise
	 */
	public boolean isWord(String word) {
		word = word.toLowerCase();
		if (!validWord(word))
			return false;
		
		TrieNode lastLetter = findLastLetter(word);
		
		if (lastLetter != null && lastLetter.isEndOfWord()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks whether a word is a prefix the Trie
	 * @param word String, word to be found
	 * @return true if the word is prefix of a dictionnary word, false otherwise
	 */
	public boolean isPrefix(String prefix) {
		prefix = prefix.toLowerCase();
		if (!validWord(prefix))
			return false;
		
		TrieNode lastLetter = findLastLetter(prefix);
		
		if (lastLetter == null)
			return false;
		return true;
	}

	private boolean validWord(String word) {
		for(Character c : word.toCharArray()) {
			if (!Character.isLetter(c) && !c.equals('-'))
				return false;
		}
		return true;
	}
	
	private TrieNode findLastLetter(String word) {
		if (word.isEmpty())
			return null;
		
		TrieNode currentLetter = root.findChild(word.charAt(0));
		
		if (currentLetter == null)
			return null;
		
		if (word.length() == 1)
			return currentLetter;
		
		for (int i=1; i < word.length() ; i++) {
			currentLetter = currentLetter.findChild(word.charAt(i));
			if (currentLetter == null)
				return null;
		}
		
		return currentLetter;
	}
	
}
