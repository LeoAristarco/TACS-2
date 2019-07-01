package utn.tacs.grupo3.repository.mongo;

import java.time.LocalDate;

import utn.tacs.grupo3.model.ListOfPlaces;
import utn.tacs.grupo3.model.Place;
import utn.tacs.grupo3.model.User;

public interface UserRepository extends GenericRepository<User, String>{
	
	/**
	 * Returns a user by its username
	 * @param username
	 * @return
	 */
	User userByUsername(String username);

	/**
	 * Creates a new list of places for a user
	 * @param username
	 * @param listName
	 */
	void createListOfPlaces(String username, String listName);
	
	/**
	 * Removes a list of places from a user
	 * @param username
	 * @param listName
	 */
	void deleteListOfPlaces(String username, String listName);
	
	/**
	 * Renames a list of places
	 * @param username
	 * @param actualListName
	 * @param newListName
	 */
	void renameListOfPlaces(String username, String actualListName, String newListName);
	
	/**
	 * Returns a lists of places
	 * @param username
	 * @param listName
	 * @return
	 */
	ListOfPlaces findListOfPlaces(String username, String listName);
	
	/**
	 * Adds a new place to a list of places
	 * @param username
	 * @param listName
	 * @param place
	 */
	void addPlaceToListOfPlaces(String username, String listName, Place place);
	
	/**
	 * Deletes a place from a list of places
	 * @param username
	 * @param listName
	 * @param foursquareId
	 */
	void deletePlaceFromListOfPlaces(String username, String listName, String foursquareId);
	
	/**
	 * Marks a place as visited without deleting it from a list of places
	 * @param username
	 * @param listName
	 * @param foursquareId
	 */
	void markAPlaceAsVisited(String username, String listName, String foursquareId);
	
	/**
	 * Checks if a user exists
	 * @param username
	 * @return
	 */
	boolean userExists(String username);
	
	/**
	 * Updates a user last access attribute after successful login
	 * @param username
	 */
	void updateUserLastAccess(String username, LocalDate date);
}
