package utn.tacs.grupo3.spring.controller.response;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import utn.tacs.grupo3.service.exception.ApiTacsException;

@Component
public class ResponseHandler {
	private static final Logger logger = Logger.getLogger(ResponseHandler.class.getName());
	
	public ResponseEntity<Response> handle(ResponseFunction<Response> function) {
		try {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(function.apply());
		} catch (ApiTacsException e) {
    		return ResponseEntity
    				.status(e.getHttpStatus())
    				.body(new Response(e.getHttpStatus(), e.getMessage()));
    	} catch (Exception e) {
    		logger.log(Level.WARNING, e.getMessage(), e);
    		return ResponseEntity
    				.status(HttpStatus.INTERNAL_SERVER_ERROR)
    				.body(new Response(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"));
    	}	}

}
