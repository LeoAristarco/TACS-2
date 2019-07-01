package utn.tacs.grupo3.service.exception;

import org.springframework.http.HttpStatus;

public class ApiTacsException extends RuntimeException{

	private static final long serialVersionUID = 5971344933033427463L;

	protected HttpStatus httpStatus;
	
	public ApiTacsException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApiTacsException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}
	
	public ApiTacsException(String message, HttpStatus httpStatus, Throwable cause) {
		super(message, cause);
		this.httpStatus = httpStatus;
	}
	

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}	

}
