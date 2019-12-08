package database;

import java.security.NoSuchAlgorithmException;
import java.util.GregorianCalendar;
import java.util.Scanner;

import base.HotelManager;
import base.RegularUser;
import base.User;
import base.Util;
import hotels.Booking;
import hotels.Hotel;
import hotels.Room;

public class InputManager {
	
		// Get the static instance of singleton class Util
		Util util = Util.getInstance();
		// Get the static instance of singleton class Connection
		ArrayListConnection conn = ArrayListConnection.getInstance();
		
		public InputManager() {}
		
		public Hotel findHotel(String hotel_name) throws IllegalArgumentException {
			for(Hotel hotel: conn.getHotels()) {
				if(hotel.getName().equals(hotel_name)) {
					return hotel;
				}
			}
			// Return null if hotel matching name was not found
			return null;
		}
		
		/**
		 * Adds user to database and returns the created instance
		 * @param username the username to be stored in the database
		 * @param token    the hashed password (see Util.hashPassword())
		 * @return the user that was created
		 */
		public RegularUser addUser(String username, String token) {
			RegularUser result = new RegularUser(username, token);
			conn.insertUser(result);
			return result;
		}
		
		public void listUsers(String u_name, String f_name, String l_name) throws IllegalArgumentException {
			int total = 0;
			// iterate through user_list and print results from filtering
			for(User user: conn.getUsers()) {
				if(user.getUsername().equals(u_name)) {
					System.out.println("username match: "+u_name);
					total += 1;
				}
				if(user.getName().split(" ")[0].equals(f_name)) {
					System.out.println("first name match: "+f_name);
					total += 1;
				}
				if(user.getName().split(" ")[1].equals(l_name)) {
					System.out.println("last name match: "+l_name);
					total += 1;
				}
			}
			if(total == 0) {
				System.out.println("We didn't find any users matching this query.");
			}
		}
		
		public void editUser(String u_name, String token) throws NoSuchAlgorithmException, IllegalArgumentException {
			for(User user: conn.getUsers()) {
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
		
		
		public HotelManager addManager(String username, String token) throws IllegalArgumentException {
			HotelManager result = new HotelManager(username, token);
			conn.insertUser(result);
			return result;
		}
		
		public Hotel addHotel(String manager) throws IllegalArgumentException {
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
			conn.insertHotel(new_hotel);
			return new_hotel;
		}
		
		public void listHotels(String name, String city, String state, int rating) {
			int total = 0;
			// iterate through user_list and print results from filtering
			for(Hotel hotel: conn.getHotels()) {
				if(hotel.getName().equals(name) || name.equals("")) {
					System.out.println("name match: "+name);
					total += 1;
				}
				if(hotel.getCity().equals(city) || city.equals("")) {
					System.out.println("city match: "+city);
					total += 1;
				}
				if(hotel.getState().equals(state) || state.equals("")) {
					System.out.println("state match: "+state);
					total += 1;
				}
			}
			if(total == 0) {
				System.out.println("We didn't find any hotels matching this query.");
			}
		}
		
		public void editHotel(String hotel_name, User user) throws IllegalArgumentException {
			Hotel hotel = findHotel(hotel_name);
			if(hotel.getManager().equals(user.getUsername())) {
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

				// Update hotel instance
				hotel.setName(name);
				hotel.setDescription(description);
				hotel.setCity(city);
				hotel.setState(state);
						
				in.close();
			}
		}
		
		public Booking addBooking(RegularUser user, Hotel hotel) throws IllegalArgumentException {
			Booking result = null;
			Scanner in = new Scanner(System.in);
			// Get check-in date from user
			System.out.println("Input booking check-in date (YYYY/MM/DD): ");
			String checkInStr =  in.nextLine();
			// Validate user input
			while(util.validateDateString(checkInStr)) {
				System.out.println("Invalid date format, please try again (YYYY/MM/DD): ");
				checkInStr =  in.nextLine();
			}
			String[] checkInArray = checkInStr.split("/");
			
			// Get check-out date from user
			System.out.println("Input booking check-out date (YYYY/MM/DD): ");
			String checkOutStr =  in.nextLine();
			// Validate user input
			while(util.validateDateString(checkOutStr)) {
				System.out.println("Invalid date format, please try again (YYYY/MM/DD): ");
				checkOutStr =  in.nextLine();
			}
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
				conn.insertBooking(result);
			} else {
				System.out.println("Your booking conflicts with existing booking on that hotel");
			}
			in.close();
			return result;
		}
		
		public boolean isOverlapping(String hotel_name, GregorianCalendar start, GregorianCalendar end) {
			for(Booking booking: conn.getBookings()) {
				if(booking.checkHotel(hotel_name)) {
					// CHECK IF DATES OVERLAP
					if(start.before(booking.getCheckout()) && booking.getCheckin().before(end)) {
						return true;
					}
				}
			}
			return false;
		}
		
		public void findBookings(User user) {
			int total = 0;
			for(Booking booking: conn.getBookings()) {
				if(user.getUsername().equals(booking.getUser())) {
					String date = booking.getCheckinString() + " - " + booking.getCheckoutString();
					String result = date + ": " + booking.getHotel();
					System.out.println(result);
					total += 1;
				}
			}
			if(total == 0) {
				System.out.println("No bookings yet...");
			}
		}
}
