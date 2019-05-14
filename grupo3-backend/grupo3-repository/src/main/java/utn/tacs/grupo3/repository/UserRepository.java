package utn.tacs.grupo3.repository;

import org.springframework.stereotype.Repository;
import utn.tacs.grupo3.model.exception.ExceptionbyListOfPlaceNotFound;
import utn.tacs.grupo3.model.exception.ExceptionbyResourceNotFound;
import utn.tacs.grupo3.model.ListOfPlaces;
import utn.tacs.grupo3.model.Place;
import utn.tacs.grupo3.model.User;
import utn.tacs.grupo3.model.exception.ExceptionbyUserNotFound;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class UserRepository {

    private List<User> users;

    public UserRepository() {
        users = new ArrayList<User>();
        User user1 = new User("jperez","MOCK","Juan", "Perez");
        ListOfPlaces listOfPlaces1 = new ListOfPlaces("LugaresFavoritos");
        Place casa = new Place("Casa", "Calle falsa 123");
        casa.setLatitude(-34.659581f);
        casa.setLongitude(-58.468068f);

        listOfPlaces1.addPlace(casa);
        user1.getListsOfPlaces().add(listOfPlaces1);
        users.add(user1);
    }

    public List<User> allUsers() {
        return users;
    }

    public List<User> usersByFirstName(String nombre) {
        return users.stream().
                filter(user -> user.getFirstName().equalsIgnoreCase(nombre)).
                collect(Collectors.toList());
    }

    public User userByFirstName(String name) throws ExceptionbyResourceNotFound {
        return usersByFirstName(name)
                .stream().findFirst()
                .orElseThrow(() -> new ExceptionbyUserNotFound(name));
    }

    public User userByUsername(String username) throws ExceptionbyResourceNotFound {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new ExceptionbyResourceNotFound("no se encontró el usuario de nombre: " + username));
    }

    public boolean usernameExists(String username) {
        return users.stream()
                .anyMatch(user -> user.getUsername().equals(username));
    }

    public User createUser(User newUser) {
        users.add(newUser);

        return newUser;
    }

    public long amountOfUsersInterestedIn(Place aPlace) {
        return users.stream().filter(u -> u.havePlacesInCommonWith(aPlace)).count();
    }

    public Stream<List<ListOfPlaces>> listsOfPlacesById(int id) {
        return users.stream()
                .map(user -> user.getListsOfPlaces())
                .filter(listsOfPlaces ->
                        listsOfPlaces.stream().anyMatch(lp -> lp.getId() == id));
    }

    private List<ListOfPlaces> searchForListsOfPlacesBy(int id) throws ExceptionbyResourceNotFound {
        return listsOfPlacesById(id).findFirst().
                orElseThrow(() -> new ExceptionbyListOfPlaceNotFound(""+id));
    }

    public ListOfPlaces listOfPlacesById(int id) throws ExceptionbyResourceNotFound {
        return searchForListsOfPlacesBy(id).get(0);

    }
}
