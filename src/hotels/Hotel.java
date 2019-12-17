package hotels;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Hotel {
	private String name;
	private String description;
	private String city;
	private String state;
	private String manager_username;
	private ArrayList<Room> rooms = new ArrayList<>();
	private double rating_total = 0;
	private int rated_count = 1;
	
	public Hotel(String name, String description, String city, String state, String manager) {
		this.setName(name);
		this.setDescription(description);
		this.setCity(city);
		this.setState(state);
		this.manager_username = manager;
	}
	
	private double normalize(double score) {
		if(score > 5)
			score = 5;
		else if(score < 1)
			score = 1;
		return score;
	}
	
	/**
	 * Add a single room to the current Hotel instance
	 * @param room Room instance to be associated to this hotel
	 * @return room number
	 */
	public void addRoom(Room room) {
		rooms.add(room);
	}
	/**
	 * Add many rooms using a Room instance array
	 * @param rooms_to_add array containing all rooms to be added
	 * @return room number of last added room
	 */
	public void addRooms(Room[] rooms_to_add) {
		for(Room room: rooms_to_add) {
			addRoom(room);
		}
	}

	/**
	 * Add many copies of the same Room instance based on a limit quantity
	 * @param room The Room instance to be added
	 * @param rooms_to_add the ammount of clones of the same room to be added
	 * @return the number of the last room added
	 * @throws NoSuchAlgorithmException 
	 */
	public void addRooms(Room room, int rooms_to_add) throws NoSuchAlgorithmException {
		for(int i = 0; i < rooms_to_add; i++) {
			addRoom(room.getClone());
		}
	}
	
	/**
	 * Returns calculated rating for this hotel
	 * @return the calculated hotel score
	 */
	public double getRating() {
		return normalize(rating_total / rated_count);
	}
	
	
	/**
	 * Updates variables used to calculate rating of this hotel with a score variable
	 * @param score contains the new score that will influence the rating calculation
	 * @return the resulting score for this hotel after the new rating was added
	 */
	public double rate(double score) {
		score = normalize(score);
		
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
		return manager_username;
	}
	
	public Room getRoom(int single_beds, int double_beds, boolean bathtub,
			            GregorianCalendar start, GregorianCalendar end) {
		for(Room room: rooms) {
			if(room.getSingleBeds() == single_beds &&
			   room.getDoubleBeds() == double_beds &&
			   room.hasBathtub() == bathtub) {
				System.out.println("Found matching room");
				ArrayList<Booking> bookings = room.getBookings();
				// If there are no bookings yet, that means this room is available
				if(bookings.size() == 0) {
					return room;
				}
				for(Booking booking: bookings) {
					if(!(start.before(booking.getCheckout()) && booking.getCheckin().before(end))) {
						return room;
					}
				}
			}
		}
		return null;
	}
}
