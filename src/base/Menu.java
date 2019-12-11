package base;

import java.security.NoSuchAlgorithmException;
import java.util.InputMismatchException;
import java.util.Scanner;

import database.ArrayListConnection;
import database.InputManager;
import exceptions.NonUniqueColumnException;
import hotels.Booking;
import hotels.Hotel;

public class Menu {
	
	private Scanner in;
	private InputManager manager;
	private User user;
	private Util util;
	private ArrayListConnection conn;
	private int choice;
	
	public Menu(Scanner input) {
		in = input;
		manager = new InputManager(in);
		util = Util.getInstance();
		conn = ArrayListConnection.getInstance();
	}
	
	public void close() {
		manager.close();
	}
	
	public void selectUserOption() throws NoSuchAlgorithmException, InputMismatchException, NonUniqueColumnException {

		if(user == null) {
		System.out.println("1. Sign up");
		System.out.println("2. Sign in");
		}
		else {
			System.out.println("3. Log out");
			System.out.println("4. Edit profile");
			if(user.getType() == UserType.RegularUser) {
				System.out.println("5. List hotels");
				System.out.println("6. Book a stay");
				System.out.println("7. List your bookings");
				System.out.println("8. Set star requirement");
				System.out.println("9. Rate hotel");
			}
			else {
				System.out.println("5. Add hotel");
				System.out.println("6. Edit hotel");
				System.out.println("7. Show hotel bookings");
			}
		}

		choice = in.nextInt();
		in.nextLine();
		
		if(choice == 1) {
			System.out.println("Sign up:");
			// Registration logic in the menu class to retain access to the user's token
			System.out.println("Username:");
			String username = in.nextLine();
			User validation = conn.findUser(username);
			if(validation != null) {
				throw new NonUniqueColumnException("username");
			}
			System.out.println("First name:");
			String f_name = in.nextLine();
			System.out.println("Last name:");
			String l_name = in.nextLine();
			System.out.println("Password:");
			String pass = in.nextLine();
			System.out.println("Confirm password:");
			String pass2 = in.nextLine();
			System.out.println("Are you a hotel manager/owner (true/false)?");
			boolean isManager = in.nextBoolean();
			// Confirm user password
			if(!pass.equals(pass2)) {
				System.out.println("Passwords did not match, please try again:");
				pass2 = in.nextLine();
			}
			// If passwords still don't match, the user should try to register again.
			if(!pass.equals(pass2)) {
				System.out.println("Passwords did not match, aborting registration...");
				return;
			}
			String token = util.hashPassword(pass);
			if(isManager) {
				manager.addManager(username, token);
			}
			else {
				manager.addUser(username, token);
			}
			user = manager.login(username, token);
			user.setName(f_name, l_name);
			System.out.println("Registration successful.");
			System.out.println("Authenticated as "+user.getName());
		}
		else if(choice == 2) {
			System.out.println("Sign in:");
			user = manager.login();
			if(user.equals(null)) {
				System.out.println("Invalid username or password...");
			}
			else {
				System.out.println("Authenticated as "+user.getName());
			}
		}
		if(user != null) {
			if(choice == 3) {
				manager.logout(user.getUsername());
				user = null;
				System.out.println("Logged out successfully");
			}
			else if(choice == 4) {
				String f_name = user.getName().split(" ")[0];
				String l_name = user.getName().split(" ")[1];
				System.out.println("Password:");
				String pass = in.nextLine();
				String token = util.hashPassword(pass);
				manager.editUser(user.getUsername(), f_name, l_name, token);
			}
			else if(choice == 5) {
				if(user.getType() == UserType.RegularUser) {
					System.out.println("Find hotels:");
					System.out.println("Name filter (blank for none):");
					String name = in.nextLine();
					System.out.println("City filter (blank for none):");
					String city = in.nextLine();
					System.out.println("State filter (blank for none):");
					String state = in.nextLine();
					manager.listHotels(name, city, state, ((RegularUser) user).getRequirement());
				}
				else {
					System.out.println("Register a new hotel:");
					manager.addHotel(user.getUsername());
				}
			}
			else if(choice == 6) {
				if(user.getType() == UserType.RegularUser) {
					System.out.println("Book a stay:");
					System.out.println("Enter the name of the hotel to request a reservation:");
					String name = in.nextLine();
					Hotel hotel = conn.findHotel(name);
					if(hotel != null) {
						Booking result = manager.addBooking(((RegularUser) user), hotel);
						if(result != null) {
							System.out.println("Booked your stay for "+result.getCheckinString());
							((RegularUser) user).incrementBookings();
						}
					}
					else {
						System.out.println("That hotel does not exist.");
					}
				}
				else {
					System.out.println("Edit hotel:");
					System.out.println("Enter the name of the hotel you want to edit:");
					String name = in.nextLine();
					manager.editHotel(name, user);
				}
			}
			else if(choice == 7) {
				if(user.getType() == UserType.RegularUser) {
					manager.findBookings(user);
				}
				else {
					System.out.println("Show bookings:");
					System.out.println("Enter the name of the hotel you want to query:");
					String name = in.nextLine();
					manager.findBookings(name);
				}
			}
			else if(choice == 8) {
				if(user.getType() == UserType.RegularUser) {
					System.out.println("Set your minimum star requirement (current: "+((RegularUser) user).getRequirement()+")");
					int req = in.nextInt();
					in.nextLine();
					((RegularUser) user).setRequirement(req);
					System.out.println("Requirement updated to "+((RegularUser) user).getRequirement()+" stars.");
				}
			}
			else if(choice == 9) {
				if(user.getType() == UserType.RegularUser) {
					System.out.println("Rate a hotel:");
					System.out.println("Enter hotel name:");
					String name = in.nextLine();
					Hotel hotel = conn.findHotel(name);
					manager.rateHotel(user, hotel);
					
				}
			}
		}
	}
}

	

