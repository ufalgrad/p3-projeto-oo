package base;

import java.util.Scanner;

public class Main {
	
	// Stores current user's session
	public User current_user;
	
	public static void main(String[] args) {
		System.out.println("Hello World!");
		Main self = new Main();
		Scanner in = new Scanner(System.in);
		String cmd = in.nextLine();
		self.execute_command(cmd, in);
	}
	
	public void execute_command(String cmd, Scanner in) {
		System.out.println("ASD"+cmd);
	}
}
