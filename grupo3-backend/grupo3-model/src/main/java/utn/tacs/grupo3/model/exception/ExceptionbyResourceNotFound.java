package utn.tacs.grupo3.model.exception;

public class ExceptionbyResourceNotFound extends HTTPException {
    public ExceptionbyResourceNotFound(String message) {
        super("no existe el recurso "+ message,404);
    }
}
