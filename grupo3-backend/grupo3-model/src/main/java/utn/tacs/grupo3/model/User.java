package utn.tacs.grupo3.model;

import utn.tacs.grupo3.model.exception.ExceptionbyListOfPlaceNotFound;
import utn.tacs.grupo3.model.exception.ExceptionbyResourceNotFound;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class User {

	private String id;
    private String firstName;
    private String lastName;
    private List<ListOfPlaces> listsOfPlaces;
    private List<Place> placesVisited;
    private LocalDateTime lastAccess;
    private String username;
    private String password;
    private String rol;

    public User() {
    }

    public User(String firstName, String lastName, String username, String password, String rol) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.listsOfPlaces = new ArrayList<>();
        this.placesVisited = new ArrayList<>();
        this.rol = rol;
        this.password = password;
    }


    public boolean havePlacesInCommonWith(Place aPlace) {
        return listsOfPlaces.stream().anyMatch(listPlaces -> listPlaces.getPlaces().contains(aPlace));
    }

    public void createListOfPlaces(String name) {
        listsOfPlaces.add(new ListOfPlaces(name));
    }

    public void removeFromListsOfPlaces(ListOfPlaces aListOfPlaces) {
        listsOfPlaces.remove(aListOfPlaces);
    }

    public void markAsVisited(Place aPlace) {
        placesVisited.add(aPlace);
    }

    public int amountOfPlacesLists() {
        return listsOfPlaces.size();
    }

    public int amountOfPlacesVisited() {
        return placesVisited.size();
    }

    public List<ListOfPlaces> listsOfPlacesByName(String name) {
        return listsOfPlaces.stream().
                filter(listOfPlaces -> listOfPlaces.getListName().equalsIgnoreCase(name)).
                collect(Collectors.toList());
    }

    public ListOfPlaces listOfPlacesByName(String name) throws ExceptionbyResourceNotFound {
        return listsOfPlacesByName(name)
                .stream().findFirst()
                .orElseThrow(() -> new ExceptionbyListOfPlaceNotFound(name));
    }


    public void registerAPlaceinAListOfPlaces(String listId, Place place) throws ExceptionbyResourceNotFound {
        listOfPlacesByName(listId).addPlace(place);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<ListOfPlaces> getListsOfPlaces() {
        return listsOfPlaces;
    }

    public void setListsOfPlaces(List<ListOfPlaces> listsOfPlaces) {
        this.listsOfPlaces = listsOfPlaces;
    }

    public List<Place> getPlacesVisited() {
        return placesVisited;
    }

    public void setPlacesVisited(List<Place> placesVisited) {
        this.placesVisited = placesVisited;
    }

    public LocalDateTime getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(LocalDateTime lastAccess) {
        this.lastAccess = lastAccess;
    }

    public String getPassword() {
        return "{noop}"+this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}    
}
