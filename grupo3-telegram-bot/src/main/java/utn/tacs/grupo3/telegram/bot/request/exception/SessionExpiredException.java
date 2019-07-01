package utn.tacs.grupo3.telegram.bot.request.exception;

public class SessionExpiredException extends RuntimeException{

	private static final long serialVersionUID = -3708706117847545256L;

	public SessionExpiredException(String message, Throwable cause) {
		super(message, cause);
	}

	public SessionExpiredException(String message) {
		super(message);
	}
}
