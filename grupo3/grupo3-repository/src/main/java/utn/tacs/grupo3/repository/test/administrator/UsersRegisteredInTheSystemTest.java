package utn.tacs.grupo3.repository.test.administrator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utn.tacs.grupo3.model.ListOfPlaces;
import utn.tacs.grupo3.model.Place;
import utn.tacs.grupo3.model.User;
import utn.tacs.grupo3.repository.UserRepository;


public class UsersRegisteredInTheSystemTest {

    private User user1;
    private ListOfPlaces listOfPlaces1;
    private Place aPlace1;

    private User user2;
    private ListOfPlaces listOfPlaces2;
    private UserRepository userRepository;

    @Before
    public void initialize() {
        user1 = new User("Elver", "Galarga", null, "s");
        listOfPlaces1 = new ListOfPlaces("Lugares Favoritos");
        user1.getListOfPlaces().add(listOfPlaces1);

        aPlace1 = new Place("el MC", "en algun lado");

        user2 = new User("Jorge", "Vergara", null, "s");
        listOfPlaces2 = new ListOfPlaces("Mi lista");
        user2.getListOfPlaces().add(listOfPlaces1);

        listOfPlaces1.addPlace(aPlace1);
        listOfPlaces2.addPlace(aPlace1);

        userRepository = new UserRepository();
        userRepository.createUser(user1);
        userRepository.createUser(user2);
    }

    // 12-Como administrador quiero poder ver los siguientes datos de un usuario:
//    -Usuario
//    -Cantidad de listas
//    -Cantidad de lugares visitados en sus listas.
//    -Último acceso
    @Test
    public void seeDataOfAuser() {
        Assert.assertEquals("el MC", aPlace1.getName());
    }

    // 13-Como administrador quiero seleccionar 2 listas de usuarios diferentes y
//    ver si tienen algún lugar en común.
    @Test
    public void seeIfThereArePlacesInCommonInTwoListsOfPlaces() {
        Assert.assertEquals(true, listOfPlaces1.areTherePlacesInCommonWith(listOfPlaces2));
    }

    //14-Como administrador quiero seleccionar un lugar y ver la cantidad de
//    usuarios que se interesaron en el mismo (lo agregaron a una lista).
    @Test
    public void seeTheAmountOfUsersInterestedInAPlace() {
        Assert.assertEquals(2, userRepository.amountOfUsersInterestedIn(aPlace1));
    }

}
