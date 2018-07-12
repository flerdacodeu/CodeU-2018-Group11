package Assignment4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CountIslandsTest {

	@Test
	void test() {
		CountIslands tester = new CountIslands();
		boolean grid[][] = new boolean[][] { 
			{ false, true, false, true },
			{ true, true, false, false },
			{ false, false, true, false },
			{ false, false, true, false } };
		assertEquals(tester.countIslands(grid, 4, 4), 3);

		boolean allWater[][] = new boolean[][] {
			{ false, false }, 
			{ false, false } };
		assertEquals(tester.countIslands(allWater, 2, 2), 0);

		boolean oneLand[][] = new boolean[][] { 
			{ true, true }, 
			{ true, true } };
		assertEquals(tester.countIslands(oneLand, 2, 2), 1);
		
		boolean diagonal[][]=new boolean[][] {
			{true,false,false},
			{false,true,false},
			{false,false,true}};
		assertEquals(tester.countIslands(diagonal, 3, 3),3);
		
		boolean checkered[][]=new boolean[][] {
			{true,false,true,false,true},
			{false,true,false,true,false},
			{true,false,true,false,true},
			{false,true,false,true,false},
			{true,false,true,false,true}
		};
		assertEquals(tester.countIslands(checkered, 5, 5),13);
		
		boolean lakeInMiddle[][] = new boolean[][] {
			{true,true,true,true,true},
			{true,false,false,false,true},
			{true,false,false,false,true},
			{true,false,false,false,true},
			{true,true,true,true,true}
		};
		assertEquals(tester.countIslands(lakeInMiddle, 5, 5),1);
		
		boolean LShaped[][] = new boolean[][] {
			{true,false,false,false,false},
			{true,false,false,false,false},
			{true,false,false,false,false},
			{true,false,false,false,false},
			{true,true,true,false,false}
		};
		assertEquals(tester.countIslands(LShaped, 5, 5),1);
		
		
		boolean UShaped[][] = new boolean[][] {
			{true,false,false,false,true},
			{true,false,false,false,true},
			{true,false,false,false,true},
			{true,false,false,false,true},
			{true,true,true,true,true}
		};
		assertEquals(tester.countIslands(UShaped, 5, 5),1);
	}

}
