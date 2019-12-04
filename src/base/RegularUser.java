package base;

public class RegularUser extends User {
	// User preferences
	int minimum_star_requirement = 3;
	int total_bookings = 0;
	
	public RegularUser(String username, String password) {
		super(username, password);
	}
	
	public void setRequirement(int stars) {
		// Validate user's star requirement
		if(stars < 1)
			stars = 1;
		else if (stars > 5)
			stars = 5;
				
		minimum_star_requirement = stars;
	}
	
	public void incrementBookings() {
		total_bookings++;
	}
	
	public void incrementBookings(int n) {
		total_bookings += n;
	}
}
