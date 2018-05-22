package Assignment1;

import java.util.Arrays;
import java.util.Scanner;

public class Q1 {
	/**
	 * Main Method for testing purposes.
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		String s = in.nextLine();
		String t = in.nextLine();
		System.out.println(isAnagram(s, t));

	}
	/**
	 * Method that checks if two strings are anagrams of the others,
	 * Cases are ignored ( Cat is anagram of cat ) also any other non english
	 * letter is ignored.
	 * @param s first string
	 * @param t second string
	 */
	public static boolean isAnagram(String s, String t) {
		s = s.toLowerCase();
		t = t.toLowerCase();
		s = preprocess(s);
		t = preprocess(t);
		if (s.length() != t.length())
			return false;
		char sArray[] = s.toCharArray();
		char tArray[] = t.toCharArray();
		Arrays.sort(sArray);
		Arrays.sort(tArray);
		for (int i = 0; i < sArray.length; i++)
			if (sArray[i] != tArray[i])
				return false;
		return true;
	}
	private static String preprocess(String str) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++)
			if (isValidChar(str.charAt(i)))
				sb.append(str.charAt(i));
		return sb.toString();
	}

	private static boolean isValidChar(char c) {
		if (c >= 'a' && c <= 'z')
			return true;
		return false;
	}
}
