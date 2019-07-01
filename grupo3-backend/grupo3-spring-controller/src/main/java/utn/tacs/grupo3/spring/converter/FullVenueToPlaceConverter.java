package utn.tacs.grupo3.spring.converter;

import org.springframework.stereotype.Component;

import utn.tacs.grupo3.model.Coordinates;
import utn.tacs.grupo3.model.Place;
import utn.tacs.grupo3.retrofit.pojo.venue.FullVenue;

@Component
public class FullVenueToPlaceConverter implements Converter<FullVenue, Place>{

	@Override
	public Place convert(FullVenue venue) {
		Place place = new Place();
		
    	place.setName(venue.getName());
    	place.setAddress(venue.getLocation().getAddress());
    	place.setFoursquareId(venue.getId());
    	place.setVisited(Boolean.FALSE);
    	place.setCoordinates(
    			new Coordinates(
    					venue.getLocation().getLat().floatValue(), 
    					venue.getLocation().getLng().floatValue())
    			);
		
    	return place;
	}
	
	

}
