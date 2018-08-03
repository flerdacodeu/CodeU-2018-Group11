package assignment_6;

import java.util.LinkedList;

import assignment6.RearrrangingCars;
import assignment6.Sequence;

public class Main {

	public static void main(String[] args) {
		char[] start = {'1','2','0','3'};
		char[] end = {'3','1','2','0'};

		RearrrangingCars recar = new RearrrangingCars(start, end);
		
		LinkedList<Sequence> sequences = recar.generateMoves();
		for (Sequence s : sequences)
			System.out.println("move car " + s.getCar() + " from "
					+ s.getFrom() + " to " + s.getTo() );

	}

}
