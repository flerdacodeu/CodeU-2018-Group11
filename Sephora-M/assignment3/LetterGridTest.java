package assignment3;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

/**
 * Note: should improve test by using setup and by testing edge cases...
 * @author Sephora-M
 *
 */
class LetterGridTest {
	
	@Test
	void testInDictionnary() {
		String[] words = {"CAR","CARDS","CART","CAT"};
		Trie dictionnary = new Trie(words);
		
		for(String word : words)
			assertTrue(dictionnary.isWord(word));
	}
	
	@Test
	void testNotInDictionnary() {
		String[] words = {"CAR","CARDS","CART","CAT"};
		Trie dictionnary = new Trie(words);

		assertFalse(dictionnary.isWord("CATS"));
		assertFalse(dictionnary.isWord(""));
	}
	
	@Test
	void testFindWords() {
		String[] words = {"CAR","CARDS","CARD","CART","CAT"};
		Trie dictionnary = new Trie(words);
		
		char[][] letterGrid = {{'A','A','R'}, {'T','C','D'}};
		LetterGrid grid = new LetterGrid(letterGrid); 
		
		Set<String> foundwords = grid.dictionnaryWords(dictionnary);
		
		assertTrue(foundwords.contains("CAR"));
		assertTrue(foundwords.contains("CARD"));
		assertTrue(foundwords.contains("CAT"));
	}

}
