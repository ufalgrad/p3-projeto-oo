package database;

import java.util.ArrayList;

import base.User;
import hotels.Booking;
import hotels.Hotel;

public interface Connection {
	/**
     * Queries the database and returns all users in an ArrayList
     */
	public ArrayList<User> getUsers();

    /**
     * Inserts new user into the database
     */
	public void insertUser(User user);

    /**
     * Queries the database and returns all hotels in an ArrayList
     */
    public ArrayList<Hotel> getHotels();

    /**
     * Inserts new hotel into the database
     */
	public void insertHotel(Hotel hotel);

    /**
     * Queries the database and returns all bookings in an ArrayList
     */
    public ArrayList<Booking> getBookings();

    /**
     * Inserts new booking into the database
     */
	public void insertBooking(Booking booking);
}
