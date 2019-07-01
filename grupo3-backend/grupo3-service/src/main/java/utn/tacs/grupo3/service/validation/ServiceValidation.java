package utn.tacs.grupo3.service.validation;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import utn.tacs.grupo3.model.ListOfPlaces;
import utn.tacs.grupo3.repository.mongo.KeyValue;
import utn.tacs.grupo3.repository.mongo.RegisteredPlaceRepository;
import utn.tacs.grupo3.repository.mongo.UserRepository;
import utn.tacs.grupo3.service.exception.ApiTacsException;

@Component
public class ServiceValidation {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RegisteredPlaceRepository registeredPlaceRepository;
	
	public void checkIfListExists(String username, String listName) {
		if (userRepository.existsBy(Arrays.asList(
				KeyValue.pair("username", username),
				KeyValue.pair("listsOfPlaces.listName", listName)))) {
			throw new ApiTacsException("User already has a list named ["+listName+"]", HttpStatus.CONFLICT);
		}
	}
	
	public void checkIfListNotExists(String username, String listName) {
		if (!userRepository.existsBy(Arrays.asList(
				KeyValue.pair("username", username),
				KeyValue.pair("listsOfPlaces.listName", listName)))) {
			throw new ApiTacsException("User ["+ username +"] does not have a list named ["+listName+"]", HttpStatus.NOT_FOUND);
		}
	}	

	public void checkIfUserNotExists(String username) {
		if (!userRepository.existsBy("username", username)) {
			throw new ApiTacsException("User not exists ["+username+"]", HttpStatus.NOT_FOUND);
		}
	}
	
	public void checkIfPlaceExistsInList(String username, String listName, String foursquareId) {
		ListOfPlaces list = userRepository.findListOfPlaces(username, listName);
		
		if (list.getPlaces().stream().anyMatch(place -> place.getFoursquareId().equals(foursquareId))) {
			throw new ApiTacsException("Place with foursquareId ["+ foursquareId+"] already exists in list ["+ listName +"]", HttpStatus.CONFLICT);
		}		
	}
	
	public void checkIfPlaceNotExistsInList(String username, String listName, String foursquareId) {
		ListOfPlaces list = userRepository.findListOfPlaces(username, listName);
		
		if (!list.getPlaces().stream().anyMatch(place -> place.getFoursquareId().equals(foursquareId))) {
			throw new ApiTacsException("Place with foursquareId ["+ foursquareId+"] does not exist in list ["+ listName +"]", HttpStatus.NOT_FOUND);
		}		
	}

	
	public void checkIfRegisteredPlaceNotExists(String foursquareId) {
		if (!registeredPlaceRepository.existsBy("foursquareId", foursquareId)) {
			throw new ApiTacsException("Registered place with foursquareId [" + foursquareId + "] does not exist", HttpStatus.NOT_FOUND);
		}
	}
	
	public void checkIfDaysIsLessThanZero(int days) {
		if (days < 0) {
			throw new ApiTacsException("Days must be equal or greater than zero", HttpStatus.BAD_REQUEST);
		}
	}
}
