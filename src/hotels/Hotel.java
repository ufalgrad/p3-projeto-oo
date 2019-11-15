package hotels;

import java.util.ArrayList;

public class Hotel {
	private String name;
	String description;
	String city;
	String state;
	ArrayList<Room> rooms = new ArrayList<>();
	ArrayList<Integer> ratings = new ArrayList<>();
	
	// This variable stores the number of the last room added +1
	int room_number = 0;
	
	public Hotel(String name, String description, String city, String state) {
		this.setName(name);
		this.description = description;
		this.city = city;
		this.state = state;
	}
	
	public int AddRoom(Room room) {
		rooms.add(room);
		room_number++;
		return room_number;
	}
	// Add many rooms using an array
	public int AddRooms(Room[] rooms_to_add) {
		for(Room room: rooms_to_add) {
			rooms.add(room);
			room_number++;
		}
		// Return the new room_number after adding all rooms in the rooms_to_add array
		return room_number;
	}
	// Add many rooms using only one room template (overload)
	public int AddRooms(Room room, int rooms_to_add) {
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
}
