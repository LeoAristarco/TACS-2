package utn.tacs.grupo3.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListOfPlaces {

    private String listName;
    private List<Place> places;

    public ListOfPlaces() { }

    public ListOfPlaces(String listName) {
        this.places = new ArrayList<>();
        this.listName = listName;
    }

    public void addPlace(Place place) {
        places.add(place);
    }
    
    @Deprecated
    public boolean areTherePlacesInCommonWith(ListOfPlaces aListOfPlaces) {
        return places
                .stream()
                .anyMatch(place -> aListOfPlaces.getPlaces().contains(place));
    }
    
    public List<Place> placesInCommonWith(ListOfPlaces anotherList){
    	return places
    			.stream()
    			.filter(place -> anotherList.getPlaces()
    					.stream()
    					.anyMatch(placeFromAnotherList -> placeFromAnotherList
    							.getFoursquareId().equals(place.getFoursquareId()))
    					)
    			.collect(Collectors.toList());    					
    }
    
    public long amountOfVisitedPlaces() {
    	return places
    			.stream()
    			.filter(place -> place.getVisited())
    			.collect(Collectors.toList()).size();
    }

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public List<Place> getPlaces() {
		return places;
	}

	public void setPlaces(List<Place> places) {
		this.places = places;
	}
}
