package utn.tacs.grupo3.spring.controller.response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;

public class Response {
    private int status;
    private String message;
    private Object body;

    public Response(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
        this.body = "";
    }

    public Response(HttpStatus status, String message, Object body) {
        super();
        this.status = status.value();
        this.message = message;
        this.body = body;
    }

    public Response(int status, String message) {
        this.status = status;
        this.message = message;
        this.body = "";
    }


    public String toJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
