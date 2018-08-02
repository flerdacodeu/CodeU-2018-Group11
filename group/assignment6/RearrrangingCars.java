package assignment_6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map.Entry;

import assignment6.Sequence;

import java.util.Random;

public class RearrrangingCars {

	private char[] startConfiguration;
	private char[] endConfiguration;
	private Random rand;
	private final char noCarKey = '0';

	public RearrrangingCars(char[] start, char[] end) { // todo check validity of the inputs (same size, unique cars, etc)
		rand = new Random();
		this.startConfiguration = start;
		this.endConfiguration = end;
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
		int emptySlot = findSlot(startConfiguration, noCarKey);
		move(moves, emptySlot);
		
		while (!Arrays.equals(startConfiguration, endConfiguration)) {
			emptySlot = findSlot(startConfiguration, noCarKey);
			int randomSlot = findRandomSlot(); 
			char carToMove = startConfiguration[randomSlot];
			moves.add(new Sequence(carToMove, randomSlot, emptySlot));
			startConfiguration[randomSlot] = noCarKey;
			startConfiguration[emptySlot] = carToMove;
			move(moves, randomSlot);
		}
		return moves;
	}

	private int findRandomSlot() {
		int randomSlot = rand.nextInt(startConfiguration.length - 1) + 1;
		
		// check that the spot we select does 
		// contain a car that is already at the right place
		while(startConfiguration[randomSlot] == endConfiguration[randomSlot])
			randomSlot = rand.nextInt(startConfiguration.length - 1) + 1;
		
		return randomSlot;
	}

	private void move(LinkedList<Sequence> moves, int currentEmptySlot) {
		char carToMove = endConfiguration[currentEmptySlot]; // finds the car that should be in the currently empty slot
		if (carToMove == noCarKey) { // if the current empty slot is also the final empty slot, don't do anything
			return; 
		}
		int currentParkingSlot = findSlot(startConfiguration, carToMove);
		moves.add(new Sequence(carToMove, currentParkingSlot, currentEmptySlot));
		startConfiguration[currentParkingSlot] = noCarKey;
		startConfiguration[currentEmptySlot] = carToMove;
		currentEmptySlot = currentParkingSlot;
		move(moves, currentEmptySlot);
	}

	
	private int findSlot(char[] configuration, char carID) {
		for(int i = 0; i<configuration.length; i++) {
			if(configuration[i] == carID)
				return i;
		}
		throw new IllegalArgumentException("The current configuration does not contain a car with ID "+ carID);
	}
	
	
	public ArrayList<LinkedList<Sequence>> generateAllSequences(){
		// TODO
		ArrayList<LinkedList<Sequence>> allSeqs = new ArrayList<LinkedList<Sequence>>();

		
		return allSeqs;
	}
	
	public Hashtable<Integer, Character> getStart() {
		return start;
	}

	public Hashtable<Integer, Character> getEnd() {
		return end;
	}

}
