package assignment_3;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Test;

public class WordSearchTest {

	Dictionary dictionary;
	HashSet<String> result;

	@Test
	public void testGetWords_1() {
		setDictionary_1();
		WordSearch word = new WordSearch();
		char[][] grid = { { 'a', 'a', 'r' }, { 't', 'c', 'd' } };
		result = word.getWords(grid, dictionary);
		assertEquals(result.size(), 3);
		assertTrue(result.contains("car"));
		assertTrue(result.contains("card"));
		assertTrue(result.contains("cat"));
	}

	@Test
	public void testGetWords_2() {
		setDictionary_2();
		char[][] grid = { { 'h', 'i', 'd' }, { 'p', 'a', 'n' },
				{ 'p', 's', 'e' }, { 's', 'k', 'l' } };
		WordSearch word = new WordSearch();
		result = word.getWords(grid, dictionary);
		assertEquals(result.size(), 1);
		assertTrue(result.contains("happiness"));
		assertFalse(result.contains("sadness"));
		assertFalse(result.contains("gloom"));
		assertFalse(result.contains("boredom"));
	}

	@Test
	public void testEmptyDictionary() {
		dictionary = new Dictionary(new ArrayList<>());
		char[][] grid = { { 'a', 'a', 'r' }, { 't', 'c', 'd' } };
		WordSearch word = new WordSearch();
		result = word.getWords(grid, dictionary);
		assertEquals(result.size(), 0);
	}

	@Test
	public void testEmptyGrid() {
		setDictionary_1();
		char[][] grid = new char[2][3];
		WordSearch word = new WordSearch();
		result = word.getWords(grid, dictionary);
		assertEquals(result.size(), 0);
	}

	@Test
	public void testGetPossiblePaths() {
		char[][] grid = { { 'h', 'i', 'd' }, { 'p', 'a', 'n' },
				{ 'p', 's', 'e' }, { 's', 'k', 'l' } };
		WordSearch word = new WordSearch();
		word.visited = new boolean[4][3];
		ArrayList<Point> points = word.getPossiblePaths(grid, 1, 0);
		assertEquals(points.size(), 5);
		points = word.getPossiblePaths(grid, 1, 1);
		assertEquals(points.size(), 8);
		points = word.getPossiblePaths(grid, 0, 2);
		assertEquals(points.size(), 3);
	}

	@Test
	public void testIsWord() {
		setDictionary_1();
		assertTrue(dictionary.isWord("car"));
		assertTrue(dictionary.isWord("cart"));
		assertFalse(dictionary.isWord("crad"));
		assertFalse(dictionary.isWord("caat"));
	}

	@Test
	public void testIsPrefix() {
		setDictionary_1();
		assertTrue(dictionary.isPrefix("ca"));
		assertTrue(dictionary.isPrefix("car"));
		assertFalse(dictionary.isPrefix("ce"));
		assertFalse(dictionary.isPrefix("cad"));
	}

	private void setDictionary_1() {
		ArrayList<String> words = new ArrayList<>();
		words.add("car");
		words.add("card");
		words.add("cart");
		words.add("cat");
		dictionary = new Dictionary(words);
	}

	private void setDictionary_2() {
		ArrayList<String> words = new ArrayList<>();
		words.add("happiness");
		words.add("sadness");
		words.add("gloom");
		words.add("boredom");
		dictionary = new Dictionary(words);
	}

}
