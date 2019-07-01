package utn.tacs.grupo3.telegram.bot.request.exception;

public class TelegramUserAlreadyLoggedException extends RequestException{

	private static final long serialVersionUID = 5573476171606733982L;

	public TelegramUserAlreadyLoggedException(String message, Throwable cause) {
		super(message, cause);
	}

	public TelegramUserAlreadyLoggedException(String message) {
		super(message);	
	}
}
