package utn.tacs.grupo3.spring.controller;

import utn.tacs.grupo3.model.User;
import utn.tacs.grupo3.model.exception.signUpValidation.ExceptionbySignUpValidation;
import utn.tacs.grupo3.spring.controller.response.Response;

public interface LoginController {
    /**
     * Registers a new user
     *
     * @param user
     * @return
     */
    Response createUser(User user) throws ExceptionbySignUpValidation;
}
