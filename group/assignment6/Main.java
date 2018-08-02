package assignment_6;

import java.util.Hashtable;
import java.util.LinkedList;

import assignment6.RearrrangingCars;
import assignment6.Sequence;

public class Main {

	public static void main(String[] args) {
		char[] start = {'1','2','3','4','5', '0'};
		
		char[] end = {'2','1','4','3','0','5'};

		RearrrangingCars recar = new RearrrangingCars(start, end);
		
		LinkedList<Sequence> sequences = recar.generateMoves();
		for (Sequence s : sequences)
			System.out.println("move car " + s.getCar() + " from "
					+ (char) (s.getFrom() + 'a') + " to " + (char) (s.getTo() + 'a') );

	}

}
