package utn.tacs.grupo3.telegram.bot.request.exception;

public class TelegramUserNotLoggedException extends RuntimeException{

	private static final long serialVersionUID = -897042597475165017L;

	public TelegramUserNotLoggedException(String message, Throwable cause) {
		super(message, cause);	
	}

	public TelegramUserNotLoggedException(String message) {
		super(message);	
	}
}
