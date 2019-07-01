package utn.tacs.grupo3.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utn.tacs.grupo3.model.Coordinates;
import utn.tacs.grupo3.model.Place;
import utn.tacs.grupo3.model.RegisteredPlace;
import utn.tacs.grupo3.model.User;
import utn.tacs.grupo3.repository.mongo.RegisteredPlaceRepository;
import utn.tacs.grupo3.repository.mongo.UserRepository;
import utn.tacs.grupo3.retrofit.FoursquarePlacesRequest;
import utn.tacs.grupo3.retrofit.pojo.venue.FullVenue;
import utn.tacs.grupo3.service.UserService;
import utn.tacs.grupo3.service.helper.TodayHelper;
import utn.tacs.grupo3.service.validation.ServiceValidation;

@Service
public class UserServiceImpl implements UserService {
	
    @Autowired
    private FoursquarePlacesRequest foursquarePlacesRequest;
    @Autowired
    private RegisteredPlaceRepository registeredPlaceRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TodayHelper todayHelper;
    @Autowired
    private ServiceValidation serviceValidation;

	@Override
	public List<User> allUsers() {
		return userRepository.findAll();
	}

	@Override
	public void markAPlaceInAUserListAsVisited(String username, String listName, String foursquareId) {
		serviceValidation.checkIfUserNotExists(username);
		serviceValidation.checkIfListNotExists(username, listName);
		serviceValidation.checkIfPlaceNotExistsInList(username, listName, foursquareId);
		
		userRepository.markAPlaceAsVisited(username, listName, foursquareId);
	}

	@Override
	public void registerAPlaceInAUserList(String username, String listName, String foursquareId) {
    	serviceValidation.checkIfUserNotExists(username);
    	serviceValidation.checkIfListNotExists(username, listName);
    	serviceValidation.checkIfPlaceExistsInList(username, listName, foursquareId);
		
		FullVenue venue = foursquarePlacesRequest.getVenueById(foursquareId);
    	Place place = venueToPlace(venue);
    	
    	userRepository.addPlaceToListOfPlaces(username, listName, place);
    	
    	if (registeredPlaceRepository.existsBy("foursquareId", foursquareId)) {
			RegisteredPlace registeredPlace = registeredPlaceRepository.findBy("foursquareId", foursquareId).get(0);
			registeredPlaceRepository.addInterestedUser(registeredPlace.getId(), username);
		} else {
			registeredPlaceRepository.save(getRegisteredPlace(venue, username));
		}
	}

	@Override
	public User userByUsername(String username) {
		return userRepository.userByUsername(username);
	}

	private RegisteredPlace getRegisteredPlace(FullVenue venue, String username) {
    	RegisteredPlace registeredPlace = new RegisteredPlace();
    	registeredPlace.setName(venue.getName());
    	registeredPlace.setAddress(venue.getLocation().getAddress());
    	registeredPlace.setRegistrationDate(todayHelper.today());
    	registeredPlace.setFoursquareId(venue.getId());
    	registeredPlace.setCoordinates(new Coordinates(venue.getLocation().getLat().floatValue(), venue.getLocation().getLng().floatValue()));
    	registeredPlace.setUsersWhoMarkedAsFavourite(Arrays.asList(username));
    	
    	return registeredPlace;    	
    }
    
	private Place venueToPlace(FullVenue venue) {
		Place place = new Place();
		
    	place.setName(venue.getName());
    	place.setAddress(venue.getLocation().getAddress());
    	place.setFoursquareId(venue.getId());
    	place.setVisited(Boolean.FALSE);
    	place.setCoordinates(
    			new Coordinates(
    					venue.getLocation().getLat().floatValue(), 
    					venue.getLocation().getLng().floatValue())
    			);
		
    	return place;
	}

	@Override
	public void updateUserLastAccess(String username) {
		userRepository.updateUserLastAccess(username, todayHelper.today());
	}


}
