package database;

import java.util.ArrayList;

import base.User;
import base.Util;
import hotels.Hotel;
import hotels.Booking;

public class ArrayListConnection implements Connection {

	// Array lists that represent database tables
	private ArrayList<User> user_list = new ArrayList<User>();
	private ArrayList<Hotel> hotel_list = new ArrayList<Hotel>();
	private ArrayList<Booking> booking_list = new ArrayList<Booking>();
    // Get singleton class Util's instance
	Util util = Util.getInstance();
	
	private ArrayListConnection() {}
	
	private static class InstanceHolder{
        private static final ArrayListConnection INSTANCE = new ArrayListConnection();
    }

    // getInstance method that implements singleton behavior
    public static ArrayListConnection getInstance(){
        return InstanceHolder.INSTANCE;
    }
    
    public Hotel findHotel(String hotel_name) {
		for(Hotel hotel: getHotels()) {
			if(hotel.getName().equals(hotel_name)) {
				return hotel;
			}
		}
		// Return null if hotel matching name was not found
		return null;
	}
    
    public int findHotelId(String hotel_name) {
    	int index = 0;
		for(Hotel hotel: getHotels()) {
			if(hotel.getName().equals(hotel_name)) {
				return index;
			}
			index++;
		}
		// Return -1 if hotel matching name was not found
		return -1;
	}
	
	public User findUser(String username) {
		for(User user: getUsers()) {
			if(user.getUsername().equals(username)) {
				return user;
			}
		}
		// Return null if user matching username was not found
		return null;
	}
	
	public int findUserId(String username) {
		int index = 0;
		for(User user: getUsers()) {
			if(user.getUsername().equals(username)) {
				return index;
			}
			index++;
		}
		// Return -1 if user matching username was not found
		return -1;
	}
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
    	System.out.println("Database connection terminated.");
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<User> getUsers() {
    	return user_list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertUser(User user) {
    	user_list.add(user);
    }

    /**
     * {@inheritDoc}
     */
	@Override
	public User editUser(String old_uname, String username, String f_name, String l_name, String old_token, String new_token) {
		for(User user: getUsers()) {
			if(user.getUsername().equals(old_uname)) {
				if(user.checkToken(old_token)) {
					String current_fname = user.getName().split(" ")[0];
					String current_lname = user.getName().split(" ")[1];
					//EDIT USER
					if(!username.equals("")) {
						System.out.println("New username: "+username);
						user.setUsername(username);
					}
					if(!f_name.equals("")) {
						System.out.println("New first name: "+f_name);
						user.setName(f_name, current_lname);
					}
					if(!l_name.equals("")) {
						System.out.println("New last name: "+l_name);
						user.setName(current_fname, l_name);
					}
					user.setPassword(old_token, new_token);
					return user;
				}
			}
		}
		return null;
	}

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<Hotel> getHotels() {
    	return hotel_list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertHotel(Hotel hotel) {
    	hotel_list.add(hotel);
    }

    /**
     * {@inheritDoc}
     */
	@Override
	public Hotel editHotel(String name, String description, String city, String state) {
		for(Hotel hotel: getHotels()) {
			if(hotel.getName().equals(name)) {
				if(!name.equals("")) {
					hotel.setName(name);
				}
				if(!description.equals("")) {
					hotel.setDescription(description);
				}
				if(!city.equals("")) {
					hotel.setCity(city);
				}
				if(!state.equals("")) {
					hotel.setState(state);
				}
				return hotel;
			}
		}
		return null;
	}
	
    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<Booking> getBookings() {
    	return booking_list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertBooking(Booking booking) {
    	booking_list.add(booking);
    }
}
