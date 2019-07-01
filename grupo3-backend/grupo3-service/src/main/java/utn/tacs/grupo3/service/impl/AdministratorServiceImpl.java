package utn.tacs.grupo3.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utn.tacs.grupo3.model.ListOfPlaces;
import utn.tacs.grupo3.model.Place;
import utn.tacs.grupo3.model.RegisteredPlace;
import utn.tacs.grupo3.model.User;
import utn.tacs.grupo3.model.dto.UserInfo;
import utn.tacs.grupo3.repository.mongo.RegisteredPlaceRepository;
import utn.tacs.grupo3.repository.mongo.UserRepository;
import utn.tacs.grupo3.service.AdministratorService;
import utn.tacs.grupo3.service.helper.TodayHelper;
import utn.tacs.grupo3.service.validation.ServiceValidation;

@Service
public class AdministratorServiceImpl implements AdministratorService{

    @Autowired
    private UserRepository userRepository;    
    @Autowired
    private RegisteredPlaceRepository registeredPlaceRepository;
    @Autowired
    private TodayHelper todayHelper;
    @Autowired
    private ServiceValidation serviceValidation;
	
	@Override
	public User userById(String username) {
		serviceValidation.checkIfUserNotExists(username);
		
		return userRepository.userByUsername(username);
	}

	@Override
	public List<String> interestedUsersInAPlace(String foursquareId) {
		serviceValidation.checkIfRegisteredPlaceNotExists(foursquareId);
		return registeredPlaceRepository.usernamesOfInterestedInPlaceUsers(foursquareId);
	}

	@Override
	public List<RegisteredPlace> registeredPlaces(int days) {
		serviceValidation.checkIfDaysIsLessThanZero(days);
		
    	if (days == 0) {
			return registeredPlaceRepository.findAll();
		} else {
			LocalDate today = todayHelper.today();
			LocalDate initialDate = today.minus(days-1, ChronoUnit.DAYS);
			
			return registeredPlaceRepository.placesRegisteredBetween(initialDate, today);			
		}
	}

	@Override
	public List<Place> placesInCommon(String username1, String listName1, String username2, String listName2) {
		serviceValidation.checkIfUserNotExists(username1);
		serviceValidation.checkIfListNotExists(username1, listName1);
		serviceValidation.checkIfUserNotExists(username2);
		serviceValidation.checkIfListNotExists(username2, listName2);
		
		ListOfPlaces list1 = userRepository.findListOfPlaces(username1, listName1);
		ListOfPlaces list2 = userRepository.findListOfPlaces(username2, listName2);
		
		return list1.placesInCommonWith(list2);
	}
	
	@Override
	public List<UserInfo> allUsersInfo(){
		List<UserInfo> users = new ArrayList<UserInfo>();
		
		List<User> allUsers = userRepository.findBy("role", "USER");
		
		for (User user : allUsers) {
			UserInfo userInfo = new UserInfo();
			userInfo.setUsername(user.getUsername());
			userInfo.setLastAccess(user.getLastAccess());
			userInfo.setAmountOfLists(user.getListsOfPlaces().size());
			long visitedPlaces = user.getListsOfPlaces().stream().mapToLong(list -> list.amountOfVisitedPlaces()).sum();
			userInfo.setAmountOfVisitedPlaces(visitedPlaces);
			
			users.add(userInfo);
		}		
		
		return users;
	}
}
