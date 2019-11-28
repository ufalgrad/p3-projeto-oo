package base;

public abstract class User {
	// User info
	String username;
	String first_name;
	String last_name;
	String token;
	
	
	public User(String username, String token) {
		this.username = username;
		this.token = token;
	}

	public String getName() {
		return first_name + last_name;
	}
	
	public void setName(String f_name, String l_name) {
		first_name = f_name;
		last_name = l_name;
	}
	
	public boolean setPassword(String old_token, String new_token) {
		// Confirm user password before change
		if(token.equals(old_token)) {
			token = new_token;
			// Return true on password change success
			return true;
		}
		// If old_password is different than this.password, return false
		return false;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username ;
	}
	
	public boolean check_token(String token) {
		return token.equals(this.token);
	}
}
