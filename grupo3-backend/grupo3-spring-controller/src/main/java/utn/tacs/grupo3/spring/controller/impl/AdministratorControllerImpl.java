package utn.tacs.grupo3.spring.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import utn.tacs.grupo3.service.AdministratorService;
import utn.tacs.grupo3.spring.controller.AdministratorController;
import utn.tacs.grupo3.spring.controller.response.Response;
import utn.tacs.grupo3.spring.controller.response.ResponseHandler;
import utn.tacs.grupo3.spring.security.token.ValidateToken;

@RestController
@RequestMapping("/administrator")
public class AdministratorControllerImpl implements AdministratorController {

	@Autowired
	private AdministratorService administratorService;
	@Autowired
	private ResponseHandler responseHandler;

    @Override
    @GetMapping("users/{user-id}")
    public ResponseEntity<Response> userById(@PathVariable("user-id") String userId){
    	return responseHandler.handle(
    			() -> new Response(
    					HttpStatus.OK, 
    					"User", 
    					administratorService.userById(userId)));
    }

    @Override
    @GetMapping("places/{place-id}/interested-users")
    public ResponseEntity<Response> interestedUsers(@PathVariable("place-id") String placeId){
    	return responseHandler.handle(
    			() -> new Response(
    					HttpStatus.OK, 
    					"Interested users in a place", 
    					administratorService.interestedUsersInAPlace(placeId)));   	
    }

    @Override
    @GetMapping("places/registered-places")
    public ResponseEntity<Response> registeredPlaces(@RequestParam("days") int days) {
    	return responseHandler.handle(
    			() -> new Response(
    					HttpStatus.OK, 
    					"Registered places", 
    					administratorService.registeredPlaces(days)));   	
    	
    }

	@Override
    @GetMapping("places/places-in-common")
	public ResponseEntity<Response> placesInCommon(
			@RequestParam("user-id-1")String userId1, 
			@RequestParam("list-id-1")String listName1,
			@RequestParam("user-id-2")String userId2, 
			@RequestParam("list-id-2")String listName2) {
    	return responseHandler.handle(
    			() -> new Response(
    					HttpStatus.OK, 
    					"Places in common between two user lists", 
    					administratorService.placesInCommon(userId1, listName1, userId2, listName2)));		
	}
    
	@Override
	@GetMapping("/me")
	public ResponseEntity<Response> me(@RequestHeader("Authorization") String token){
		String username = new ValidateToken().getUserNameFromToken(token);
		return responseHandler.handle(
				() -> new Response(HttpStatus.OK, "Your data", administratorService.userById(username)));
	}
	
	@Override
	@GetMapping("/users")
	public ResponseEntity<Response> users() {
		return responseHandler.handle(
				() -> new Response(HttpStatus.OK, "All users", administratorService.allUsersInfo()));
	}	
}
