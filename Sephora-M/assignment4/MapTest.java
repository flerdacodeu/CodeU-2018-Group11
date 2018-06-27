package assignment4;

import static org.junit.Assert.*;

import org.junit.Test;

public class MapTest {

	@Test
	public void testExampleMap() {
	boolean[][] map = { {false, true,  false, true}, 
						{true,  true,  false, false},
						{false, false, true, false},
						{false, false, true, false}};
	Map mapGrid = new Map(map); 
	assertEquals(mapGrid.countIslands(),3);
	}
	
	@Test
	public void testSmallIslandsMap() {
	boolean[][] map = { {true,  false,  false, true}, 
						{false, false,  false, false},
						{false, false, false, false},
						{true,  false, false, true}};
	Map mapGrid = new Map(map); 
	assertEquals(mapGrid.countIslands(),4);
	}
	
	@Test
	public void testNoIslandMap() {
	boolean[][] map = { {false, false, false, false}, 
						{false, false, false, false},
						{false, false, false, false},
						{false, false, false, false}};
	Map mapGrid = new Map(map); 
	assertEquals(mapGrid.countIslands(),0);
	}
	
	@Test
	public void testOneBigIslandMap() {
	boolean[][] map = { {true, true,  true, true}, 
						{true, true,  true, true},
						{true, true,  true, true},
						{true, true,  true, true}};
	Map mapGrid = new Map(map); 
	assertEquals(mapGrid.countIslands(),1);
	}
	
	@Test
	public void testDiagonalIslandsMap() {
	boolean[][] map = { {true, false, false, false}, 
						{false, true, false, false},
						{false, false, true, false},
						{false, false, false, true}};
	Map mapGrid = new Map(map); 
	assertEquals(mapGrid.countIslands(),4);
	}
	
	@Test
	public void testLongIslandMap() {
	boolean[][] map = { {true, false, false, false}, 
						{true, false, false, false},
						{true, false, false, false},
						{true, true, true, true}};
	Map mapGrid = new Map(map); 
	assertEquals(mapGrid.countIslands(),1);
	}

}
