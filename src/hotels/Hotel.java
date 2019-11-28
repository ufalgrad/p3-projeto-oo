package hotels;

import java.util.ArrayList;

public class Hotel {
	private String name;
	private String description;
	private String city;
	private String state;
	private ArrayList<Room> rooms = new ArrayList<>();
	private ArrayList<Integer> ratings = new ArrayList<>();
	
	// This variable stores the number of the last room added +1
	int room_number = 0;
	
	public Hotel(String name, String description, String city, String state) {
		this.setName(name);
		this.setDescription(description);
		this.setCity(city);
		this.setState(state);
	}
	
	public int addRoom(Room room) {
		rooms.add(room);
		room_number++;
		return room_number;
	}
	// Add many rooms using an array
	public int addRooms(Room[] rooms_to_add) {
		for(Room room: rooms_to_add) {
			rooms.add(room);
			room_number++;
		}
		// Return the new room_number after adding all rooms in the rooms_to_add array
		return room_number;
	}
	// Add many rooms using only one room template (overload)
	public int addRooms(Room room, int rooms_to_add) {
		for(int i = 0; i < rooms_to_add; i++) {
			rooms.add(room);
			room_number++;
		}
		// Return the new room_number after adding all rooms in the rooms_to_add array
		return room_number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public ArrayList<Integer> getRatings() {
		return ratings;
	}

	public void setRatings(ArrayList<Integer> ratings) {
		this.ratings = ratings;
	}
}
