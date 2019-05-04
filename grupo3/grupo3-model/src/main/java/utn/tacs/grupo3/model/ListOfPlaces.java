package utn.tacs.grupo3.model;

import java.util.ArrayList;
import java.util.List;

public class ListOfPlaces {

    private static int autoIncrement = 0;
    private int id = 0;
    private String listName;
    private List<Place> places;

    public ListOfPlaces() {
    }

    public ListOfPlaces(String listName) {
        autoIncrement = autoIncrement + 1;
        id = autoIncrement;
        places = new ArrayList<>();
        this.listName = listName;
    }

    public void addPlace(Place place) {
        places.add(place);
    }

    public boolean areTherePlacesInCommonWith(ListOfPlaces aListOfPlaces) {
        return places
                .stream()
                .anyMatch(place -> aListOfPlaces.getPlaces().contains(place));
    }

    public String getListName() {
        return listName;
    }

    public int getId() {
        return id;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public List<Place> getPlaces() {
        return places;
    }
}
