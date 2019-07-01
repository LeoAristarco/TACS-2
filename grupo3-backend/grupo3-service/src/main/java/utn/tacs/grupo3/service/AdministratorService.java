package utn.tacs.grupo3.service;

import java.util.List;

import utn.tacs.grupo3.model.Place;
import utn.tacs.grupo3.model.RegisteredPlace;
import utn.tacs.grupo3.model.User;
import utn.tacs.grupo3.model.dto.UserInfo;

public interface AdministratorService {
	
	User userById(String username);
	
	List<String> interestedUsersInAPlace(String placeId);
	
	List<RegisteredPlace> registeredPlaces(int days);
	
	List<Place> placesInCommon(String username1, String listName1, String username2, String listName2);

	List<UserInfo> allUsersInfo();

}
