package hotels;

import java.util.GregorianCalendar;
import base.Util;

public class Booking {
	private String user;
	private String hotel;
	private GregorianCalendar check_in;
    private GregorianCalendar check_out;
    private Room room;
    // Get singleton class Util's instance
    Util util = Util.getInstance();
    
    public Booking(String username, String hotel_name, GregorianCalendar check_in, GregorianCalendar check_out) {
    	this.user = username;
    	this.hotel = hotel_name;
    	this.check_in = check_in;
    	this.check_out = check_out;
    }
    
    public String getUser() {
    	return user;
    }
    
    public String getHotel() {
    	return hotel;
    }
    
    /**
     * Compares this instance's hotel_name name to a given hotel_name string
     * @param hotel_name String to be compared
     * @return the result of this comparison
     */
    public boolean checkHotel(String hotel_name) {
    	return hotel.equals(hotel_name);
    }
    
    public GregorianCalendar getCheckin() {
    	return check_in;
    }
    
    public GregorianCalendar getCheckout() {
    	return check_out;
    }
    
    /**
     * Gets check-in date formatted as String
     * @return String containing the formatted date
     * @see base.Util#dateToString()
     */
    public String getCheckinString() {
    	return util.dateToString(check_in);
    }
    
    /**
     * Gets check-out date formatted as String
     * @return String containing the formatted date
     * @see base.Util#dateToString()
     */
    public String getCheckoutString() {
    	return util.dateToString(check_out);
    }
    
    public Room getRoom() {
    	return room;
    }
}
