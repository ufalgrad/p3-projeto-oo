package database;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

import base.User;
import base.RegularUser;
import base.HotelManager;
import hotels.Hotel;
import hotels.Booking;

public class Connection {

	private ArrayList<User> user_list = new ArrayList<User>();
	private ArrayList<Hotel> hotel_list = new ArrayList<Hotel>();
	private ArrayList<Booking> booking_list = new ArrayList<Booking>();
	
	public Connection() {
		// Initiate database connection
	}
	
	private static String bytesToHex(byte[] hash) {
	    StringBuffer hexString = new StringBuffer();
	    for (int i = 0; i < hash.length; i++) {
	    String hex = Integer.toHexString(0xff & hash[i]);
	    if(hex.length() == 1) hexString.append('0');
	        hexString.append(hex);
	    }
	    return hexString.toString();
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
				if(user.check_token(token)) {
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
						String new_token = hashPassword(input);
						user.setPassword(token, new_token);
					}
					
					System.out.println("Edit user: "+user.getName());
					
					in.close();
				}
			}
		}
	}
	
	public String hashPassword(String pass) throws NoSuchAlgorithmException {
		final MessageDigest digest = MessageDigest.getInstance("SHA-256");
		final byte[] hashbytes = digest.digest(
		pass.getBytes(StandardCharsets.UTF_8));
		String sha3_256hex = bytesToHex(hashbytes);
		return sha3_256hex;
	}
	
	public HotelManager addManager(String username, String token) {
		HotelManager result = new HotelManager(username, token);
		user_list.add(result);
		return result;
	}
	
	public Hotel addHotel(String name, String description, String city, String state) {
		Hotel result = new Hotel(name, description, city, state);
		hotel_list.add(result);
		return result;
	}
	
	public void listHotels(String name, String city, String state, int rating) {
		// iterate through user_list and print results from filtering
		for(Hotel hotel: hotel_list) {
			if(hotel.getName().equals(name)) {
				System.out.println("name match: "+name);
			}
			if(hotel.getCity().equals(city)) {
				System.out.println("city match: "+city);
			}
			if(hotel.getState().equals(state)) {
				System.out.println("state match: "+state);
			}
		}
	}
	
	public Booking adBooking(RegularUser user, Hotel hotel, String check_in, String check_out) {
		// Calendar rightNow = Calendar.getInstance();
		// TODO: Add calendar functionality
		Booking result = new Booking();
		booking_list.add(result);
		return result;
	}

}
