package utn.tacs.grupo3.service;

import java.util.List;

import utn.tacs.grupo3.model.RegisteredPlace;
import utn.tacs.grupo3.retrofit.pojo.Venue;

public interface PlaceService {
	
	List<RegisteredPlace> allRegisteredPlaces();
	
	RegisteredPlace findById(String placeId);
	
	List<Venue> venuesNearByCoordinates(String coordinates);
	
	List<Venue> venuesNearByName(String name);

}
