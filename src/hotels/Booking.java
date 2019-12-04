package hotels;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Booking {
	private String user;
	private String hotel;
	GregorianCalendar check_in = new GregorianCalendar(2018, 6, 28);
    GregorianCalendar check_out = new GregorianCalendar(2018, 5, 28);
    
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
    
    public boolean checkHotel(String hotel_name) {
    	return hotel.equals(hotel_name);
    }
    
    public GregorianCalendar getCheckin() {
    	return check_in;
    }
    
    public GregorianCalendar getCheckout() {
    	return check_out;
    }
    
    public String getCheckinString() {
    	SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy/MM/dd"); 
	    String dateFormatted = formattedDate.format(check_in.getTime());
	    return dateFormatted;
    }
    
    public String getCheckoutString() {
    	SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy/MM/dd"); 
	    String dateFormatted = formattedDate.format(check_out.getTime());
	    return dateFormatted;
    }
}
