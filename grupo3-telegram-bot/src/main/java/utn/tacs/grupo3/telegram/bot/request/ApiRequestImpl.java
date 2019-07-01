package utn.tacs.grupo3.telegram.bot.request;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import utn.tacs.grupo3.telegram.bot.request.entity.ListOfPlaces;
import utn.tacs.grupo3.telegram.bot.request.entity.Venue;
import utn.tacs.grupo3.telegram.bot.request.exception.BadCredentialsException;
import utn.tacs.grupo3.telegram.bot.request.exception.ListNotFoundException;
import utn.tacs.grupo3.telegram.bot.request.exception.PlaceAlreadyInListException;
import utn.tacs.grupo3.telegram.bot.request.exception.TelegramUserAlreadyLoggedException;
import utn.tacs.grupo3.telegram.bot.request.exception.TelegramUserNotLoggedException;
import utn.tacs.grupo3.telegram.bot.request.exception.UnknownRequestException;
import utn.tacs.grupo3.telegram.bot.request.response.ListOfPlacesResponse;
import utn.tacs.grupo3.telegram.bot.request.response.ListsOfPlacesResponse;
import utn.tacs.grupo3.telegram.bot.request.response.LoginResponse;
import utn.tacs.grupo3.telegram.bot.request.response.VenuesResponse;
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
	
	private static final String UNKNOWN_ERROR_MESSAGE = "An error occured. Please try again later";
	private static final String PLACE_IN_LIST_ERROR_MESSAGE = "The place is already in the list";
	private static final String LIST_NOT_FOUND_ERROR_MESSAGE = "The list does not exists or was deleted";
	
	private RestTemplate rest = new RestTemplate();
		
	@Override
	public String login(UserCredentials user, Integer telegramUserId) throws BadCredentialsException {
		checkUserNotLogged(user.getUsername(), telegramUserId);
		
		String uri = new URIBuilder()
				.setBaseUri(API_BASE_URL)
				.setRelativeUri(LOGIN)
				.build();
		
		try {
			String token = rest.exchange(uri, HttpMethod.POST, new HttpEntity<UserCredentials>(user), LoginResponse.class)
					.getBody().getToken();
			
			LoggedUsers.addLoggedUser(telegramUserId, user.getUsername(), token);
			
			return token;
			
			
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			switch (e.getStatusCode()) {
			case UNAUTHORIZED:
				throw new BadCredentialsException("Invalid username or password. Status code [" + e.getRawStatusCode() + "]", e);
			default:
				throw new UnknownRequestException(UNKNOWN_ERROR_MESSAGE);
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
		
		try {
			ResponseEntity<ListsOfPlacesResponse> lists = rest.exchange(
					uri,
					HttpMethod.GET, createHeaders(telegramUserId),
					ListsOfPlacesResponse.class);		
			return lists.getBody().getListOfPlaces().stream().map(list -> list.getListName()).collect(Collectors.toList());			
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			throw new UnknownRequestException(UNKNOWN_ERROR_MESSAGE);
		}		
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
		
		try {
			ResponseEntity<VenuesResponse> venuesNearLocation = rest.exchange(
					uri, 
					HttpMethod.GET,
					createHeaders(telegramUserId),
					VenuesResponse.class);		
			return venuesNearLocation.getBody().getVenues();			
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			throw new UnknownRequestException(UNKNOWN_ERROR_MESSAGE);
		}		
	}


	@Override
	public List<Venue> searchPlacesByName(String name, Integer telegramUserId) {
		checkUserLogged(telegramUserId);
		
		String uri = new URIBuilder()
				.setBaseUri(API_BASE_URL)
				.setRelativeUri(PLACES_BY_NAME)
				.setParameter(":name", name)
				.build();
		try {
			ResponseEntity<VenuesResponse> venuesByName = rest.exchange(
					uri,
					HttpMethod.GET, 
					createHeaders(telegramUserId), 
					VenuesResponse.class);		
			return venuesByName.getBody().getVenues();
			
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			throw new UnknownRequestException(UNKNOWN_ERROR_MESSAGE);
		}		
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
		
		try {
			ResponseEntity<String> response = rest.exchange(
					uri,
					HttpMethod.POST,
					createHeaders(telegramUserId),
					String.class);
			response.getBody();
			
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			switch (e.getStatusCode()) {
			case CONFLICT:
				throw new PlaceAlreadyInListException(PLACE_IN_LIST_ERROR_MESSAGE);
			case NOT_FOUND:
				throw new ListNotFoundException(LIST_NOT_FOUND_ERROR_MESSAGE);
			default:
				throw new UnknownRequestException(UNKNOWN_ERROR_MESSAGE);		
			}
		}
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
		try {
			ResponseEntity<ListOfPlacesResponse> listOfPlaces = rest.exchange(
					uri,
					HttpMethod.GET,
					createHeaders(telegramUserId),
					ListOfPlacesResponse.class);			
			return listOfPlaces.getBody().getListOfPlaces();
			
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			switch (e.getStatusCode()) {
			case NOT_FOUND:
				throw new ListNotFoundException(LIST_NOT_FOUND_ERROR_MESSAGE);
			default:
				throw new UnknownRequestException(UNKNOWN_ERROR_MESSAGE);
			}

		}		
		
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
	
	private void checkUserNotLogged(String username, Integer telegramUserId) {
		if (LoggedUsers.isLogged(telegramUserId)) {
			String currentUsername = LoggedUsers.getUsername(telegramUserId);
			
			if (currentUsername.equals(username)) {
				throw new TelegramUserAlreadyLoggedException("You are already logged");
			} else {
				throw new TelegramUserAlreadyLoggedException("You are already logged with a different user. Please logout first");
			}			
		}
	}

}
