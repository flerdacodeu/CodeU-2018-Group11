import java.util.Hashtable;

/***
 * This class implements a static method checkAnagrams that checks whether or not two strings are 
 * anagrams of each other, where two strings are anagrams if one string is a rearrangement of the 
 * English letters of the other, disregarding any other characters.
 * @author Sephora-M
 *
 */
public class Anagrams {
	
	/**
	 * Checks whether or not two strings are anagrams of each other
	 * @param str1 the first string
	 * @param str2 the second string 
	 * @param caseSensitive boolean, if true, check for case sensitive anagrams, otherwise cheks for
	 * case insensitive anagrams
	 * @return true if str1 and str2 are anagrams, false otherwise
	 */
	public static boolean checkAnagrams(String str1, String str2, boolean caseSensitive) {
		if (str1.length() == 0 && str2.length() == 0) return true; // consider empty strings to be anagrams
		
		if (!caseSensitive) {
			str1 = str1.toLowerCase();
			str2 = str2.toLowerCase();
		}
		
		// create hashmaps of occurrences for each strings
		Hashtable<Character,Integer> occurrences1 = countOccurrences(str1);
		Hashtable<Character,Integer> occurrences2 = countOccurrences(str2);
		
		return occurrences1.equals(occurrences2);
		
	}

	private static Hashtable<Character, Integer> countOccurrences(String str) {
		Hashtable<Character,Integer> occurrences = new Hashtable<Character,Integer>();
		
		for (int i = 0; i < str.length(); i++) {
			Character c = str.charAt(i);
			if (Character.isLetter(c)) {
				Integer count = occurrences.get(c);
				if (count == null) 
					occurrences.put(c, 1);
				else 
					occurrences.put(c, ++count);
			}
		}
		return occurrences;
	}
}
