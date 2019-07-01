package utn.tacs.grupo3.model.exception;

public class HTTPException extends Exception {

    private String message;
    private int statusCode;

    public HTTPException(String message,int statusCode) {
        super();
        this.message = message;
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}