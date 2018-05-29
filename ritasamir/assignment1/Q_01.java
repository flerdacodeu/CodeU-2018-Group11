package assignment_1;

import java.util.Arrays;

public class Q_01 {

	/**
	 * checkAnagram is a method that takes two sentences and checks if they are
	 * anagram or not 
	 * @param firstStr is the first sentence
	 * @param secondStr is the second sentence
	 * @return true if they are anagram, false otherwise.
	 */
	public boolean checkAnagram(String firstStr, String secondStr) {
	        if (stringSort(firstStr).equals(stringSort(secondStr))) {
					return true;
				}
		return false;
	}

	private String stringSort(String str) {
		char[] array = str.toLowerCase().toCharArray();
		Arrays.sort(array);
		String sortedStr = new String(array);
		return sortedStr;
	}

}
