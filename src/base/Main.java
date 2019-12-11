package base;

import java.security.NoSuchAlgorithmException;
import java.util.InputMismatchException;
import java.util.Scanner;

import exceptions.NonUniqueColumnException;

public class Main {
	
	// Stores current user's session
	public User current_user;
	private static Menu menu;
	static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args)   {
		menu = new Menu(in);
		boolean running = true;
		while(running) {
			try {
				menu.selectUserOption();
			} catch (NoSuchAlgorithmException | InputMismatchException | NullPointerException e) {
				System.out.println("Entrada inválida.");
				// DEBUG: e.printStackTrace();
				in.next();
			} catch (NonUniqueColumnException e) {
				System.out.println(e.getMessage());
				continue;
			}
		}
	}
	
	public void execute_command(String cmd, Scanner in) {
		System.out.println("ASD"+cmd);
	}
}
