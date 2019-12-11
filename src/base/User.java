package base;

public abstract class User {
	// User info
	protected String username;
	protected String first_name;
	protected String last_name;
	private String token;
	private boolean authenticated = false;
	
	
	public User(String username, String token) {
		this.username = username;
		this.token = token;
	}

	public abstract UserType getType();

	/**
	 * Returns the full name of current user
	 * @return string concatenation of first_name and last_name
	 */
	public String getName() {
		return first_name + " "+ last_name;
	}
	
	/**
	 * Updates user full name
	 * @param f_name new first name
	 * @param l_name new last name
	 */
	public void setName(String f_name, String l_name) {
		first_name = f_name;
		last_name = l_name;
	}
	
	/**
	 * Updates current user token
	 * @param old_token old password to confirm validity of this password update
	 * @param new_token the resulting password if the old_token is correct
	 * @return boolean defining the success of this operation
	 */
	public boolean setPassword(String old_token, String new_token) {
		// Confirm user password before change
		if(checkToken(old_token)) {
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
	
	/**
	 * Utilitary function to check token without exposing sensitive private variable this.token
	 * @param token a String containing the token to be compared to the one stored in this User instance.
	 * @return boolean containing the result of this comparison
	 */
	public boolean checkToken(String token) {
		return token.equals(this.token);
	}
	
	/**
	 * marks User instance as authenticated if password token is correct
	 * @param token the current token representing this user's password
	 * @return boolean defining the success of this authentication operation
	 */
	public boolean logIn(String token) {
		if(checkToken(token)) {
			authenticated = true;
			return true;
		}
		return false;
	}
	
	/**
	 * Updates this User instance's authentication status to false
	 */
	public void logOut() {
		authenticated = false;
	}
	
	/**
	 * More readable getter for authentication status
	 * @return this instance's authentication status
	 */
	public boolean isAuthenticated() {
		return authenticated;
	}
}
