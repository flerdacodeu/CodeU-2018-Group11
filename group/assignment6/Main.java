package assignment_6;

import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {

		char[] start = {'1','2','0'};
		char[] end = {'2','1','0'};
		RearrrangingCars recar = new RearrrangingCars(start, end);
		
		ArrayList<LinkedList<Move>> allSequences = recar.generateAllSequences();
		System.out.println("Total number of possible move sequences :" + allSequences.size());
		for(LinkedList<Move> sequences : allSequences) {
			System.out.println();
			char[] initial = start.clone();
			for(Move move: sequences) {
				System.out.println("move car " + move.getCar() + " from "
						+ move.getFrom() + " to " + move.getTo() );
				System.out.println(initial);
				move.apply(initial);
			}
		}
		

	}

}
