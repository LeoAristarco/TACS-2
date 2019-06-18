package utn.tacs.grupo3.telegram.bot.request.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "name", "address", "coordinates", "foursquareId", "visited" })
public class Place {

	@JsonProperty("name")
	private String name;
	@JsonProperty("address")
	private String address;
	@JsonProperty("coordinates")
	private Coordinates coordinates;
	@JsonProperty("foursquareId")
	private String foursquareId;
	@JsonProperty("visited")
	private Boolean visited;

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("address")
	public String getAddress() {
		return address;
	}

	@JsonProperty("address")
	public void setAddress(String address) {
		this.address = address;
	}

	@JsonProperty("coordinates")
	public Coordinates getCoordinates() {
		return coordinates;
	}

	@JsonProperty("coordinates")
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	@JsonProperty("foursquareId")
	public String getFoursquareId() {
		return foursquareId;
	}

	@JsonProperty("foursquareId")
	public void setFoursquareId(String foursquareId) {
		this.foursquareId = foursquareId;
	}

	@JsonProperty("visited")
	public Boolean getVisited() {
		return visited;
	}

	@JsonProperty("visited")
	public void setVisited(Boolean visited) {
		this.visited = visited;
	}

}