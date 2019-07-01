package utn.tacs.grupo3.telegram.bot.exception;

public class UserNotLoggedException extends TelegramBotException{

	private static final long serialVersionUID = -1428129017122540433L;

	public UserNotLoggedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotLoggedException(String message) {
		super(message);
	}
}
