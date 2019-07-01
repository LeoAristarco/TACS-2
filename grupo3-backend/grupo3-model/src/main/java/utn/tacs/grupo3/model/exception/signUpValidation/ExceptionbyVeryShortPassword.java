package utn.tacs.grupo3.model.exception.signUpValidation;

public class ExceptionbyVeryShortPassword extends ExceptionbySignUpValidation  {
    public ExceptionbyVeryShortPassword() {
        super("password debe ser mayor a 2 caracteres");
    }
}
