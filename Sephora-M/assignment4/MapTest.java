package assignment4;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Test;

public class MapTest {

	@Test
	public void testExampleMap() {
		boolean[][] map = { { false, true, false, true }, { true, true, false, false }, { false, false, true, false },
				{ false, false, true, false } };
		Map mapGrid = new Map(map);
		assertEquals(mapGrid.countIslands(), 3);
	}

	@Test
	public void testSmallIslandsMap() {
		boolean[][] map = { { true, false, false, true }, { false, false, false, false },
				{ false, false, false, false }, { true, false, false, true } };
		Map mapGrid = new Map(map);
		assertEquals(mapGrid.countIslands(), 4);
	}

	@Test
	public void testNoIslandMap() {
		boolean[][] map = { { false, false, false, false }, { false, false, false, false },
				{ false, false, false, false }, { false, false, false, false } };
		Map mapGrid = new Map(map);
		assertEquals(mapGrid.countIslands(), 0);
	}

	@Test
	public void testOneBigIslandMap() {
		boolean[][] map = { { true, true, true, true }, { true, true, true, true }, { true, true, true, true },
				{ true, true, true, true } };
		Map mapGrid = new Map(map);
		assertEquals(mapGrid.countIslands(), 1);
	}

	@Test
	public void testDiagonalIslandsMap() {
		boolean[][] map = { { true, false, false, false }, { false, true, false, false }, { false, false, true, false },
				{ false, false, false, true } };
		Map mapGrid = new Map(map);
		assertEquals(mapGrid.countIslands(), 4);
	}

	@Test
	public void testLongIslandMap() {
		boolean[][] map = { { true, false, false, false }, { true, false, false, false }, { true, false, false, false },
				{ true, true, true, true } };
		Map mapGrid = new Map(map);
		assertEquals(mapGrid.countIslands(), 1);
	}
	
	@Test
	public void testNeighbors() {
		boolean[][] map = new boolean[4][4];
		Map mapGrid = new Map(map);
		
		ArrayList<Point> neighbors = mapGrid.findNeighbors(new Point(0,0), new boolean[4][4]);
		
		assertTrue(neighbors.contains(new Point(0,1)));
		assertTrue(neighbors.contains(new Point(1,0)));
		
		neighbors = mapGrid.findNeighbors(new Point(1,2), new boolean[4][4]);
		
		assertTrue(neighbors.contains(new Point(1,1)));
		assertTrue(neighbors.contains(new Point(1,3)));
		assertTrue(neighbors.contains(new Point(0,2)));
		assertTrue(neighbors.contains(new Point(2,2)));
		
		boolean[][] visited = { { true, true, false, false }, 
								{ false, false, false, false },
								{ false, false, false, false }, 
								{ false, false, false, false } };
		
		neighbors = mapGrid.findNeighbors(new Point(0,0), visited);
		
		assertFalse(neighbors.contains(new Point(1,0)));
		assertTrue(neighbors.contains(new Point(0,1)));
	}

}
