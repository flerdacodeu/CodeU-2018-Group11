package assignment_3;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class WordSearchTest {

	Dictionary dictionary;
	char[][] grid;
	ArrayList<String> result;

	@Test
	public void testGetWords() {

		setDictionary();
		setGrid();
		WordSearch word = new WordSearch();
		result = word.getWords(grid, dictionary);
		assertEquals(result.size(), 3);
		assertTrue(result.contains("car"));
		assertTrue(result.contains("card"));
		assertTrue(result.contains("cat"));

	}
	
	@Test
	public void testEmptyDictionary() {
		dictionary = new Dictionary(new ArrayList<>());
		setGrid();
		WordSearch word = new WordSearch();
		result=word.getWords(grid, dictionary);
		assertEquals(result.size(), 0);
		
		
	}

	private void setGrid() {
		grid = new char[2][3];
		grid[0][0] = 'a';
		grid[0][1] = 'a';
		grid[0][2] = 'r';
		grid[1][0] = 't';
		grid[1][1] = 'c';
		grid[1][2] = 'd';

	}

	private void setDictionary() {
		ArrayList<String> words = new ArrayList<>();
		words.add("car");
		words.add("card");
		words.add("cart");
		words.add("cat");
		dictionary = new Dictionary(words);

	}

}
