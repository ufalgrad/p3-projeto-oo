package hotels;

public class Suite extends Room {
	int sinks;
	boolean bathtub;
	
	public Suite(int number, int sinks, boolean bathtub) {
		super(number);
		this.sinks = sinks;
		this.bathtub = bathtub;
	}

}
