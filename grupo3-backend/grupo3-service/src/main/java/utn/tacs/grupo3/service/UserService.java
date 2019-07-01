package utn.tacs.grupo3.service;

import java.util.List;

import utn.tacs.grupo3.model.User;

public interface UserService {
	
	List<User> allUsers();
	
	void markAPlaceInAUserListAsVisited(String username, String listName, String foursquareId);
	
	void registerAPlaceInAUserList(String username, String listName, String foursquareId);
	
	User userByUsername(String username);
	
	void updateUserLastAccess(String username);
	
}
