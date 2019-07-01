package utn.tacs.grupo3.repository.exception;

public class DocumentNotUniqueException extends RuntimeException{

	private static final long serialVersionUID = 8477395040334012158L;

	public DocumentNotUniqueException(String message, Throwable cause) {
		super(message, cause);
	}

	public DocumentNotUniqueException(String message) {
		super(message);
	}
}
