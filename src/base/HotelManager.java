package base;

import java.util.LinkedList;

import hotels.Hotel;

public class HotelManager extends User {
	
	LinkedList<Hotel> registered_hotels = new LinkedList<>();

	public HotelManager(String username, String password) {
		super(username, password);
	}
	
	public Hotel RegisterHotel(String name, String description,
							   String city, String state) throws IllegalArgumentException{
		// Create new hotel instance
		Hotel new_hotel = new Hotel(name, description, city, state);
		for(Hotel hotel: registered_hotels) {
			if(hotel.getName().equals(new_hotel.getName())) {
				throw new IllegalArgumentException("Hotel with name \""+hotel.getName()+"\" already registered.");
			}
		}
		registered_hotels.add(new_hotel);
		return new_hotel;
	}

}
