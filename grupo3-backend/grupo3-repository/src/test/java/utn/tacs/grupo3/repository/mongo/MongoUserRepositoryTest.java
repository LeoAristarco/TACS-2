package utn.tacs.grupo3.repository.mongo;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utn.tacs.grupo3.model.ListOfPlaces;
import utn.tacs.grupo3.model.Place;
import utn.tacs.grupo3.model.Role;
import utn.tacs.grupo3.model.User;
import utn.tacs.grupo3.mongo.test.configuration.MongoTestConfiguration;
import utn.tacs.grupo3.repository.exception.DocumentNotFoundException;
import utn.tacs.grupo3.repository.exception.DocumentNotUniqueException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoTestConfiguration.class})
public class MongoUserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MongoOperations mongoOps;
	
	private User juan;
	
	@Before
	public void setUp() {
		if (!mongoOps.collectionExists(User.class)) {
			mongoOps.createCollection(User.class);
		}		
		
		juan = new User();
		juan.setListsOfPlaces(new ArrayList<ListOfPlaces>());
		juan.setRole(Role.USER);
		juan.setFirstName("Juan");
		juan.setLastName("Perez");
		juan.setUsername("JPerez1");		
	}
	
	@After
	public void tearDown() {
		if (mongoOps.collectionExists(User.class)) {
			mongoOps.dropCollection(User.class);			
		}
	}
	
	@Test
	public void saveUserTest() {		
		User savedUser = userRepository.save(juan);
		
		Assert.assertNotNull(savedUser);
		Assert.assertEquals(juan.getRole(), savedUser.getRole());
		Assert.assertEquals(juan.getFirstName(), savedUser.getFirstName());
		Assert.assertEquals(juan.getLastName(), savedUser.getLastName());
		Assert.assertEquals(juan.getUsername(), savedUser.getUsername());
		Assert.assertTrue(savedUser.getListsOfPlaces().isEmpty());
	}
	
	@Test
	public void usernameAlreadyExistsTest() {
		userRepository.save(juan);		
		boolean exists = userRepository.existsBy("username", "JPerez1");
		
		Assert.assertTrue(exists);
	}
	
	@Test(expected = DocumentNotFoundException.class)
	public void usernameNotFoundTest() {
		userRepository.userByUsername("pepe");
	}
	
	@Test(expected = DocumentNotUniqueException.class)
	public void createDuplicatedListOfPlacesTest() {
		userRepository.save(juan);
		userRepository.createListOfPlaces("JPerez1", "Universidades");
		userRepository.createListOfPlaces("JPerez1", "Universidades");		
	}
	
	@Test
	public void deleteListOfPlacesTest() {
		userRepository.save(juan);
		userRepository.createListOfPlaces("JPerez1", "Bancos");
		userRepository.deleteListOfPlaces("JPerez1", "Bancos");
		userRepository.createListOfPlaces("JPerez1", "Bancos");
		userRepository.createListOfPlaces("JPerez1", "Universidades");
	}
	
	@Test
	public void renameListOfPlacesTest() {
		userRepository.save(juan);
		userRepository.createListOfPlaces("JPerez1", "Bancos");
		userRepository.renameListOfPlaces("JPerez1", "Bancos", "B4nc0s");
		
		ListOfPlaces listOfPlaces = userRepository.findListOfPlaces("JPerez1", "B4nc0s");
		
		Assert.assertEquals("B4nc0s", listOfPlaces.getListName());
	}
	
	@Test
	public void getListOfPlacesTest() {
		userRepository.save(juan);
		userRepository.createListOfPlaces("JPerez1", "Bancos");
		
		ListOfPlaces listOfPlaces = userRepository.findListOfPlaces("JPerez1", "Bancos");
		
		Assert.assertEquals("Bancos", listOfPlaces.getListName());
	}
	
	@Test
	public void getNonEmptyListOfPlaces() {
		Place medrano = new Place();
		medrano.setName("UTN Medrano");
		medrano.setAddress("Av. Medrano");
		
		List<Place> places = new ArrayList<Place>();
		places.add(medrano);
		
		ListOfPlaces list = new ListOfPlaces();
		list.setListName("Universidades");
		list.setPlaces(places);
		
		List<ListOfPlaces> lists = new ArrayList<ListOfPlaces>();
		lists.add(list);
		
		juan.setListsOfPlaces(lists);
		
		userRepository.save(juan);
		
		ListOfPlaces juanListOfPlaces = userRepository.findListOfPlaces("JPerez1", "Universidades");
		
		Assert.assertFalse(juanListOfPlaces.getPlaces().isEmpty());
		
	}
	
	@Test(expected = DocumentNotFoundException.class)
	public void tryToFindANonExistingListOfPlaces() {
		userRepository.save(juan);
		userRepository.findListOfPlaces("JPerez1", "Non existing list");
	}
	
	@Test
	public void addANewPlaceToAList() {
		userRepository.save(juan);
		userRepository.createListOfPlaces("JPerez1", "Universidades");
		
		Place medrano = new Place("UTN Medrano", "Av. Medrano 951");
		medrano.setVisited(Boolean.FALSE);
		
		userRepository.addPlaceToListOfPlaces("JPerez1", "Universidades", medrano);
		
		ListOfPlaces listsOfPlaces = userRepository.findListOfPlaces("JPerez1", "Universidades");
		
		Assert.assertTrue(listsOfPlaces.getPlaces().stream().anyMatch(anyPlace -> anyPlace.getName().equals("UTN Medrano")));
	}
	
	@Test
	public void addTwoPlacesToAList() {
		userRepository.save(juan);
		userRepository.createListOfPlaces("JPerez1", "Universidades");
		
		Place medrano = new Place("UTN Medrano", "Av. Medrano 951");
		medrano.setVisited(Boolean.FALSE);
		
		Place campus = new Place("UTN Campus", "Saraza 123");
		campus.setVisited(Boolean.FALSE);
		
		userRepository.addPlaceToListOfPlaces("JPerez1", "Universidades", medrano);
		userRepository.addPlaceToListOfPlaces("JPerez1", "Universidades", campus);
		
		ListOfPlaces listsOfPlaces = userRepository.findListOfPlaces("JPerez1", "Universidades");
		
		Assert.assertTrue(listsOfPlaces.getPlaces().stream().anyMatch(anyPlace -> anyPlace.getName().equals("UTN Medrano")));
		Assert.assertTrue(listsOfPlaces.getPlaces().stream().anyMatch(anyPlace -> anyPlace.getName().equals("UTN Campus")));		
	}
	
	@Test
	public void addPlacesToDifferentLists() {
		userRepository.save(juan);
		userRepository.createListOfPlaces("JPerez1", "Universidades");
		userRepository.createListOfPlaces("JPerez1", "Bancos");
		
		Place medrano = new Place("UTN Medrano", "Av. Medrano 951");
		medrano.setVisited(Boolean.FALSE);
		
		Place campus = new Place("UTN Campus", "Saraza 123");
		campus.setVisited(Boolean.FALSE);
		
		Place bancoNacion = new Place("Banco Nacion", "Av. Alberdi 1234");
		bancoNacion.setVisited(Boolean.FALSE);

		
		userRepository.addPlaceToListOfPlaces("JPerez1", "Universidades", medrano);
		userRepository.addPlaceToListOfPlaces("JPerez1", "Universidades", campus);
		userRepository.addPlaceToListOfPlaces("JPerez1", "Bancos", bancoNacion);
		
		ListOfPlaces universities = userRepository.findListOfPlaces("JPerez1", "Universidades");
		ListOfPlaces banks = userRepository.findListOfPlaces("JPerez1", "Bancos");
		
		Assert.assertTrue(universities.getPlaces().stream().anyMatch(anyPlace -> anyPlace.getName().equals("UTN Medrano")));
		Assert.assertTrue(universities.getPlaces().stream().anyMatch(anyPlace -> anyPlace.getName().equals("UTN Campus")));
		Assert.assertTrue(banks.getPlaces().stream().anyMatch(anyPlace -> anyPlace.getName().equals("Banco Nacion")));
		
	}
	
	@Test
	public void deletePlaceFromList() {
		userRepository.save(juan);
		userRepository.createListOfPlaces("JPerez1", "Universidades");

		Place medrano = new Place("UTN Medrano", "Av. Medrano 951");
		medrano.setFoursquareId("asdf1234");
		
		userRepository.addPlaceToListOfPlaces("JPerez1", "Universidades", medrano);
		userRepository.deletePlaceFromListOfPlaces("JPerez1", "Universidades", "asdf1234");
		
		ListOfPlaces universities = userRepository.findListOfPlaces("JPerez1", "Universidades");
		
		Assert.assertTrue(universities.getPlaces().isEmpty());
	}
	
	@Test
	public void markAPlaceAsVisited() {
		userRepository.save(juan);
		userRepository.createListOfPlaces("JPerez1", "Universidades");

		Place medrano = new Place("UTN Medrano", "Av. Medrano 951");
		medrano.setFoursquareId("asdf1234");
		medrano.setVisited(Boolean.FALSE);
		
		Place campus = new Place("UTN Campus", "Saraza 123");
		campus.setFoursquareId("1234");
		campus.setVisited(Boolean.FALSE);
		
		userRepository.addPlaceToListOfPlaces("JPerez1", "Universidades", medrano);
		userRepository.addPlaceToListOfPlaces("JPerez1", "Universidades", campus);
		
		userRepository.markAPlaceAsVisited("JPerez1", "Universidades", "asdf1234");
		
		ListOfPlaces universities = userRepository.findListOfPlaces("JPerez1", "Universidades");

		Assert.assertTrue(universities.getPlaces().size() == 2);
		Assert.assertTrue(universities.getPlaces()
				.stream()
				.anyMatch(anyPlace -> anyPlace.getName().equals("UTN Medrano") && anyPlace.getVisited()));

	}

}
