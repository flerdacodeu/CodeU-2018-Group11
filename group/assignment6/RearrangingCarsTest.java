package assignment_6;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import assignment6.Sequence;

class RearrangingCarsTest {

	@Test
	void testExampleArrangements() {
		char[] start = {'1','2','0','3'};
		char[] end = {'3','1','2','0'};
		
		RearrrangingCars recar = new RearrrangingCars(start, end);
		LinkedList<Sequence> sequences = recar.generateMoves();
		
		LinkedList<Sequence> expected = new LinkedList<Sequence>();
		expected.add(new Sequence('2', 1, 2));
		expected.add(new Sequence('1', 0, 1));
		expected.add(new Sequence('3', 3, 0));
	
		assertEquals(expected, sequences);
	}
	
	@Test
	void testStartEqualsEnd() {
		char[] start = {'1','2','0','3'};
		char[] end = {'1','2','0','3'};
		RearrrangingCars recar = new RearrrangingCars(start, end);
		
		
		assertTrue(recar.generateMoves().isEmpty());
	}
	
	@Test
	void testSameEmptySpot() {
		char[] start = {'1','2','0','3'};
		char[] end = {'3','1','0','2'};
		RearrrangingCars recar = new RearrrangingCars(start, end);
		recar.generateMoves();
		
		assertTrue(Arrays.equals(end, recar.getStart()));
		
	}
	
	@Test
	void testDifficultRearrangement() {
		char[] start = {'1','2','3','0','4'};
		char[] end = {'2','1','3','4','0'};
		RearrrangingCars recar = new RearrrangingCars(start, end);
		recar.generateMoves();
		
		assertTrue(Arrays.equals(end, recar.getStart()));
	}
	
	@Test
	void testDifficultRearrangement2() {
		char[] start = {'1','2','3','4','5', '0'};
		char[] end = {'2','1','4','3','0','5'};
		RearrrangingCars recar = new RearrrangingCars(start, end);
		recar.generateMoves();
		
		assertTrue(Arrays.equals(end, recar.getStart()));
	}
	
	

}
