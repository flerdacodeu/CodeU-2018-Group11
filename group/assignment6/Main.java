package assignment_6;

import java.util.Hashtable;

public class Main {

	public static void main(String[] args) {
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
		for (Sequence s : recar.generateMoves())
			System.out.println("move car " + s.getCar() + " from "
					+ s.getFrom() + " to " + s.getTo());

	}

}
