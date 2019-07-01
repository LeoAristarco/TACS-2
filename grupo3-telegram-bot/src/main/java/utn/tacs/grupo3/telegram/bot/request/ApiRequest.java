package utn.tacs.grupo3.telegram.bot.request;

import java.util.List;

import utn.tacs.grupo3.telegram.bot.request.entity.ListOfPlaces;
import utn.tacs.grupo3.telegram.bot.request.entity.Venue;
import utn.tacs.grupo3.telegram.bot.request.exception.BadCredentialsException;
import utn.tacs.grupo3.telegram.bot.user.UserCredentials;

/**
 * Interface for backend api requests
 *
 */
public interface ApiRequest {
		
	String login(UserCredentials user, Integer telegramUserId) throws BadCredentialsException;
	
	void logout(Integer telegramUserId);
	
	List<String> listNames(Integer telegramUserId);	
	
	List<Venue> near(float latitude, float longitude, Integer telegramUserId);
	
	List<Venue> searchPlacesByName(String name, Integer telegramUserId);
	
	void addPlaceToList(String listName, String placeId, Integer telegramUserId);
	
	ListOfPlaces listByName(String listName, Integer telegramUserId);

}
