package utn.tacs.grupo3.repository.mongo;

import java.time.LocalDate;
import java.util.List;

import utn.tacs.grupo3.model.RegisteredPlace;

public interface RegisteredPlaceRepository extends GenericRepository<RegisteredPlace, String>{
	
	/**
	 * Returns a list of usernames who are interested in a specific place. 
	 * Interested users are the ones who saved a place into a list of places
	 * @param id
	 * @return
	 */
	List<String> usernamesOfInterestedInPlaceUsers(String id);
	
	/**
	 * Returns all places registered in the system between two dates
	 * @param date
	 * @return
	 */
	List<RegisteredPlace> placesRegisteredBetween(LocalDate from, LocalDate to);

	/**
	 * Deletes an interested user from a registered place 
	 * @param id
	 * @param username
	 */
	void deleteInterestedUser(String id, String username);
	
	/**
	 * Adds a new interested user to a registered place
	 * @param id
	 * @param username
	 */
	void addInterestedUser(String id, String username);

	

}
