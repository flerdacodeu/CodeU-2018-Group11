package assignment_6;

public class Move {
	private char car;
	private int from;
	private int to;

	public Move(char car, int from, int to) {
		if (from < 0 || to < 0)
			throw new ArrayIndexOutOfBoundsException("Parking slots ID need to be nonnegative.");
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
	
	public void apply(char[] configuration) {
		if (from >= configuration.length)
			throw new ArrayIndexOutOfBoundsException("Index " + from + "is out of bounds for this configuration.");
		if (to >= configuration.length)
			throw new ArrayIndexOutOfBoundsException("Index " + to + "is out of bounds for this configuration.");
		if (configuration[to] != RearrangingCars.noCarKey)
			throw new IllegalArgumentException("Cannot complete move; the destiation slot is non empty.");
		if (configuration[from] != car)
			throw new IllegalArgumentException("Cannot complete move; the car ("+configuration[from]+") to be moved"
					+ " is not the expected car("+car+").");
		
		configuration[from] = RearrangingCars.noCarKey;
		configuration[to] = car;
	}
	
	@Override
	public String toString() {
		return "move car " + car + " from " + from + " to " +to;
	}
	
	@Override
	public boolean equals(Object o) {
		
		if (o == this)
			return true;
		
		if (!(o instanceof Move))
			return false;
		
		Move s = (Move) o;
		return (from == s.getFrom() && to == s.getTo() && car == s.getCar());
	}
}
