package utn.tacs.grupo3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import utn.tacs.grupo3.model.ListOfPlaces;
import utn.tacs.grupo3.repository.exception.DocumentNotFoundException;
import utn.tacs.grupo3.repository.mongo.UserRepository;
import utn.tacs.grupo3.service.ListOfPlacesService;
import utn.tacs.grupo3.service.exception.ApiTacsException;

@Service
public class ListOfPlacesServiceImpl implements ListOfPlacesService{

	@Autowired
    private UserRepository userRepository;

	@Override
	public List<ListOfPlaces> allUserListsOfPlaces(String username) {
		try {
			return userRepository.userByUsername(username).getListsOfPlaces();			
		} catch (DocumentNotFoundException e) {
			throw new ApiTacsException("Username not found", HttpStatus.NOT_FOUND, e);
		}
	}

	@Override
	public void createListOfPlaces(String username, String listName) {
		userRepository.createListOfPlaces(username, listName);		
	}

	@Override
	public ListOfPlaces userListOfPlacesByName(String username, String listName) {
		return userRepository.findListOfPlaces(username, listName);
	}

	@Override
	public void deleteUserListOfPlaces(String username, String listName) {
		userRepository.deleteListOfPlaces(username, listName);
	}

	@Override
	public void renameListOfPlaces(String username, String listName, String newName) {
		userRepository.renameListOfPlaces(username, listName, newName);		
	}

}
