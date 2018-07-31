package assignment_6;

import assignment6.Sequence;

public class Sequence {
	private int car;
	private char from;
	private char to;

	public Sequence(int car, char from, char to) {
		this.car = car;
		this.from = from;
		this.to = to;
	}

	public int getCar() {
		return car;
	}

	public char getFrom() {
		return from;
	}

	public char getTo() {
		return to;
	}
	
	@Override
	public boolean equals(Object o) {
		
		if (o == this)
			return true;
		
		if (!(o instanceof Sequence))
			return false;
		
		Sequence s = (Sequence) o;
		return (from == s.getFrom() && to == s.getTo() && car == s.getCar());
	}
}
