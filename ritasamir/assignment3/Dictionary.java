package assignment_3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

public class Dictionary {
	Hashtable<Integer, String> dictionary;
	HashSet<String> prefixes;

	/**
	 * create a dictionary with a list of words
	 * 
	 * @param words
	 */
	public Dictionary(ArrayList<String> words) {
		dictionary = new Hashtable<>(words.size());
		prefixes = new HashSet<>();
		for (int i = 0; i < words.size(); i++) {
			dictionary.put(i, words.get(i));
			getPrefixes(words.get(i));
		}
	}

	private void getPrefixes(String string) {
		for (int i = 1; i < string.length() + 1; i++) {
			String prefix = string.substring(0, i);
			if (!prefixes.contains(prefix)) {
				prefixes.add(prefix);
			}
		}
	}

	/**
	 * @param word
	 * @return true if this word exists in the dictionary, false otherwise.
	 */
	public boolean isWord(String word) {
		return dictionary.contains(word);
	}

	/**
	 * @param word
	 * @return true if this word is a prefix in the dictionary, false otherwise.
	 */
	public boolean isPrefix(String word) {
		return prefixes.contains(word);

	}

}
