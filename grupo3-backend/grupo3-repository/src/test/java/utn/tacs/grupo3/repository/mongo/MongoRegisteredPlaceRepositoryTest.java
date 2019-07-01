package utn.tacs.grupo3.repository.mongo;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
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

import utn.tacs.grupo3.model.Coordinates;
import utn.tacs.grupo3.model.RegisteredPlace;
import utn.tacs.grupo3.mongo.test.configuration.MongoTestConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MongoTestConfiguration.class)
public class MongoRegisteredPlaceRepositoryTest {

	@Autowired
	private RegisteredPlaceRepository registeredPlaceRepository;
	@Autowired
	private MongoOperations mongoOps;
	
	private RegisteredPlace utnMedrano;
	private LocalDate april4th;
	private String medranoFoursquareId;
	
	@Before
	public void setUp() {
		medranoFoursquareId = "asdf1234";
		
		if (!mongoOps.collectionExists(RegisteredPlace.class)) {
			mongoOps.createCollection(RegisteredPlace.class);
		}
		april4th = LocalDate.of(2019, Month.APRIL, 4);
		utnMedrano = new RegisteredPlace();
		utnMedrano.setName("UTN Medrano");
		utnMedrano.setAddress("Av. Medrano 951");
		utnMedrano.setFoursquareId(medranoFoursquareId);
		utnMedrano.setRegistrationDate(april4th);
		utnMedrano.setUsersWhoMarkedAsFavourite(Arrays.asList("JPerez1", "GGonzalez2"));
		utnMedrano.setCoordinates(new Coordinates(-34.598561f, -58.420024f));
		
		utnMedrano = registeredPlaceRepository.save(utnMedrano);
		
	}
	
	@After
	public void tearDown() {
		if (mongoOps.collectionExists(RegisteredPlace.class)) {
			mongoOps.dropCollection(RegisteredPlace.class);
		}
	}
	
	@Test
	public void interestedUsersInAPlaceTest() {
		List<String> usernames = registeredPlaceRepository.usernamesOfInterestedInPlaceUsers(medranoFoursquareId);
		
		Assert.assertTrue(usernames.containsAll(Arrays.asList("JPerez1", "GGonzalez2")));
	}
	
	@Test
	public void addInterestedUserTest() {
		registeredPlaceRepository.addInterestedUser(utnMedrano.getId(), "JJLopez");
		List<String> usernames = registeredPlaceRepository.usernamesOfInterestedInPlaceUsers(medranoFoursquareId);
		
		Assert.assertTrue(usernames.contains("JJLopez"));
	}
	
	@Test
	public void deleteInterestedUserTest() {
		registeredPlaceRepository.deleteInterestedUser(utnMedrano.getId(), "JPerez1");
		List<String> usernames = registeredPlaceRepository.usernamesOfInterestedInPlaceUsers(medranoFoursquareId);
		
		Assert.assertFalse(usernames.contains("JPerez1"));
	}
	
	@Test
	public void placesRegisteredOnApril4thTest() {
		List<RegisteredPlace> places = registeredPlaceRepository.placesRegisteredBetween(april4th, april4th);
		
		Assert.assertEquals("UTN Medrano", places.get(0).getName());
	}
	
	@Test
	public void thereAreNotAnyPlaceRegisteredBetweenDatesTest() {
		LocalDate february4th = LocalDate.of(2018, Month.FEBRUARY, 4);
		LocalDate march3rd = LocalDate.of(2018, Month.MARCH, 3);
		
		List<RegisteredPlace> places = registeredPlaceRepository.placesRegisteredBetween(march3rd, february4th);
		
		Assert.assertTrue(places.isEmpty());
	}

}
