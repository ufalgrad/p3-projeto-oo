package database;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

import base.User;
import base.Util;
import base.RegularUser;
import base.HotelManager;
import hotels.Hotel;
import hotels.Room;
import hotels.Booking;

public class Connection {

	private ArrayList<User> user_list = new ArrayList<User>();
	private ArrayList<Hotel> hotel_list = new ArrayList<Hotel>();
	private ArrayList<Booking> booking_list = new ArrayList<Booking>();
	Util util = new Util();
	
	public Connection() {
		// Initiate database connection
	}
	
	public Hotel findHotel(String hotel_name) {
		for(Hotel hotel: hotel_list) {
			if(hotel.getName().equals(hotel_name)) {
				return hotel;
			}
		}
		// Return null if hotel matching name was not found
		return null;
	}
	
	public RegularUser addUser(String username, String token) {
		RegularUser result = new RegularUser(username, token);
		user_list.add(result);
		return result;
	}
	
	public void listUsers(String u_name, String f_name, String l_name) {
		// iterate through user_list and print results from filtering
		for(User user: user_list) {
			if(user.getUsername().equals(u_name)) {
				System.out.println("username match: "+u_name);
			}
			if(user.getName().split(" ")[0].equals(f_name)) {
				System.out.println("first name match: "+f_name);
			}
			if(user.getName().split(" ")[1].equals(l_name)) {
				System.out.println("last name match: "+l_name);
			}
		}
	}
	
	public void editUser(String u_name, String token) throws NoSuchAlgorithmException {
		for(User user: user_list) {
			if(user.getUsername().equals(u_name)) {
				if(user.checkToken(token)) {
					Scanner in = new Scanner(System.in);
					String input;
					String f_name = user.getName().split(" ")[0];
					String l_name = user.getName().split(" ")[1];
					
					System.out.println("Edit user: "+user.getName());
					
					System.out.println("Change username (or blank to maintain current value): ");
					System.out.println("Current value: "+user.getUsername());
					// get next value and update user if user input was given
					input = in.nextLine();
					if(!input.equals("")) {
						user.setUsername(input);
					}
					
					System.out.println("Change first name (or blank to maintain current value): ");
					System.out.println("Current value: "+f_name);
					// get next value and update user if user input was given
					input = in.nextLine();
					if(!input.equals("")) {
						user.setName(input, l_name);
					}
					
					System.out.println("Change last name (or blank to maintain current value): ");
					System.out.println("Current value: "+l_name);
					// get next value and update user if user input was given
					input = in.nextLine();
					if(!input.equals("")) {
						user.setName(f_name, input);
					}
					
					System.out.println("Change password (or blank to maintain current value): ");
					System.out.print("Current value: ");
					
					// get next value and update user if user input was given
					input = in.nextLine();
					if(!input.equals("")) {
						String new_token = util.hashPassword(input);
						user.setPassword(token, new_token);
					}
					
					System.out.println("Edit user: "+user.getName());
					
					in.close();
				}
			}
		}
	}
	
	
	public HotelManager addManager(String username, String token) {
		HotelManager result = new HotelManager(username, token);
		user_list.add(result);
		return result;
	}
	
	public Hotel addHotel(String manager) {
		Scanner in = new Scanner(System.in);
		// Get hotel info
		System.out.println("name: ");
		String name = in.nextLine();
		System.out.println("description: ");
		String description = in.nextLine();
		System.out.println("city: ");
		String city = in.nextLine();
		System.out.println("state: ");
		String state = in.nextLine();

		// Create new hotel instance
		Hotel new_hotel = new Hotel(name, description, city, state, manager);
				
		// Register hotel rooms
		System.out.println("Register a room:");
		boolean register_another = true;
		int single_beds;
		int double_beds;
		int number = 0;
		while(register_another) {
			int rooms;
			System.out.println("How many single beds are there in this room?");
			single_beds = in.nextInt();
			System.out.println("How many double beds are there in this room?");
			double_beds = in.nextInt();
			System.out.println("Does the room have a bathtub? (true/false)");
			boolean bathtub = in.nextBoolean();

			// Instantiate new Room()
			Room new_room = new Room(number, single_beds, double_beds, bathtub);
					
			System.out.println("How many rooms like this are there in the hotel?");
			rooms = in.nextInt();
			new_hotel.addRooms(new_room, rooms);

			System.out.println("Do you want to add another room? (true/false)");
			register_another = in.nextBoolean();
		}
		in.close();
		return new_hotel;
	}
	
	public void listHotels(String name, String city, String state, int rating) {
		// iterate through user_list and print results from filtering
		for(Hotel hotel: hotel_list) {
			if(hotel.getName().equals(name) || name.equals("")) {
				System.out.println("name match: "+name);
			}
			if(hotel.getCity().equals(city) || city.equals("")) {
				System.out.println("city match: "+city);
			}
			if(hotel.getState().equals(state) || state.equals("")) {
				System.out.println("state match: "+state);
			}
		}
	}
	
	public void editHotel(String hotel_name, User user) {
		Hotel hotel = findHotel(hotel_name);
		if(hotel.getManager().equals(user.getUsername())) {
			// Allow user to edit hotel;
		}
	}
	
	public Booking addBooking(RegularUser user, Hotel hotel) {
		Booking result = null;
		Scanner in = new Scanner(System.in);
		// Allow user to input the date of the booking
		System.out.println("Input booking check-in date (YY/MM/DD): ");
		String checkInStr =  in.nextLine();
		String[] checkInArray = checkInStr.split("/");
		System.out.println("Input booking check-out date (YY/MM/DD): ");
		String checkOutStr =  in.nextLine();
		String[] checkOutArray = checkOutStr.split("/");
		
		GregorianCalendar start = new GregorianCalendar(
				Integer.parseInt(checkInArray[0]),
				Integer.parseInt(checkInArray[1]) - 1,
				Integer.parseInt(checkInArray[2])
		);
		
		GregorianCalendar end = new GregorianCalendar(
				Integer.parseInt(checkOutArray[0]),
				Integer.parseInt(checkOutArray[1]) - 1,
				Integer.parseInt(checkOutArray[2])
		);
		
		// Check for overlap between bookings in the same hotel
		if(!isOverlapping(hotel.getName(), start, end)) {
			result = new Booking(user.getUsername(), hotel.getName(), start, end);
			booking_list.add(result);
		} else {
			System.out.println("Your booking conflicts with existing booking on that hotel");
		}
		in.close();
		return result;
	}
	
	public boolean isOverlapping(String hotel_name, GregorianCalendar start, GregorianCalendar end) {
		for(Booking booking: booking_list) {
			if(booking.checkHotel(hotel_name)) {
				// CHECK IF DATES OVERLAP
				if(start.before(booking.getCheckout()) && booking.getCheckin().before(end)) {
					return true;
				}
			}
		}
		return false;
	}

}
