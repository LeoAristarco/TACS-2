package utn.tacs.grupo3.model.exception.signUpValidation;

public class ExceptionbyVeryshortUserName extends ExceptionbySignUpValidation  {
    public ExceptionbyVeryshortUserName() {
        super("username debe ser mayor a 2 caracteres");
    }
}
