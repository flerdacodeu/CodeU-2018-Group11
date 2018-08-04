//package assignment_6;

import java.util.*;

public class Main {

	public static void main(String[] args) {

		char[] start = {'1','2','0'};
		char[] end = {'2','1','0'};
		RearrangingCars recar = new RearrangingCars(start, end);

		ArrayList<LinkedList<Move>> allSequences = recar.generateAllSequences();
		System.out.println("Total number of possible move sequences :" + allSequences.size());
		for(LinkedList<Move> sequences : allSequences) {
			System.out.println();
			char[] initial = start.clone();
			for(Move move: sequences) {
				System.out.println(move);
				System.out.println(initial);
				move.apply(initial);
			}
		}

        HashMap<Integer, ArrayList<Integer>> reserved = new HashMap<>();
        ArrayList<Integer> reservedFor2 = new ArrayList<>();
        Collections.addAll(reservedFor2, 3);
        ArrayList<Integer> reservedForOthers = new ArrayList<>();
        Collections.addAll(reservedForOthers, 3, 2, 1,4);
        reserved.put(2, reservedFor2);
        reserved.put(0, reservedForOthers);
        reserved.put(1, reservedForOthers);
        reserved.put(3, reservedForOthers);
        reserved.put(4, reservedForOthers);
        Challenge3.generateMoves(new int[] { 1, 2, 0, 3, 4}, new int[] { 2, 1, 0, 4, 3} , reserved);
    }
}
