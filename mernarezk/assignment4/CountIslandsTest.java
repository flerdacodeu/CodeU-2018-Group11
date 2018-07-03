package Assignment4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CountIslandsTest {

	@Test
	void test() {
		CountIslands tester = new CountIslands();
		boolean grid[][] = new boolean[][] { { false, true, false, true }, { true, true, false, false },
				{ false, false, true, false }, { false, false, true, false } };
		assertEquals(tester.countIslands(grid, 4, 4), 3);

		boolean allWater[][] = new boolean[][] { { false, false }, { false, false } };
		assertEquals(tester.countIslands(allWater, 2, 2), 0);

		boolean oneLand[][] = new boolean[][] { { true, true }, { true, true } };
		assertEquals(tester.countIslands(oneLand, 2, 2), 1);
	}

}
