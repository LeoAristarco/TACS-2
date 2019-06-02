package utn.tacs.grupo3.telegram.bot.request.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"token"})
public class LoginResponse {
	
	@JsonProperty("token")
	private String token;

	@JsonProperty("token")
	public String getToken() {
		return token;
	}
	@JsonProperty("token")
	public void setToken(String token) {
		this.token = token;
	}
	
	

}
