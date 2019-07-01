package utn.tacs.grupo3.telegram.bot.request.exception;

public class RequestException extends RuntimeException{

	private static final long serialVersionUID = -1708746115865564824L;

	public RequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public RequestException(String message) {
		super(message);
	}
}
