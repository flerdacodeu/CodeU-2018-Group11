package Assignment3;

import java.util.HashSet;
import java.util.Set;
/**
 * Simple dictionary class that has two attributes, a set of words which are assumed to be only
 * lower case english letters and prefixes set which is a set that has all possible prefixes of all words.
 */
public class Dictionary {
	private Set<String> words;
	private Set<String> prefixes;

	public Dictionary() {
		words = new HashSet<>();
		prefixes = new HashSet<>();
	}
	/**
	 * Each time we add a new word the dictionary, we compute all possible prefixes and add them to a 
	 * set of prefixes to eliminate duplicates generated from words like cat,cat,cape .. etc.
	 * @param s new word to be added to the dictionary
	 */
	public void addWord(String s) {
		generatePrefixes(s);
		words.add(s);
	}

	private Set<String> generatePrefixes(String s) {
		StringBuilder str = new StringBuilder("");
		for (int i = 0; i < s.length(); i++) {
			str.append(s.charAt(i));
			prefixes.add(str.toString());
		}
		return prefixes;
	}
	/**
	 * Just check if the word is present in the set in O(1) time.
	 * @param s word to be checked.
	 */
	public boolean isWord(String s) {
		return words.contains(s);
	}
	/**
	 * checks if current string is a prefix of one of the words of the dictionary, a hashset rather than a trie
	 * was used as a trade of time complexity as a trie takes O(n) complexity while retrieving from hash would be O(1)
	 * but the downside of the hash it would require a lot of memory to store all possible prefixes.
	 * @param s prefix to be checked if it's prefix of a dictionary.
	 */
	public boolean isPrefix(String s) {
		return prefixes.contains(s);
	}

}
