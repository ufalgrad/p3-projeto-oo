package base;

public class RegularUser extends User {
	// User preferences
	int minimum_star_requirement = 3;
	int total_bookings = 0;
	
	public RegularUser(String username, String password) {
		super(username, password);
	}
	
	// base.User.UpdateInfo() overload
	public void UpdateInfo(String f_name, String l_name, int stars) {
		first_name = f_name;
		last_name = l_name;
		
		// Validate user's star requirement
		if(stars < 1)
			stars = 1;
		else if (stars > 5)
			stars = 5;
				
		minimum_star_requirement = stars;
	}
	
	public void IncrementBookings() {
		total_bookings++;
	}
	
	public void IncrementBookings(int n) {
		total_bookings += n;
	}
}
