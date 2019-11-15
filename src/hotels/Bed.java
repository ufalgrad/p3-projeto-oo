package hotels;

public class Bed {
	float width;
	float height;
	int capacity = 1;
	BedType type;
	
	public Bed(float width, float height, int capacity, BedType type) {
		this.width = width;
		this.height = height;
		this.capacity = capacity;
		this.type = type;
	}
}
