package hotels;

import java.util.ArrayList;

public class Hotel {
	private String name;
	private String description;
	private String city;
	private String state;
	private String managerUsername;
	private ArrayList<Room> rooms = new ArrayList<>();
	private double rating_total;
	private int rated_count;
	
	// This variable stores the number of rooms added
	int room_number = 0;
	
	public Hotel(String name, String description, String city, String state, String manager) {
		this.setName(name);
		this.setDescription(description);
		this.setCity(city);
		this.setState(state);
		this.managerUsername = manager;
	}
	
	/**
	 * Add a single room to the current Hotel instance
	 * @param room Room instance to be associated to this hotel
	 * @return room number
	 */
	public int addRoom(Room room) {
		rooms.add(room);
		room_number++;
		return room_number;
	}
	/**
	 * Add many rooms using a Room instance array
	 * @param rooms_to_add array containing all rooms to be added
	 * @return room number of last added room
	 */
	public int addRooms(Room[] rooms_to_add) {
		for(Room room: rooms_to_add) {
			addRoom(room);
		}
		// Return the new room_number after adding all rooms in the rooms_to_add array
		return room_number;
	}

	/**
	 * Add many copies of the same Room instance based on a limit quantity
	 * @param room The Room instance to be added
	 * @param rooms_to_add the ammount of clones of the same room to be added
	 * @return the number of the last room added
	 */
	public int addRooms(Room room, int rooms_to_add) {
		for(int i = 0; i < rooms_to_add; i++) {
			addRoom(room);
		}
		// Return the new room_number after adding all rooms in the rooms_to_add array
		return room_number;
	}
	
	/**
	 * Returns calculated rating for this hotel
	 * @return the calculated hotel score
	 */
	public double getRating() {
		return rating_total / rated_count;
	}
	
	
	/**
	 * Updates variables used to calculate rating of this hotel with a score variable
	 * @param score contains the new score that will influence the rating calculation
	 * @return the resulting score for this hotel after the new rating was added
	 */
	public double rate(double score) {
		rated_count += 1;
		rating_total = rating_total+score;
		return rating_total / rated_count;
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
	
	public String getManager() {
		return managerUsername;
	}
}
