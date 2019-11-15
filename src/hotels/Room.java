package hotels;

import java.util.LinkedList;

public class Room {
	int number;
	LinkedList<Bed> beds = new LinkedList<>();
	
	public Room(int number) {
		this.number = number;
	}
	
	public void AddBed(Bed bed) {
		beds.add(bed);
	}
	
	public void RemoveBed(Bed bed) {
		beds.remove(bed);
	}
}
