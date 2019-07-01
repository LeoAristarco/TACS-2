package utn.tacs.grupo3.model.exception.signUpValidation;

public class ExceptionbyRepeatedUserName extends ExceptionbySignUpValidation  {
    public ExceptionbyRepeatedUserName() {
        super("username ya existe");
    }
}
