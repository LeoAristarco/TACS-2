package utn.tacs.grupo3.repository.exception;

public class DocumentNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 5801772537829830840L;

	public DocumentNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public DocumentNotFoundException(String message) {
		super(message);
	}
}
