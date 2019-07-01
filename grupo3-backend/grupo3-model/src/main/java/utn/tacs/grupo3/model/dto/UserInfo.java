package utn.tacs.grupo3.model.dto;

import java.time.LocalDateTime;

public class UserInfo {
	
	private String username;
	private int amountOfLists;
	private long amountOfVisitedPlaces;
	private LocalDateTime lastAccess;
	
	public UserInfo() {
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAmountOfLists() {
		return amountOfLists;
	}

	public void setAmountOfLists(int amountOfLists) {
		this.amountOfLists = amountOfLists;
	}

	public long getAmountOfVisitedPlaces() {
		return amountOfVisitedPlaces;
	}

	public void setAmountOfVisitedPlaces(long amountOfVisitedPlaces) {
		this.amountOfVisitedPlaces = amountOfVisitedPlaces;
	}

	public LocalDateTime getLastAccess() {
		return lastAccess;
	}

	public void setLastAccess(LocalDateTime lastAccess) {
		this.lastAccess = lastAccess;
	}	
}
