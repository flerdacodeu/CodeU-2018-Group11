package assignment_3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class WordSearch {

	boolean[][] visited;
	Dictionary dictionary;
	HashSet<String> words;

	/**
	 * A method finds all possible words can be created from the letter grid.
	 * @param grid with letters.
	 * @param dictionary containing valid words.
	 * @return arrayList with all possible words in the grid
	 */
	public HashSet<String> getWords(char[][] grid, Dictionary dictionary) {
		visited = new boolean[grid.length][grid[0].length];
		words = new HashSet<>();
		this.dictionary = dictionary;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				resetVisited();
				searchAt(grid, i, j);
			}
		}
		return words;
	}

	private String searchAt(char[][] grid, int row, int col) {
		String word = "";
		dfs(grid, row, col, word);
		return word;
	}

	private void dfs(char[][] grid, int row, int col, String word) {

		visited[row][col] = true;
		word += grid[row][col];
		if (dictionary.isPrefix(word)) {
			if (dictionary.isWord(word)) {
				words.add(word);
			}
			ArrayList<Point> points = getPossiblePaths(grid, row, col);
			Iterator<Point> itr = points.iterator();
			while (itr.hasNext()) {
				Point point = itr.next();
				dfs(grid, point.x, point.y, word);
				visited[point.x][point.y] = false;
			}
		}
	}

	 ArrayList<Point> getPossiblePaths(char[][] grid, int row, int col) {
		ArrayList<Point> points = new ArrayList<>();
		// add all possible not visited locations of cells in points arrayList
		if (row + 1 < grid.length && !visited[row + 1][col]) {
			points.add(new Point(row + 1, col));
		}
		if (row + 1 < grid.length && col + 1 < grid[0].length
				&& !visited[row + 1][col + 1]) {
			points.add(new Point(row + 1, col + 1));
		}
		if (row - 1 > -1 && !visited[row - 1][col]) {
			points.add(new Point(row - 1, col));
		}
		if (row - 1 > -1 && col - 1 > -1 && !visited[row - 1][col - 1]) {
			points.add(new Point(row - 1, col - 1));
		}
		if (col + 1 < grid[0].length && !visited[row][col + 1]) {
			points.add(new Point(row, col + 1));
		}
		if (col + 1 < grid[0].length && row - 1 > -1
				&& !visited[row - 1][col + 1]) {
			points.add(new Point(row - 1, col + 1));
		}
		if (col - 1 > -1 && !visited[row][col - 1]) {
			points.add(new Point(row, col - 1));
		}
		if (col - 1 > -1 && row + 1 < grid.length && !visited[row + 1][col - 1]) {
			points.add(new Point(row + 1, col - 1));
		}
		return points;
	}

	private void resetVisited() {
		for (int i = 0; i < visited.length; i++) {
			for (int j = 0; j < visited[0].length; j++) {
				visited[i][j] = false;
			}
		}

	}

}
