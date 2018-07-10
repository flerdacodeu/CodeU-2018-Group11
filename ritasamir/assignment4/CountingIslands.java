package assignment_4;

import java.util.ArrayList;
import java.util.Iterator;

import assignment_3.Point;

public class CountingIslands {

	/**
	 * @param tiles
	 *            is a 2-dimensional array of booleans, where false means water
	 *            and true means land
	 * @param rowNumber
	 *            is the number of rows
	 * @param columnNumber
	 *            is the number of columns
	 * @return the number of islands.
	 */
	/* looping through the 2d array and search for an island when we find a land
	   we do a dfs search to get all possible lands next to that land, while
	   doing the dfs we convert the land to water so we won't count this island
	   again and finally increasing the counter of islands by one every time we
	   start with a land.*/
	public int getNumberOfIslands(boolean[][] tiles) {
		int counter = 0;
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				if (tiles[i][j]) {
					counter++;
					dfs(i, j, tiles);
				}
			}
		}
		return counter;
	}

	private void dfs(int row, int col, boolean[][] tiles) {
		tiles[row][col] = false;
		Iterator<Point> itr = getPossibleNeighbours(tiles, row, col).iterator();
		while (itr.hasNext()) {
			Point point = itr.next();
			dfs(point.x, point.y, tiles);
		}
	}

	ArrayList<Point> getPossibleNeighbours(boolean[][] tiles, int row, int col) {
		ArrayList<Point> points = new ArrayList<>();
		if (row + 1 < tiles.length && tiles[row + 1][col]) {
			points.add(new Point(row + 1, col));
		}
		if (row - 1 > -1 && tiles[row - 1][col]) {
			points.add(new Point(row - 1, col));
		}
		if (col + 1 < tiles[0].length && tiles[row][col + 1]) {
			points.add(new Point(row, col + 1));
		}
		if (col - 1 > -1 && tiles[row][col - 1]) {
			points.add(new Point(row, col - 1));
		}
		return points;
	}
}
