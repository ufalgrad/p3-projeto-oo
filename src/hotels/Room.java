package hotels;

public class Room {
	private int number;
	private int single_beds;
	private int double_beds;
	private boolean bathtub;
	private boolean available;
	
	public Room(int number, int single_beds, int double_beds, boolean bathtub) {
		this.number = number;
		this.single_beds = single_beds;
		this.double_beds = double_beds;
		this.bathtub = bathtub;
	}
	
	boolean hasBathtub() {
		return bathtub;
	}
	
	int getBeds() {
		return single_beds + double_beds;
	}
	
	int getSingleBeds() {
		return single_beds;
	}
	
	int getDoubleBeds() {
		return double_beds;
	}
	
	int getNumber() {
		return number;
	}
	
	public boolean isAvailable() {
		return available;
	}
	
	public void setAvailable(boolean status) {
		available = status;
	}
}
