package assignment_4;

import static org.junit.Assert.*;

import org.junit.Test;

public class CountingIslandsTest {

	CountingIslands count = new CountingIslands();

	@Test
	public void testGetNumberOfIslands() {
		boolean[][] tiles_1 = { { false, true, false, true },
				{ true, true, false, false }, { false, false, true, false },
				{ false, false, true, false } };
		assertEquals(count.getNumberOfIslands(tiles_1), 3);
		boolean[][] tiles_2 = { { false, true, false }, { true, true, false },
				{ false, false, true }, { false, false, true } };
		assertEquals(count.getNumberOfIslands(tiles_2), 2);
	}
	
	@Test
	public void testNoIslands() {
		boolean[][] tiles = new boolean[3][2];
		assertEquals(count.getNumberOfIslands(tiles), 0);
	}
	
	@Test
	public void testGetPossibleNeighbours() {
		boolean[][] tiles = { { false, true, false, true },
				{ true, true, false, false }, { false, false, true, false },
				{ false, false, true, false } };
		assertEquals(count.getPossibleNeighbours(tiles, 3, 2).size(), 1);
		assertEquals(count.getPossibleNeighbours(tiles, 0, 3).size(), 0);
		assertEquals(count.getPossibleNeighbours(tiles, 1, 1).size(), 2);
	}
}
