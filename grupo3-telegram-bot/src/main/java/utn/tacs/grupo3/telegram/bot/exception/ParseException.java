package utn.tacs.grupo3.telegram.bot.exception;

public class ParseException extends TelegramBotException{

	private static final long serialVersionUID = 3972390437418281407L;

	public ParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParseException(String message) {
		super(message);
	}

}
