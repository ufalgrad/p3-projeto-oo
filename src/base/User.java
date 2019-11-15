package base;

public abstract class User {
	// User info
	String username;
	String first_name;
	String last_name;
	String password;
	
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public void UpdateInfo(String f_name, String l_name) {
		first_name = f_name;
		last_name = l_name;
	}
	
	public boolean ChangePassword(String old_password, String new_password) {
		// Confirm user password before change
		if(password.equals(old_password)) {
			password = new_password;
			// Return true on password change success
			return true;
		}
		// If old_password is different than this.password, return false
		return false;
	}
}
