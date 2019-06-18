package utn.tacs.grupo3.telegram.bot.request.exception;

public class UnknownRequestException extends RequestException{

	private static final long serialVersionUID = 6205719823603994747L;

	public UnknownRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnknownRequestException(String message) {
		super(message);
	}
}
