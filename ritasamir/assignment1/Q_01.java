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
		String[] array1 = firstStr.split(" ");
		String[] array2 = secondStr.split(" ");
		boolean isAnagram = false;
		if (array1.length != array2.length)
			return false;
		for (int i = 0; i < array1.length; i++) {
			for (int j = 0; j < array2.length; j++) {
				if (stringSort(array1[i]).equals(stringSort(array2[j]))) {
					isAnagram = true;
					break;
				}
			}
			if (isAnagram) {
				isAnagram = false;
			} else {
				return false;
			}

		}
		return true;
	}

	private String stringSort(String str) {
		char[] array = str.toLowerCase().toCharArray();
		Arrays.sort(array);
		String sortedStr = new String(array);
		return sortedStr;
	}

}
