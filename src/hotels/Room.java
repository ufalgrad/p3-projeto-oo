package hotels;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import base.Util;

public class Room {
	private String id;
	private int single_beds;
	private int double_beds;
	private boolean bathtub;
	private ArrayList<Booking> bookings;
	
	public Room(String id, int single_beds, int double_beds, boolean bathtub) {
		this.id = id;
		this.single_beds = single_beds;
		this.double_beds = double_beds;
		this.bathtub = bathtub;
		bookings = new ArrayList<Booking>();
	}
	
	public Room getClone() throws NoSuchAlgorithmException {
		Room result = new Room(Util.getInstance().generateUniqueId(), single_beds, double_beds, bathtub);
		for(Booking booking: bookings) {
			Booking booking_result = new Booking(booking.getUser(),
					                             booking.getHotel(),
					                             booking.getCheckin(),
					                             booking.getCheckout());
			result.addBooking(booking_result);
		}
		return result;
	}
	
	public boolean hasBathtub() {
		return bathtub;
	}
	
	public int getBeds() {
		return single_beds + double_beds;
	}
	
	public int getSingleBeds() {
		return single_beds;
	}
	
	public int getDoubleBeds() {
		return double_beds;
	}
	
	public String getId() {
		return id;
	}
	
	public boolean checkType(Room room) {
		int single_beds = room.getSingleBeds();
		int double_beds = room.getDoubleBeds(); 
		boolean bathtub = room.hasBathtub();
		
		if(getSingleBeds() == single_beds && getDoubleBeds() == double_beds && hasBathtub() == bathtub) {
			return true;
		}
		else {
			return false;
		}
	}

	public ArrayList<Booking> getBookings() {
		return bookings;
	}
	
	public void addBooking(Booking booking) {
		bookings.add(booking);
	}
}
