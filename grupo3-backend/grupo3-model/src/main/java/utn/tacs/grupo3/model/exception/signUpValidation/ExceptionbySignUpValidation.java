package utn.tacs.grupo3.model.exception.signUpValidation;

import utn.tacs.grupo3.model.exception.HTTPException;

public class ExceptionbySignUpValidation extends HTTPException {

    public ExceptionbySignUpValidation(String message) {
        super(message,400);
    }
}
