package Assignment3;

import java.util.HashSet;
import java.util.Set;

public class WordSearch {
	/**
	 *  Method is divided into two parts, the first part we try all possible starting points on the grid ONLY if
	 *  the current character is a prefix in our dictionary. The second part, if we have a matching prefix then we try to explore
	 *  the neighbouring cells of that current character and once we find a word matching the dictionary we add it to the set of words.
	 *  Also the set choice because two paths can eventually generate the same word so we need to eliminate the duplicates. Also visited array
	 *  is used to enure we don't visit the same cell twice while we are exploring the neighbours of a cell.
	 * @param grid of chars that needs to be explored and assumed that grid is all
	 *  lower case and english letters so no checks needs to be done
	 * @param dictionary dictionary object of words.
	 * @return a set of words that were found.
	 */
	public Set<String> searchWords(char grid[][], Dictionary dictionary) {
		boolean visited[][] = new boolean[grid.length][grid[0].length];
		Set<String> result = new HashSet<String>();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (dictionary.isPrefix(Character.toString(grid[i][j])))
					explore(grid, dictionary, result, visited, i, j, "");
			}
		}
		return result;
	}

	private void explore(char grid[][], Dictionary dictionary, Set<String> result, boolean visited[][], int i, int j,
			String word) {
		if (dictionary.isWord(word)) {
			result.add(word);
			return;
		} else {
			if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && !visited[i][j]) {
				visited[i][j] = true;
				explore(grid, dictionary, result, visited, i - 1, j, word + grid[i][j]);
				explore(grid, dictionary, result, visited, i + 1, j, word + grid[i][j]);
				explore(grid, dictionary, result, visited, i, j - 1, word + grid[i][j]);
				explore(grid, dictionary, result, visited, i, j + 1, word + grid[i][j]);
				explore(grid, dictionary, result, visited, i - 1, j - 1, word + grid[i][j]);
				explore(grid, dictionary, result, visited, i + 1, j - 1, word + grid[i][j]);
				explore(grid, dictionary, result, visited, i + 1, j + 1, word + grid[i][j]);
				explore(grid, dictionary, result, visited, i - 1, j + 1, word + grid[i][j]);
				visited[i][j] = false;
			}
		}
	}
}
