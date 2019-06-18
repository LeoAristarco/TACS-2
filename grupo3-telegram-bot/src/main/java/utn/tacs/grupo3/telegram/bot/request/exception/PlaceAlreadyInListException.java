package utn.tacs.grupo3.telegram.bot.request.exception;

public class PlaceAlreadyInListException extends RequestException{

	private static final long serialVersionUID = -102533429399998997L;

	public PlaceAlreadyInListException(String message, Throwable cause) {
		super(message, cause);
	}

	public PlaceAlreadyInListException(String message) {
		super(message);
	}
}
