package utn.tacs.grupo3.telegram.bot.request.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import utn.tacs.grupo3.telegram.bot.request.entity.ListOfPlaces;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "status", "message", "body" })
public class ListOfPlacesResponse {

	@JsonProperty("status")
	private Integer status;
	@JsonProperty("message")
	private String message;
	@JsonProperty("body")
	private ListOfPlaces listOfPlaces;

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
	public ListOfPlaces getListOfPlaces() {
		return listOfPlaces;
	}

	@JsonProperty("body")
	public void setListOfPlaces(ListOfPlaces listOfPlaces) {
		this.listOfPlaces = listOfPlaces;
	}

}
