package hotels;

public class Room {
	private int number;
	private int single_beds;
	private int double_beds;
	private boolean bathtub;
	
	public Room(int number, int single_beds, int double_beds, boolean bathtub) {
		this.number = number;
		this.single_beds = single_beds;
		this.double_beds = double_beds;
		this.bathtub = bathtub;
	}
	
	public boolean hasBathtub() {
		return bathtub;
	}
	
	public int getBeds() {
		return single_beds + double_beds;
	}
	
	public int getSingleBeds() {
		return single_beds;
	}
	
	public int getDoubleBeds() {
		return double_beds;
	}
	
	public int getNumber() {
		return number;
	}
	
	public boolean checkType(Room room) {
		int single_beds = room.getSingleBeds();
		int double_beds = room.getDoubleBeds(); 
		boolean bathtub = room.hasBathtub();
		
		if(getSingleBeds() == single_beds && getDoubleBeds() == double_beds && hasBathtub() == bathtub) {
			return true;
		}
		else {
			return false;
		}
	}
}
