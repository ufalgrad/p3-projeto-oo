package base;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Util {
	// method to get hexadecimal string from byte[] hash
	private static String bytesToHex(byte[] hash) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
		    if(hex.length() == 1) 
		    	hexString.append('0');
		    hexString.append(hex);
		}
		return hexString.toString();
	}
	
	public String hashPassword(String pass) throws NoSuchAlgorithmException {
		final MessageDigest digest = MessageDigest.getInstance("SHA-256");
		final byte[] hashbytes = digest.digest(
		pass.getBytes(StandardCharsets.UTF_8));
		String sha3_256hex = bytesToHex(hashbytes);
		return sha3_256hex;
	}
	
	public String dateToString(GregorianCalendar date) {
    	SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy/MM/dd"); 
	    String dateFormatted = formattedDate.format(date.getTime());
	    return dateFormatted;
    }
}
