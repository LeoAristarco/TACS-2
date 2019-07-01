package utn.tacs.grupo3.spring.controller;

import org.springframework.http.ResponseEntity;

import utn.tacs.grupo3.spring.controller.response.Response;

public interface ListOfPlacesController {

    /**
     * Returns all lists of list-of-places that belong to a user
     *
     * @param userId
     * @return
     */
    ResponseEntity<Response> listsOfListOfPlaces(String userId);
    
    /**
     * Creates a new list-of-places for a user
     *
     * @param userId
     * @param listId
     */
    ResponseEntity<Response> createListOfPlaces(String userId, String listId);

    /**
     * Returns a specific list-of-places that belongs to a user
     *
     * @param userId
     * @param listId
     * @return
     */
    ResponseEntity<Response> listOfPlacesListById(String userId, String listId);


    /**
     * Deletes a specific list-of-places places that belongs to a user
     *
     * @param userId
     * @param listId
     */
    ResponseEntity<Response> deleteListOfPlacesList(String userId, String listId);


    /**
     * Changes the name of a list-of-places list that belongs to a user
     *
     * @param userId
     * @param listId
     * @param newName
     */
    ResponseEntity<Response> editListOfPlacesList(String userId, String listId, String newName);

}
