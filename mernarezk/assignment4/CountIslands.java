package Assignment4;

public class CountIslands {
	/**
	 * Iterate over all valid start points, which are set to true meaning they're land
	 * and try to recursively explore from that starting point setting all the land cells to
	 * false so that we do not visit a cell twice and we count a land everytime we have a valid
	 * starting point.
	 * @param grid that needs to be explored.
	 * @param m number of rows.
	 * @param n number of columns.
	 * @return number of islands in the grid.
	 */
	public int countIslands(boolean grid[][], int m, int n) {
		int count = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j]) {
					count++;
					explore(grid, m, n, i, j);
				}
			}
		}
		return count;
	}

	private void explore(boolean grid[][], int m, int n, int i, int j) {
		if (i >= m || i < 0 || j >= n || j < 0 || !grid[i][j])
			return;
		grid[i][j] = false;
		explore(grid, m, n, i + 1, j);
		explore(grid, m, n, i - 1, j);
		explore(grid, m, n, i, j + 1);
		explore(grid, m, n, i, j - 1);
	}
}
