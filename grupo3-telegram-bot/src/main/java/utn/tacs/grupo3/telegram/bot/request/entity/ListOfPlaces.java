package utn.tacs.grupo3.telegram.bot.request.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "listName", "places" })
public class ListOfPlaces {

	@JsonProperty("listName")
	private String listName;
	@JsonProperty("places")
	private List<Place> places = null;

	@JsonProperty("listName")
	public String getListName() {
		return listName;
	}

	@JsonProperty("listName")
	public void setListName(String listName) {
		this.listName = listName;
	}

	@JsonProperty("places")
	public List<Place> getPlaces() {
		return places;
	}

	@JsonProperty("places")
	public void setPlaces(List<Place> places) {
		this.places = places;
	}

}