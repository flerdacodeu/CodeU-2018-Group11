package assignment5;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		String[] dictionary = {"ART","TECH", "MAT","RAT", "CAT","CAMERA", "CAR"};
		UnknownAlphabet a = new UnknownAlphabet(dictionary);
		
		ArrayList<Character> alphabet = a.alphabet();
		
		System.out.println("Possible alphabet");
		System.out.println(alphabet);
		
	}

}
