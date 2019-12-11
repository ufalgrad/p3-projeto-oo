package exceptions;

public class NonUniqueColumnException extends Exception{
	private static final long serialVersionUID = 1L;
	public NonUniqueColumnException(String column) {
		super("Column "+column+" with this value already exists in the database.");
	}

}
