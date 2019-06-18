package utn.tacs.grupo3.telegram.bot.request;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import utn.tacs.grupo3.telegram.bot.request.entity.ListOfPlaces;
import utn.tacs.grupo3.telegram.bot.request.entity.LoginResponse;
import utn.tacs.grupo3.telegram.bot.request.entity.Venue;
import utn.tacs.grupo3.telegram.bot.request.exception.BadCredentialsException;
import utn.tacs.grupo3.telegram.bot.request.exception.TelegramUserNotLoggedException;
import utn.tacs.grupo3.telegram.bot.user.LoggedUsers;
import utn.tacs.grupo3.telegram.bot.user.UserCredentials;

public class ApiRequestImpl implements ApiRequest{
	
	private static final String API_BASE_URL = "http://localhost:8080";
	
	private static final String NEAR_PLACES = "/places/near?coordinates=:lat,:long";
	private static final String USER_LISTS_OF_PLACES = "/users/:user-id/list-of-places";
	private static final String PLACES_BY_NAME = "/places/near-by-name?name=:name";
	private static final String LIST_BY_NAME = "/users/:user-id/list-of-places/:list-name";
	private static final String ADD_PLACE_TO_SELECTED_LIST = "/users/:user-id/list-of-places/:list-name/:place-id";
	private static final String LOGIN = "/login";
		
	private static final String AUTHORIZATION_HEADER = "Authorization";
	
	private RestTemplate rest = new RestTemplate();
		
	@Override
	public String login(UserCredentials user, Integer telegramUserId) throws BadCredentialsException {
		String uri = new URIBuilder()
				.setBaseUri(API_BASE_URL)
				.setRelativeUri(LOGIN)
				.build();
		
		try {
			String token = rest.exchange(uri, HttpMethod.POST, new HttpEntity<UserCredentials>(user), LoginResponse.class)
					.getBody().getToken();
			
			LoggedUsers.addLoggedUser(telegramUserId, user.getUsername(), token);
			
			return token;
			
			
		} catch (HttpClientErrorException e) {
			switch (e.getStatusCode()) {
			case FORBIDDEN:
				throw new BadCredentialsException("Invalid username or password. Status code [" + e.getRawStatusCode() + "]", e);
			default:
				throw e;
			}
		}
		
	}
	
	
	@Override
	public void logout(Integer telegramUserId) {
		LoggedUsers.removeLoggedUser(telegramUserId);
	}

	@Override
	public List<String> listNames(Integer telegramUserId){
		checkUserLogged(telegramUserId);
		
		String uri = new URIBuilder()
				.setBaseUri(API_BASE_URL)
				.setRelativeUri(USER_LISTS_OF_PLACES)
				.setParameter(":user-id", LoggedUsers.getUsername(telegramUserId))
				.build();
		
		ResponseEntity<List<ListOfPlaces>> lists = rest.exchange(
				uri,
				HttpMethod.GET, createHeaders(telegramUserId),
				new ParameterizedTypeReference<List<ListOfPlaces>>() {});
		
		return lists.getBody().stream().map(list -> list.getListName()).collect(Collectors.toList());
	}


	@Override
	public List<Venue> near(float latitude, float longitude, Integer telegramUserId) {
		checkUserLogged(telegramUserId);
		
		String uri = new URIBuilder()
				.setBaseUri(API_BASE_URL)
				.setRelativeUri(NEAR_PLACES)
				.setParameter(":lat", latitude)
				.setParameter(":long", longitude)
				.build();
		
		ResponseEntity<List<Venue>> venuesNearLocation = rest.exchange(
				uri, 
				HttpMethod.GET,
				createHeaders(telegramUserId),
				new ParameterizedTypeReference<List<Venue>>() {});
		
		return venuesNearLocation.getBody();
	}


	@Override
	public List<Venue> searchPlacesByName(String name, Integer telegramUserId) {
		checkUserLogged(telegramUserId);
		
		String uri = new URIBuilder()
				.setBaseUri(API_BASE_URL)
				.setRelativeUri(PLACES_BY_NAME)
				.setParameter(":name", name)
				.build();
		
		ResponseEntity<List<Venue>> venuesByName = rest.exchange(
				uri,
				HttpMethod.GET, 
				createHeaders(telegramUserId), 
				new ParameterizedTypeReference<List<Venue>>() {});
		
		return venuesByName.getBody();
	}


	@Override
	public void addPlaceToList(String listName, String placeId, Integer telegramUserId) {
		checkUserLogged(telegramUserId);
		
		String uri = new URIBuilder()
				.setBaseUri(API_BASE_URL)
				.setRelativeUri(ADD_PLACE_TO_SELECTED_LIST)
				.setParameter(":user-id", LoggedUsers.getUsername(telegramUserId))
				.setParameter(":list-name", listName)
				.setParameter(":place-id", placeId)
				.build();
		
		ResponseEntity<String> response = rest.exchange(
				uri,
				HttpMethod.POST,
				createHeaders(telegramUserId),
				String.class);
		response.getBody();
	}

	
	@Override
	public ListOfPlaces listByName(String listName, Integer telegramUserId) {
		checkUserLogged(telegramUserId);
		
		String uri = new URIBuilder()
				.setBaseUri(API_BASE_URL)
				.setRelativeUri(LIST_BY_NAME)
				.setParameter(":user-id", LoggedUsers.getUsername(telegramUserId))
				.setParameter(":list-name", listName)
				.build();
		
		ResponseEntity<ListOfPlaces> listOfPlaces = rest.exchange(
				uri,
				HttpMethod.GET,
				createHeaders(telegramUserId),
				ListOfPlaces.class);
		
		return listOfPlaces.getBody();
	}
	
	private HttpEntity<String> createHeaders(Integer telegramUserId) {
		
		HttpHeaders header = new HttpHeaders();
		header.add(AUTHORIZATION_HEADER, LoggedUsers.getToken(telegramUserId));
		HttpEntity<String> entity = new HttpEntity<String>(header);
		
		return entity;
	}
	
	private void checkUserLogged(Integer telegramUserId) {
		if (!LoggedUsers.isLogged(telegramUserId)) {
			throw new TelegramUserNotLoggedException("Telegram user [Id=" + telegramUserId + "] should be logged.");
		}
	}

}
