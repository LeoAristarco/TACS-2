package utn.tacs.grupo3.telegram.bot.exception;

public class TelegramBotException extends RuntimeException{

	private static final long serialVersionUID = -8066273182742016969L;
	
	public TelegramBotException(String message) {
		super(message);
	}

	public TelegramBotException(String message, Throwable cause) {
		super(message, cause);
	}	

}
