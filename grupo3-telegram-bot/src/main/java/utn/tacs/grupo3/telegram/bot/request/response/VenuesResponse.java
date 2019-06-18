package utn.tacs.grupo3.telegram.bot.request.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import utn.tacs.grupo3.telegram.bot.request.entity.Venue;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "status", "message", "body" })
public class VenuesResponse {

	@JsonProperty("status")
	private Integer status;
	@JsonProperty("message")
	private String message;
	@JsonProperty("body")
	private List<Venue> venues = null;

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
	public List<Venue> getVenues() {
		return venues;
	}

	@JsonProperty("body")
	public void setVenues(List<Venue> venues) {
		this.venues = venues;
	}

}
