package base;

public class HotelManager extends User {

	public HotelManager(String username, String password) {
		super(username, password);
	}
	
	@Override
	public String getType() {
		return "manager";
	}

}
