package utn.tacs.grupo3.spring.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import utn.tacs.grupo3.service.UserService;
import utn.tacs.grupo3.spring.controller.UserController;
import utn.tacs.grupo3.spring.controller.response.Response;
import utn.tacs.grupo3.spring.controller.response.ResponseHandler;
import utn.tacs.grupo3.spring.security.token.ValidateToken;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserControllerImpl implements UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private ResponseHandler responseHandler;
    
    @Override
    @GetMapping
    public ResponseEntity<Response> users() {
        return responseHandler.handle(
        		() -> new Response(HttpStatus.OK, "All users", userService.allUsers()));
    }

    @Override
    @PutMapping("/{user-id}/{list-id}/places-visited/{place-id}")
    public ResponseEntity<Response> markAsVisitedAPlace(@PathVariable("user-id") String userId, @PathVariable("list-id") String listId, @PathVariable("place-id") String placeId){
    	return responseHandler.handle(() -> {
    		userService.markAPlaceInAUserListAsVisited(userId, listId, placeId);
    		return new Response(HttpStatus.OK, "Place successfully marked as visited");
    	});
    }

    @Override
    @PostMapping("/{user-id}/list-of-places/{list-id}/{place-id}")
    public ResponseEntity<Response> registerPlaceInListOfPlaces(
    		@PathVariable("user-id") String userId, 
    		@PathVariable("list-id") String listId, 
    		@PathVariable("place-id") String placeId){
    	return responseHandler.handle(() -> {
    			userService.registerAPlaceInAUserList(userId, listId, placeId);
    			return new Response(HttpStatus.OK, "Place successfully registered");
    	});
    }

    @Override
	@GetMapping("/me")
	public ResponseEntity<Response> me(@RequestHeader("Authorization") String token){
		String username = new ValidateToken().getUserNameFromToken(token);
		return responseHandler.handle(
				() -> new Response(HttpStatus.OK, "Your data", userService.userByUsername(username)));
	}
}
