package assignment_6;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Random;

public class RearrrangingCars {

	private Hashtable<Integer, Character> start;
	private Hashtable<Integer, Character> end;
	private Random rand;
	private int noCarKey = 0;
	private int noExistKey = -1;

	public RearrrangingCars(Hashtable<Integer, Character> start,
			Hashtable<Integer, Character> end) {
		rand = new Random();
		this.start = start;
		this.end = end;
	}
	
	/**
	 * given an starting and ending state of parking
	 * start with the parking emptySlot in start state,
	 * get the car that will exist at the same slot in the end state 
	 * and do a move by swap the car from its current slot to the emptySlot
	 * repeat that until reaching emptySlots
	 * finally check if the start state equal the end,
	 * if not it will be the case that at the beginning the emptySlot at the same position in two states
	 * in this case generate in the first move a random slot to swap it with the empty 
	 * and then starting the algorithm
	 * @return linkedList of sequences to define moves as car, starting and ending spaces
	 */
	public LinkedList<Sequence> generateMoves() {
		LinkedList<Sequence> moves = new LinkedList<>();
		char emptySlot = start.get(noCarKey);
		move(moves, noCarKey, emptySlot);
		if (!start.equals(end)) {
			int key = rand.nextInt(start.keySet().size() - 1) + 1;
			char value = start.get(key);
			moves.add(new Sequence(key, value, emptySlot));
			start.replace(noCarKey, start.get(key));
			start.replace(key, emptySlot);
			move(moves, key, value);
		}
		return moves;
	}

	private void move(LinkedList<Sequence> moves, int emptyKey, char emptySlot) {
		int key = findKey(end, emptySlot);
		char value = start.get(key);
		if (emptySlot == value) {
			return;
		}
		moves.add(new Sequence(key, value, emptySlot));
		start.replace(noCarKey, start.get(key));
		start.replace(key, emptySlot);
		move(moves, key, value);
	}

	private int findKey(Hashtable<Integer, Character> end, Character value) {
		for (Entry<Integer, Character> element : end.entrySet()) {
			if (element.getValue().equals(value)) {
				return element.getKey();
			}
		}
		return noExistKey;
	}

}
