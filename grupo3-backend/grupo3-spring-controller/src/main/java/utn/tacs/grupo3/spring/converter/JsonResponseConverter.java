package utn.tacs.grupo3.spring.converter;

import org.springframework.http.HttpStatus;
import utn.tacs.grupo3.spring.controller.response.Response;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonResponseConverter {
    public void convert(HttpServletResponse response, int statusCode, HttpStatus httpStatus, String message, Object body) {
        response.setContentType("application/json");
        Response res = new Response(httpStatus, message, body);
        response.setStatus(statusCode);
        try {
            response.getOutputStream().print(res.toJson());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
