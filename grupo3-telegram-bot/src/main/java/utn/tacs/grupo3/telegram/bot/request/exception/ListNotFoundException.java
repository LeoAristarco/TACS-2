package utn.tacs.grupo3.telegram.bot.request.exception;

public class ListNotFoundException extends RequestException{

	private static final long serialVersionUID = 6649749323417071560L;

	public ListNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ListNotFoundException(String message) {
		super(message);
	}

}
