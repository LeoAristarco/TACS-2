package utn.tacs.grupo3.spring.controller;

import org.springframework.http.ResponseEntity;

import utn.tacs.grupo3.spring.controller.response.Response;

/**
 * Interface for user-related controller methods
 */
public interface UserController {

    /**
     * Returns all registered users
     *
     * @return a list of users
     */
    ResponseEntity<Response> users();

    /**
     * Register a new place in an user's list of places
     *
     * @param userId
     * @param listId
     * @param placeId
     */
    ResponseEntity<Response> registerPlaceInListOfPlaces(String userId, String listId, String placeId);


    /**
     * Marks a place as visited
     *
     * @param userId
     * @param placeId
     */
    ResponseEntity<Response> markAsVisitedAPlace(String userId, String listId, String placeId);
    
    /**
     * Returns the data of the current user
     * @param token
     * @return
     */
    ResponseEntity<Response> me(String token);

}