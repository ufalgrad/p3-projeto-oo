package base;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Util {
	// Limit instantiation with private constructor
    private Util(){}
    
    private static class InstanceHolder{
        private static final Util INSTANCE = new Util();
    }
    
    // getInstance method that implements singleton behavior
    public static Util getInstance(){
        return InstanceHolder.INSTANCE;
    }
    
    /**
     * Checks if date input matches accepted format (YYYY/MM/DD) and returns a boolean
     * @param stringDate raw String input to be validated
     * @return boolean defining the validation status (pass or failure)
     */
    public boolean validateDateString(String stringDate) {
		return stringDate.length() < 10 || stringDate.length() > 10 || !stringDate.contains("/");
	}
    
	/**
	 * Converts a byte[] result from MessageDigest SHA-256 into a String using Hexadecimal notation
	 * @param hash the byte[] result from MessageDigest
	 * @return a String created manually based on the input array
	 */
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
	
	/**
	 * returns a sha256 hash based on a String password
	 * @param pass the password in plain text
	 * @return a String containing the hexadecimal representation of the hash
	 * @throws NoSuchAlgorithmException
	 * @see bytesToHex()
	 */
	public String hashPassword(String pass) throws NoSuchAlgorithmException {
		final MessageDigest digest = MessageDigest.getInstance("SHA-256");
		final byte[] hashbytes = digest.digest(
		pass.getBytes(StandardCharsets.UTF_8));
		String sha3_256hex = bytesToHex(hashbytes);
		return sha3_256hex;
	}
	
	/**
	 * Returns a string based on a GregorianCalendar that represents a date
	 * @param date the GregorianCalendar containing the date
	 * @return the converted date as a String
	 */
	public String dateToString(GregorianCalendar date) {
    	SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy/MM/dd"); 
	    String dateFormatted = formattedDate.format(date.getTime());
	    return dateFormatted;
    }
}
