package utn.tacs.grupo3.spring.exceptionHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import utn.tacs.grupo3.model.exception.HTTPException;
import utn.tacs.grupo3.spring.controller.response.Response;
import utn.tacs.grupo3.spring.converter.ConvertToJson;


@ControllerAdvice
public class ResponseExceptionHandler {

    @SuppressWarnings("rawtypes")
    @ExceptionHandler(HTTPException.class)
    public ResponseEntity handleException(HTTPException e) {
        return ResponseEntity.status(e.getStatusCode()).body(new Response(e.getStatusCode(), e.getMessage()));
    }

}