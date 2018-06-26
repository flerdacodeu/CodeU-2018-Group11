package assignment3;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

/**
 * Note: should improve test by using setup and by testing edge cases...
 * @author Sephora-M
 *
 */
class LetterGridTest {
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testIsWord() {
		String[] words = {"CAR","CARDS","CART","CAT"};
		Trie dictionnary = new Trie(words);
		
		for(String word : words)
			assertTrue(dictionnary.isWord(word));
	}
	

	@Test
	public void testIsNotWord() {
		String[] words = {"CAR","CARDS","CART","CAT"};
		Trie dictionnary = new Trie(words);

		assertFalse(dictionnary.isWord("CATS"));
		assertFalse(dictionnary.isWord(""));
	}
	
	
	@Test
	public void testPrefix() {
		String[] words = {"CAR","CARDS","CART","CAT"};
		Trie dictionnary = new Trie(words);
		
		String[] prefixes = {"C","CARD","CART","CA",""};
		for(String prefix : prefixes)
			assertTrue(dictionnary.isPrefix(prefix));
		
		assertFalse(dictionnary.isPrefix("CATS"));
	}
	
	@Test
	public void testNotPrefix() {
		String[] words = {"CAR","CARDS","CART","CAT"};
		Trie dictionnary = new Trie(words);
		
		assertFalse(dictionnary.isPrefix("CATS"));
		assertFalse(dictionnary.isPrefix("AT"));
	}
	
	@Test
	public void testFindWords() {
		String[] words = {"CAR","CARDS","CARD","CART","CAT"};
		Trie dictionnary = new Trie(words);
		
		char[][] letterGrid = {{'A','A','R'}, {'T','C','D'}};
		LetterGrid grid = new LetterGrid(letterGrid); 
		
		Set<String> foundwords = grid.dictionnaryWords(dictionnary);
		
		assertTrue(foundwords.contains("CAR"));
		assertTrue(foundwords.contains("CARD"));
		assertTrue(foundwords.contains("CAT"));
	}
	
	@Test
	public void testEmptyGrid() {
		String[] words = {"CAR","CARDS","CARD","CART","CAT"};
		Trie dictionnary = new Trie(words);
		
		thrown.expect(IllegalArgumentException.class);
		char[][] letterGrid = {{}, {}};
		LetterGrid grid = new LetterGrid(letterGrid); 
		
		Set<String> foundwords = grid.dictionnaryWords(dictionnary);
		
		assertTrue(foundwords.isEmpty());
	}
	
	@Test
	public void testEmptyDictionnary() {
		String[] words = {};
		Trie dictionnary = new Trie(words);
		
		char[][] letterGrid = {{'A','A','R'}, {'T','C','D'}};
		LetterGrid grid = new LetterGrid(letterGrid); 
		
		Set<String> foundwords = grid.dictionnaryWords(dictionnary);
		
		assertTrue(foundwords.isEmpty());
	}

}
