package utn.tacs.grupo3.service;

import java.util.List;

import utn.tacs.grupo3.model.ListOfPlaces;

public interface ListOfPlacesService {
	
	List<ListOfPlaces> allUserListsOfPlaces(String username);
	
	void createListOfPlaces(String username, String listName);
	
	ListOfPlaces userListOfPlacesByName(String username, String listName);
	
	void deleteUserListOfPlaces(String username, String listName);
	
	void renameListOfPlaces(String username, String listName, String newName);

}
