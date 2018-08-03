package assignment_6;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RearrangingCarsTest {

	@Test
	void testDifferentSizeInput() {
		char[] start = {'1','2','0'};
		char[] end = {'3','1','2','0'};
		Assertions.assertThrows(IllegalArgumentException.class, () -> new RearrrangingCars(start, end));	
	}
	
	@Test
	void testDuplicatesInInput(){
		char[] start = {'1','1','0'};
		char[] end = {'2','1','0'};
		Assertions.assertThrows(IllegalArgumentException.class, () -> new RearrrangingCars(start, end));	
	}
	
	@Test
	void testInputsContainDifferentCars(){
		char[] start = {'1','3','0'};
		char[] end = {'2','1','0'};
		Assertions.assertThrows(IllegalArgumentException.class, () -> new RearrrangingCars(start, end));	
	}
	
	@Test
	void testNoEmptySlotInInput(){
		char[] start = {'1','2','3'};
		char[] end = {'3','1','2'};
		Assertions.assertThrows(IllegalArgumentException.class, () -> new RearrrangingCars(start, end));	
	}
	
	@Test
	void testExampleArrangements() {
		char[] start = {'1','2','0','3'};
		char[] end = {'3','1','2','0'};
		
		RearrrangingCars recar = new RearrrangingCars(start, end);
		LinkedList<Move> sequences = recar.generateMoves();
		
		LinkedList<Move> expected = new LinkedList<Move>();
		expected.add(new Move('2', 1, 2));
		expected.add(new Move('1', 0, 1));
		expected.add(new Move('3', 3, 0));
	
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
		
		assertTrue(Arrays.equals(end, recar.getCurrentConfiguration()));
		
	}
	
	@Test
	void testDifficultRearrangement() {
		char[] start = {'1','2','3','0','4'};
		char[] end = {'2','1','3','4','0'};
		RearrrangingCars recar = new RearrrangingCars(start, end);
		recar.generateMoves();
		
		assertTrue(Arrays.equals(end, recar.getCurrentConfiguration()));
	}
	
	@Test
	void testDifficultRearrangement2() {
		char[] start = {'1','2','3','4','5', '0'};
		char[] end = {'2','1','4','3','0','5'};
		RearrrangingCars recar = new RearrrangingCars(start, end);
		recar.generateMoves();
		
		assertTrue(Arrays.equals(end, recar.getCurrentConfiguration()));
	}

	@Test
	void testGenerateAllSequences() {
		char[] start = {'1','2','0'};
		char[] end = {'2','0','1'};
		RearrrangingCars recar = new RearrrangingCars(start, end);
		ArrayList<LinkedList<Move>> allSequences = recar.generateAllSequences();
		assertEquals(2, allSequences.size());
		for(LinkedList<Move> sequences : allSequences) {
			char[] initial = start.clone();
			for(Move move: sequences) {
				move.apply(initial);
			}
			assertTrue(Arrays.equals(end, initial));
		}
	}
	
	@Test
	void testGenerateAllSequencesWhenStartEqualsEnd() {
		char[] start = {'1','2','0', '3'};
		char[] end = {'1','2','0', '3'};
		RearrrangingCars recar = new RearrrangingCars(start, end);
		ArrayList<LinkedList<Move>> allSequences = recar.generateAllSequences();
		
		assertEquals(0, allSequences.size());
	}
	
	

}
