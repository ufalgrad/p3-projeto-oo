package database;

import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;

import base.HotelManager;
import base.RegularUser;
import base.User;
import base.Util;
import exceptions.NonUniqueColumnException;
import hotels.Booking;
import hotels.Hotel;
import hotels.Room;

public class InputManager {
	
		Util util;
		ArrayListConnection conn;
		Scanner in;
		
		public InputManager(Scanner input) {
			in = input;
			// Get the static instance of singleton class Util
			util = Util.getInstance();
			// Get the static instance of singleton class Connection
			conn = ArrayListConnection.getInstance();
		}
		
		public void close() {
			conn.close();
			in.close();
		}
		
		/**
		 * Gets username and password and tries to match existing user with hashed password token on the database
		 * @return authenticated User instance if login was successful, or none if username/password was incorrect
		 * @throws NoSuchAlgorithmException
		 * @throws InputMismatchException
		 * @see base.User#logIn(String)
		 */
		public User login() throws NoSuchAlgorithmException, InputMismatchException {
			System.out.println("Username:");
			String username = in.nextLine();
			System.out.println("Password:");
			String pass = in.nextLine();
			String token = util.hashPassword(pass);
			User user = conn.findUser(username);
			if(user.logIn(token)) {
				findUpcomingStays(user);
				return user;
			}
			return null;
		}
		
		/**
		 * Gets username and password and tries to match existing user with hashed password token on the database
		 * @return authenticated User instance if login was successful, or none if username/password was incorrect
		 * @throws NoSuchAlgorithmException
		 * @throws InputMismatchException
		 * @see base.User#logIn(String)
		 */
		public User login(String username, String token) throws NoSuchAlgorithmException, InputMismatchException {
			User user = conn.findUser(username);
			if(user.logIn(token)) {
				findUpcomingStays(user);
				return user;
			}
			return null;
		}
		
		public void logout(String username) {
			User user = conn.findUser(username);
			user.logOut();
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
		
		public void listUsers(String u_name, String f_name, String l_name) throws InputMismatchException {
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
		
		public void editUser(String u_name, String f_name, String l_name, String token) throws NoSuchAlgorithmException, InputMismatchException {
						System.out.println("Edit user: "+u_name);
						
						System.out.println("Change username (or blank to maintain current value): ");
						System.out.println("Current value: "+u_name);
						// get next value and update user if user input was given
						String uname = in.nextLine();
						
						System.out.println("Change first name (or blank to maintain current value): ");
						System.out.println("Current value: "+f_name);
						// get next value and update user if user input was given
						String fname = in.nextLine();
						
						System.out.println("Change last name (or blank to maintain current value): ");
						System.out.println("Current value: "+l_name);
						// get next value and update user if user input was given
						String lname = in.nextLine();
						
						System.out.println("Change password (or blank to maintain current value): ");
						System.out.print("Current value: ");
						
						// get next value and update user if user input was given
						String pass = in.nextLine();
						String new_token = token;
						if(!pass.equals("")) {
							new_token = util.hashPassword(pass);
						}
						
						conn.editUser(u_name, uname, fname, lname, token, new_token);
		}
		
		
		public HotelManager addManager(String username, String token) throws InputMismatchException {
			HotelManager result = new HotelManager(username, token);
			conn.insertUser(result);
			return result;
		}
		
		public Hotel addHotel(String manager) throws InputMismatchException, NonUniqueColumnException, NoSuchAlgorithmException {
			// Get hotel info
			System.out.println("name: ");
			String name = in.nextLine();

			Hotel validation = conn.findHotel(name);
			if(validation != null) {
				throw new NonUniqueColumnException("hotel_name");
			}
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
			while(register_another) {
				int rooms;
				System.out.println("How many single beds are there in this room?");
				single_beds = in.nextInt();
				System.out.println("How many double beds are there in this room?");
				double_beds = in.nextInt();
				System.out.println("Does the room have a bathtub? (true/false)");
				boolean bathtub = in.nextBoolean();

				// Instantiate new Room()
				Room new_room = new Room(util.generateUniqueId(), single_beds, double_beds, bathtub);
						
				System.out.println("How many rooms like this are there in the hotel?");
				rooms = in.nextInt();
				new_hotel.addRooms(new_room, rooms);

				System.out.println("Do you want to add another room? (true/false)");
				register_another = in.nextBoolean();
			}
			conn.insertHotel(new_hotel);
			return new_hotel;
		}
		
		public void listHotels(String name, String city, String state, int rating) {
			int total = 0;
			// iterate through user_list and print results from filtering
			for(Hotel hotel: conn.getHotels()) {
				if(hotel.getName().equals(name) || name.equals("") ||
				   hotel.getCity().equals(city) || city.equals("") || 
				   hotel.getState().equals(state) || state.equals("")) {
					if(hotel.getRating() >= rating) {
						System.out.println("----------");
						System.out.println(hotel.getName());
						System.out.print("Rating: ");
						for(int i = 0; i < hotel.getRating(); i++) {
							System.out.print("*");
						}
						System.out.println();
						System.out.println("----------");
						total += 1;
					}
				}
			}
			if(total == 0) {
				System.out.println("We didn't find any hotels matching this query.");
			}
		}
		
		public void editHotel(String hotel_name, User user) throws InputMismatchException, NoSuchAlgorithmException {
				
				Hotel hotel = conn.findHotel(hotel_name);
				if(hotel != null) {
					if(hotel.getManager().equals(user.getUsername())) {
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
						conn.editHotel(name, description, city, state);

						System.out.println("Do you want to register another room to this hotel?");
						Boolean more_rooms = in.nextBoolean();
						while(more_rooms) {
							int rooms;
							System.out.println("How many single beds are there in this room?");
							int single_beds = in.nextInt();
							System.out.println("How many double beds are there in this room?");
							int double_beds = in.nextInt();
							System.out.println("Does the room have a bathtub? (true/false)");
							boolean bathtub = in.nextBoolean();

							// Instantiate new Room()
							Room new_room = new Room(util.generateUniqueId(), single_beds, double_beds, bathtub);
									
							System.out.println("How many rooms like this are there in the hotel?");
							rooms = in.nextInt();
							hotel.addRooms(new_room, rooms);

							System.out.println("Do you want to add another room? (true/false)");
							more_rooms = in.nextBoolean();
						}
						System.out.println("Hotel "+hotel.getName()+" edited successfully");
					}
					else {
						System.out.println("You did not register this hotel.");
					}
				}
				else {
					System.out.println("This hotel does not exist.");
				}
		}
		
		public Booking addBooking(RegularUser user, Hotel hotel) throws InputMismatchException {
			Booking result = null;
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
			
			System.out.println("How many single beds do you need?");
			int single_beds = in.nextInt();
			System.out.println("How many double beds do you need?");
			int double_beds = in.nextInt();
			System.out.println("Do you want your room to have a bathtub (true/false)?");
			boolean bathtub = in.nextBoolean();
			
			Room room = hotel.getRoom(single_beds, double_beds, bathtub, start, end);
			
			// Check for overlap between bookings in the same hotel
			if(room != null) {
				result = new Booking(user.getUsername(), hotel.getName(), start, end);
				conn.insertBooking(result);
				room.addBooking(result);
				System.out.println("Your booking was made.");
			} else {
				System.out.println("There are no rooms like that available in this hotel");
			}
			return result;
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
		public void findBookings(String hotel_name) {
			int total = 0;
			for(Booking booking: conn.getBookings()) {
				if(hotel_name.equals(booking.getHotel())) {
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
		
		public void findUpcomingStays(User user) {
			GregorianCalendar limit = (GregorianCalendar) GregorianCalendar.getInstance();
			limit.add(Calendar.DATE, 7);
			for(Booking booking: conn.getBookings()) {
				if(user.getUsername().equals(booking.getUser())) {
					if(booking.getCheckin().before((limit))) {
						System.out.println("Upcoming booking:");
						System.out.println(booking.getHotel()+": "+booking.getCheckinString()+" - "+booking.getCheckoutString());
					}
				}
			}
		}
		
		public void rateHotel(User user, Hotel hotel) {
			GregorianCalendar now = (GregorianCalendar) GregorianCalendar.getInstance();
			for(Booking booking: conn.getBookings()) {
				if(user.getUsername().equals(booking.getUser()) && hotel.getName().equals(booking.getHotel())) {
					if(booking.getCheckout().before((now))) {
						System.out.println("Rate your stay at "+hotel.getName()+" (1-5 stars):");
						int rating = in.nextInt();
						in.nextLine();
						hotel.rate((double) rating);
						System.out.print("Rated: ");
						for(int i = 0; i < rating; i++) {
							System.out.print("*");
						}
						System.out.println();
						return;
					}
				}
			}
			System.out.println("Could not rate selected hotel.");
		}
}
