package inventoryapp.inventoryapp.exceptions;

public class UserException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserException(String exception) {
		super(exception);
	}
}
