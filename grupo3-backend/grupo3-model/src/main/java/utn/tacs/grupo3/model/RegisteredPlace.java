package utn.tacs.grupo3.model;

import java.time.LocalDate;
import java.util.List;

public class RegisteredPlace {
	
	private String id;
	private String name;
	private String address;
	private LocalDate registrationDate;
	private Coordinates coordinates;
	private String foursquareId;
	private List<String> usersWhoMarkedAsFavourite;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public LocalDate getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}
	public Coordinates getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	public String getFoursquareId() {
		return foursquareId;
	}
	public void setFoursquareId(String foursquareId) {
		this.foursquareId = foursquareId;
	}
	public List<String> getUsersWhoMarkedAsFavourite() {
		return usersWhoMarkedAsFavourite;
	}
	public void setUsersWhoMarkedAsFavourite(List<String> usersWhoMarkedAsFavourite) {
		this.usersWhoMarkedAsFavourite = usersWhoMarkedAsFavourite;
	}
}
