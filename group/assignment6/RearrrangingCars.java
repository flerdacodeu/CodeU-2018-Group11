package assignment_6;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;

import assignment6.Sequence;

public class RearrrangingCars {

	private char[] startConfiguration;
	private char[] endConfiguration;
	private Random rand;
	private final char noCarKey = '0';

	public RearrrangingCars(char[] start, char[] end) { 
		if (start.length != end.length)
			throw new IllegalArgumentException("Starting and ending configurations must be of the same size.");
		if (hasDuplicates(start) || hasDuplicates(end))
			throw new IllegalArgumentException("Car IDs must be unique, configurations cannot contain duplicates.");
		
		rand = new Random();
		this.startConfiguration = start;
		this.endConfiguration = end;
	}
	
	private boolean hasDuplicates(char[] configuration){
	  Set<Character> set = new HashSet<Character>();
	  for (char car : configuration) {
	    if (set.contains(car)) 
	    	return true;
	    set.add(car);
	  }
	  return false;
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
		int emptySlot = findCarSlot(startConfiguration, noCarKey);
		move(moves, emptySlot);
		return moves;
	}
	
	private void move(LinkedList<Sequence> moves, int currentEmptySlot) {
		char carToMove = endConfiguration[currentEmptySlot]; 
		
		// it the current configuration and the end configuration have the same empty slot 
		// but are not overall equal, we select a random misplaced car to move
		if(carToMove == noCarKey && !Arrays.equals(startConfiguration, endConfiguration)) {
			int randomSlot = findRandomSlot(); 
			carToMove = startConfiguration[randomSlot];
		}
		 
		if (carToMove == noCarKey) { 
			return; 
		}
		
		int currentParkingSlot = findCarSlot(startConfiguration, carToMove);
		moves.add(new Sequence(carToMove, currentParkingSlot, currentEmptySlot));
		startConfiguration[currentParkingSlot] = noCarKey;
		startConfiguration[currentEmptySlot] = carToMove;
		currentEmptySlot = currentParkingSlot;
		move(moves, currentEmptySlot);
	}

	private int findCarSlot(char[] configuration, char carID) {
		for(int i = 0; i<configuration.length; i++) {
			if(configuration[i] == carID)
				return i;
		}
		throw new IllegalArgumentException("The current configuration does not contain a car with ID "+ carID);
	}
	
	private int findRandomSlot() {
		int randomSlot = rand.nextInt(startConfiguration.length - 1) + 1;
		while(startConfiguration[randomSlot] == endConfiguration[randomSlot])
			randomSlot = rand.nextInt(startConfiguration.length - 1) + 1;
		return randomSlot;
	}

	public char[] getStart() {
		return startConfiguration;
	}

	public char[] getEnd() {
		return endConfiguration;
	}

}
