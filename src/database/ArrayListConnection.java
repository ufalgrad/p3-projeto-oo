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
