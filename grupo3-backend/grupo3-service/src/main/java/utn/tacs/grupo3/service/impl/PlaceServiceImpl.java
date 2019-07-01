package utn.tacs.grupo3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utn.tacs.grupo3.model.RegisteredPlace;
import utn.tacs.grupo3.repository.mongo.RegisteredPlaceRepository;
import utn.tacs.grupo3.retrofit.FoursquarePlacesRequest;
import utn.tacs.grupo3.retrofit.pojo.Venue;
import utn.tacs.grupo3.service.PlaceService;

@Service
public class PlaceServiceImpl implements PlaceService{
	
    @Autowired
    private FoursquarePlacesRequest foursquarePlacesRequest;
    @Autowired
    private RegisteredPlaceRepository registeredPlaceRepository;
    
	@Override
	public List<RegisteredPlace> allRegisteredPlaces() {
		return registeredPlaceRepository.findAll();
	}
	@Override
	public RegisteredPlace findById(String placeId) {
		return registeredPlaceRepository.findById(placeId);
	}
	@Override
	public List<Venue> venuesNearByCoordinates(String coordinates) {
		return foursquarePlacesRequest.getAllPlacesByCoordinates(coordinates);
	}
	@Override
	public List<Venue> venuesNearByName(String name) {
		return foursquarePlacesRequest.getAllPlacesByName(name);
	}
}
