package assignment_6;

import assignment6.Sequence;

public class Sequence {
	private char car;
	private int from;
	private int to;

	public Sequence(char car, int from, int to) {
		this.car = car;
		this.from = from;
		this.to = to;
	}

	public char getCar() {
		return car;
	}

	public int getFrom() {
		return from;
	}

	public int getTo() {
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
