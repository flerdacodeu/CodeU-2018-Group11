package assignment_4;

import java.util.ArrayList;
import java.util.Iterator;

import assignment_3.Point;

public class CountingIslands {
	int rowNumber;
	int columnNumber;

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
	public int getNumberOfIslands(boolean[][] tiles, int rowNumber,
			int columnNumber) {
		if (tiles.length != rowNumber || tiles[0].length != columnNumber)
			throw new Error("the size doesn't match");
		int counter = 0;
		this.rowNumber = rowNumber;
		this.columnNumber = columnNumber;
		for (int i = 0; i < rowNumber; i++) {
			for (int j = 0; j < columnNumber; j++) {
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
		if (row + 1 < rowNumber && tiles[row + 1][col]) {
			points.add(new Point(row + 1, col));
		}
		if (row - 1 > -1 && tiles[row - 1][col]) {
			points.add(new Point(row - 1, col));
		}
		if (col + 1 < columnNumber && tiles[row][col + 1]) {
			points.add(new Point(row, col + 1));
		}
		if (col - 1 > -1 && tiles[row][col - 1]) {
			points.add(new Point(row, col - 1));
		}
		return points;
	}

}
