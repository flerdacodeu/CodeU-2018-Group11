package Assignment3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

class WordSearchTest {
	
	@Test
	void searchTest() {
		WordSearch tester = new WordSearch();
		char grid[][] = new char[][] { { 'a', 'a', 'r' }, { 't', 'c', 'd' } };
		Dictionary dictionary = new Dictionary();
		dictionary.addWord("car");
		dictionary.addWord("card");
		dictionary.addWord("cart");
		dictionary.addWord("cat");
		Dictionary dictionary2 = new Dictionary();
		dictionary.addWord("ccar");
		dictionary.addWord("card");
		dictionary.addWord("cart");
		dictionary.addWord("cat");
		char grid2[][] = new char[][] { { 'c', 'a', 'r','d' }};
		Dictionary dictionary3 = new Dictionary();
		Set<String> resultContainsTwoWords = tester.searchWords(grid, dictionary);
		Set<String> resultWithLetterUsedOnce = tester.searchWords(grid, dictionary2);
		Set<String> resultWithTwoWordsFromDict = tester.searchWords(grid2, dictionary3);
		assertEquals(resultWithLetterUsedOnce.contains("ccar"),false);
		assertEquals(resultContainsTwoWords.contains("car"),true);
		assertEquals(resultContainsTwoWords.contains("cat"),true);
		assertEquals(resultContainsTwoWords.size(),2);
		assertEquals(resultWithTwoWordsFromDict.contains("car"),true);
		assertEquals(resultWithTwoWordsFromDict.contains("card"),true);
	}

}
