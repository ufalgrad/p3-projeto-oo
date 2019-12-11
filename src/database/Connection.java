package database;

import java.util.ArrayList;

import base.User;
import hotels.Booking;
import hotels.Hotel;

public interface Connection {
	/**
	 * Terminates connection to the DBMS
	 */
	public void close();
	
	/**
     * Queries the database and returns all users in an ArrayList
     */
	public ArrayList<User> getUsers();

    /**
     * Inserts new user into the database
     */
	public void insertUser(User user);

	/**
	 * Edit user instance on database
	 * @param username new username
	 * @param f_name new first name
	 * @param l_name new last name
	 * @param token new hashed password
	 * @return Updated User instance containing new user info
	 */
	public User editUser(String old_uname, String username, String f_name, String l_name, String old_token, String new_token);

    /**
     * Queries the database and returns all hotels in an ArrayList
     */
    public ArrayList<Hotel> getHotels();

    /**
     * Inserts new hotel into the database
     */
	public void insertHotel(Hotel hotel);
	
	/**
	 * Edits hotel instance on database
	 * @param name new hotel name
	 * @param description new hotel description
	 * @param city new hotel city
	 * @param state new hotel state
	 * @return Updated Hotel instance with the new information
	 */
	public Hotel editHotel(String name, String description, String city, String state);

    /**
     * Queries the database and returns all bookings in an ArrayList
     */
    public ArrayList<Booking> getBookings();

    /**
     * Inserts new booking into the database
     */
	public void insertBooking(Booking booking);
}
