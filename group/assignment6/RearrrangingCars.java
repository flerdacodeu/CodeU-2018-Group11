package assignment_6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;

public class RearrrangingCars {

	private char[] currentConfiguration;
	private char[] endConfiguration;
	private int parkingSize;
	private Random rand;
	public final static char noCarKey = '0';

	public RearrrangingCars(char[] start, char[] end) { 
		if (start.length != end.length)
			throw new IllegalArgumentException("Starting and ending configurations must be of the same size.");
		if (hasDuplicates(start) || hasDuplicates(end))
			throw new IllegalArgumentException("Car IDs must be unique, configurations cannot contain duplicates.");
		if (!arePermutations(start, end))
			throw new IllegalArgumentException("The starting and ending configurations must contain the same cars.");
		if (!hasEmptySlot(start))
			throw new IllegalArgumentException("The starting and ending configurations must contain one empty slot"
					+ "represented by character "+ noCarKey +".");
		rand = new Random();
		this.currentConfiguration = start;
		this.endConfiguration = end;
		parkingSize = currentConfiguration.length;
	}

	public char[] getCurrentConfiguration() {
		return currentConfiguration;
	}

	public char[] getEndConfiguration() {
		return endConfiguration;
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
	 * @return an object of type LinkedList<Sequence> representing the list of moves required to 
	 * reach the desired end configuration with a minimum number of moves.
	 */
	public LinkedList<Move> generateMoves() {
		LinkedList<Move> moves = new LinkedList<>();
		int emptySlot = findCarSlot(currentConfiguration, noCarKey);
		recursiveMove(moves, emptySlot);
		return moves;
	}
	
	/**
	 * Computes all possible valid sequences of moves for getting to the end configuration from the
	 * starting configuration. A valid sequences means that the same configuration is never repeated more
	 * than once.
	 * @return an object of type ArrayList<LinkedList<Sequence>>, which is the list of all possible sequences
	 */
	public ArrayList<LinkedList<Move>> generateAllSequences(){
		ArrayList<LinkedList<Move>> allSeqs = new  ArrayList<LinkedList<Move>>();
		for(int carToMove = 0; carToMove<parkingSize; carToMove++) {
			if (currentConfiguration[carToMove] != noCarKey) {
				Set<char[]> previousConfigurations = new HashSet<char[]>();
				previousConfigurations.add(currentConfiguration);
				char[] startCopy = Arrays.copyOf(currentConfiguration, parkingSize);
				LinkedList<Move> moves = new LinkedList<>();
				generateMoves(allSeqs, startCopy, previousConfigurations, moves, carToMove);
			}
		}
		return allSeqs;
	}
	
	private void generateMoves(ArrayList<LinkedList<Move>> allSeqs, char[] configuration, 
			Set<char[]> previousConfigurations, LinkedList<Move> moves, int carToMove) {
		if (configuration[carToMove] == noCarKey)
			return;
		
		int emptySlot = findCarSlot(configuration, noCarKey);
		emptySlot = singleMove(moves, configuration, emptySlot, configuration[carToMove]);
		
		if (setContainsConfiguration(previousConfigurations, configuration))
			return;
		
		if(Arrays.equals(configuration, endConfiguration)) {
			allSeqs.add(moves);
			return;
		}
		
		previousConfigurations.add(configuration);
		for(int nextCarToMove = 0; nextCarToMove<parkingSize; nextCarToMove++) {
				generateMoves(allSeqs, Arrays.copyOf(configuration, configuration.length), new HashSet<char[]>(previousConfigurations), new LinkedList<Move>(moves), nextCarToMove);
		}
		
	}

	private int singleMove(LinkedList<Move> moves, char[] configuration, int currentEmptySlot, char carToMove) {
		int currentParkingSlot = findCarSlot(configuration, carToMove);
		moves.add(new Move(carToMove, currentParkingSlot, currentEmptySlot));
		configuration[currentParkingSlot] = noCarKey;
		configuration[currentEmptySlot] = carToMove;
		return currentParkingSlot;
	}
	
	private void recursiveMove(LinkedList<Move> moves, int currentEmptySlot) {
		char carToMove = endConfiguration[currentEmptySlot]; 
		
		// it the current configuration and the end configuration have the same empty slot 
		// but are not overall equal, we select a random misplaced car to move
		if(carToMove == noCarKey && !Arrays.equals(currentConfiguration, endConfiguration)) {
			int randomSlot = findRandomSlot(); 
			carToMove = currentConfiguration[randomSlot];
		}
		 
		if (carToMove == noCarKey) { 
			return; 
		}
		
		currentEmptySlot = singleMove(moves, currentConfiguration, currentEmptySlot, carToMove);
		recursiveMove(moves, currentEmptySlot);
	}

	private int findCarSlot(char[] configuration, char carID) {
		for(int i = 0; i<configuration.length; i++) {
			if(configuration[i] == carID)
				return i;
		}
		throw new IllegalArgumentException("The current configuration does not contain a car with ID "+ carID);
	}
	
	private int findRandomSlot() {
		int randomSlot = rand.nextInt(parkingSize - 1) + 1;
		while(currentConfiguration[randomSlot] == endConfiguration[randomSlot])
			randomSlot = rand.nextInt(parkingSize - 1) + 1;
		return randomSlot;
	}
	
	private boolean setContainsConfiguration(Set<char[]> set, char[] configuration) {
		for (char[] c : set) {
			if (Arrays.equals(c, configuration))
				return true;
		}
		return false;
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
	
	private boolean arePermutations(char[] config1, char[] config2) {
		Set<Character> startSet = new HashSet<Character>();
		Set<Character> endSet = new HashSet<Character>();
		for (int i=0; i<config1.length; i++) {
			startSet.add(config1[i]);
			endSet.add(config2[i]);
		}
		
		return startSet.equals(endSet);
	}
	
	private boolean hasEmptySlot(char[] config) {
		for(char c : config) {
			if (c == noCarKey)
				return true;
		}
		return false;
	}
}
