package utn.tacs.grupo3.spring.validations;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.google.gson.Gson;

import utn.tacs.grupo3.spring.controller.response.Response;

public class InvalidOrNonExistingJwtTokenHandler implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		Gson gson = new Gson();
		response.setStatus(401);
		response.addHeader("Content-type", "application/json");
		response.getWriter().write(gson.toJson(new Response(HttpStatus.UNAUTHORIZED, "Unauthorized to perform the requested action")));
		
	}
}
