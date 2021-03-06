package utn.tacs.grupo3.telegram.bot.request.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "status", "message", "body" })
public class LoginResponse {

	@JsonProperty("status")
	private Integer status;
	@JsonProperty("message")
	private String message;
	@JsonProperty("body")
	private String token;

	@JsonProperty("status")
	public Integer getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(Integer status) {
		this.status = status;
	}

	@JsonProperty("message")
	public String getMessage() {
		return message;
	}

	@JsonProperty("message")
	public void setMessage(String message) {
		this.message = message;
	}

	@JsonProperty("body")
	public String getToken() {
		return token;
	}

	@JsonProperty("body")
	public void setToken(String body) {
		this.token = body;
	}

}
