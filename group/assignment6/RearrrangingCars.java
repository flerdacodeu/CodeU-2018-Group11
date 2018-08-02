package assignment_6;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map.Entry;

public class RearrrangingCars {

	private Hashtable<Integer, Character> start;
	private Hashtable<Integer, Character> end;
	private HashSet<Integer> carsInRightSlots;
	private final int noCarKey = 0;

	public RearrrangingCars(Hashtable<Integer, Character> start,
			Hashtable<Integer, Character> end) {
		carsInRightSlots = new HashSet<>();
		this.start = start;
		this.end = end;
	}

	/**
	 * given an starting and ending state of parking start with the parking
	 * emptySlot in start state, get the car that will exist at the same slot in
	 * the end state and do a move by swap the car from its current slot to the
	 * emptySlot repeat that until reaching emptySlots and meanwhile check if the
	 * start state equal the end before ending the moves, if not it will be the case that at the
	 * beginning the emptySlot at the same position in two states in this case
	 * generate in the first move a random slot to swap it with the empty and
	 * then starting the algorithm
	 * 
	 * @return linkedList of sequences to define moves as car, starting and
	 *         ending spaces
	 */
	public LinkedList<Sequence> generateMoves() {
		LinkedList<Sequence> moves = new LinkedList<>();
		char emptySlot = start.get(noCarKey);
		move(moves, noCarKey, emptySlot);
		return moves;
	}

	private void move(LinkedList<Sequence> moves, int emptyKey, char emptySlot) {
		int carID = findCarID(end, emptySlot);
		if (carID == 0 && !start.equals(end)) {
			carID = generateRandomCarID();
		} else {
			carsInRightSlots.add(carID);
		}
		char parkingSlot = start.get(carID);
		if (emptySlot == parkingSlot) {
			return;
		}
		moves.add(new Sequence(carID, parkingSlot, emptySlot));
		start.replace(noCarKey, start.get(carID));
		start.replace(carID, emptySlot);
		move(moves, carID, parkingSlot);
	}

	private int findCarID(Hashtable<Integer, Character> configuration,
			Character parkingSlot) {
		for (Entry<Integer, Character> element : configuration.entrySet()) {
			if (element.getValue().equals(parkingSlot)) {
				return element.getKey();
			}
		}
		throw new Error("That input is not correct, Missing car or slot");
	}

	private int generateRandomCarID() {
		for (int i = 1; i < start.size(); i++) {
			if (!carsInRightSlots.contains(i))
				return i;
		}
		return noCarKey;
	}

	public Hashtable<Integer, Character> getStart() {
		return start;
	}

	public Hashtable<Integer, Character> getEnd() {
		return end;
	}

}
