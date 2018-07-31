package assignment_6;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Hashtable;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

class RearrangingCarsTest {

	@Test
	void testExampleArrangements() {
		Hashtable<Integer, Character> start = new Hashtable<>();
		start.put(1, 'a');
		start.put(2, 'b');
		start.put(3, 'd');
		start.put(0, 'c');
		Hashtable<Integer, Character> end = new Hashtable<>();
		end.put(1, 'b');
		end.put(2, 'c');
		end.put(3, 'a');
		end.put(0, 'd');
		RearrrangingCars recar = new RearrrangingCars(start, end);
		
		LinkedList<Sequence> expected = new LinkedList<Sequence>();
		expected.add(new Sequence(2, 'b', 'c'));
		expected.add(new Sequence(1, 'a', 'b'));
		expected.add(new Sequence(3, 'd', 'a'));
		
		assertEquals(expected, recar.generateMoves());
	}
	
	@Test
	void testStartEqualsEnd() {
		Hashtable<Integer, Character> start = new Hashtable<>();
		start.put(1, 'a');
		start.put(2, 'b');
		start.put(3, 'd');
		start.put(0, 'c');
		Hashtable<Integer, Character> end = new Hashtable<>();
		end.put(1, 'a');
		end.put(2, 'b');
		end.put(3, 'd');
		end.put(0, 'd');
		RearrrangingCars recar = new RearrrangingCars(start, end);
		
		
		assertTrue(recar.generateMoves().isEmpty());
		//TODO: catches a NullPointerException!
	}
	
	@Test
	void testSameEmptySpot() {
		Hashtable<Integer, Character> start = new Hashtable<>();
		start.put(1, 'a');
		start.put(2, 'b');
		start.put(3, 'd');
		start.put(0, 'c');
		Hashtable<Integer, Character> end = new Hashtable<>();
		end.put(1, 'b');
		end.put(2, 'd');
		end.put(3, 'a');
		end.put(0, 'c');
		RearrrangingCars recar = new RearrrangingCars(start, end);
		recar.generateMoves();
		
		assertEquals(end, recar.getStart());
		
	}
	
	@Test
	void testDifficultRearrangement() {
		Hashtable<Integer, Character> start = new Hashtable<>();
		start.put(1, 'a');
		start.put(2, 'b');
		start.put(3, 'c');
		start.put(0, 'd');
		start.put(4, 'e');
		Hashtable<Integer, Character> end = new Hashtable<>();
		end.put(2, 'a');
		end.put(1, 'b');
		end.put(3, 'c');
		end.put(4, 'd');
		end.put(0, 'e');
		RearrrangingCars recar = new RearrrangingCars(start, end);
		recar.generateMoves();
		
		assertEquals(end, recar.getStart());
		//TODO: fails
	}
	
	@Test
	void testDifficultRearrangement2() {
		Hashtable<Integer, Character> start = new Hashtable<>();
		start.put(1, 'a');
		start.put(2, 'b');
		start.put(3, 'c');
		start.put(4, 'd');
		start.put(5, 'e');
		start.put(0, 'f');
		
		Hashtable<Integer, Character> end = new Hashtable<>();
		end.put(2, 'a');
		end.put(1, 'b');
		end.put(4, 'c');
		end.put(3, 'd');
		end.put(0, 'e');
		end.put(5, 'f');
		
		RearrrangingCars recar = new RearrrangingCars(start, end);
		recar.generateMoves();
		
		assertEquals(end, recar.getStart());
		//TODO: fails
	}
}
