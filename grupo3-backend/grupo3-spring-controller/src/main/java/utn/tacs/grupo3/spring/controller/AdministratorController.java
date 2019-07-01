package utn.tacs.grupo3.spring.controller;

import org.springframework.http.ResponseEntity;

import utn.tacs.grupo3.spring.controller.response.Response;

public interface AdministratorController {
    /**
     * Searches a user by its id
     *
     * @param userId
     * @return
     */
    ResponseEntity<Response> userById(String userId);
    /**
     * Searches places in common among two lists of places
     *
     * @param listId1
     * @param listId2
     * @return
     */    
    ResponseEntity<Response> placesInCommon(String userId1, String listName1, String userId2, String listName2);

    /**
     * Returns the number of interested users in a place
     *
     * @param placeId
     * @return
     */
    ResponseEntity<Response> interestedUsers(String placeId);

    /**
     * Returns the number of registered places in the last days
     *
     * @param days
     * @return
     */
    ResponseEntity<Response> registeredPlaces(int days);
    
    /**
     * Returns the data of the current admin
     * @param token
     * @return
     */
	ResponseEntity<Response> me(String token);
	
	/**
	 * Returs info about all users
	 * @return
	 */
	ResponseEntity<Response> users();
}
