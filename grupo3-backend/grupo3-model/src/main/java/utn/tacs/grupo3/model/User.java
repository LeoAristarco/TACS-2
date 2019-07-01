package utn.tacs.grupo3.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {

	private String id;

	private String username;
	private String password;
	private Role role;
	private String firstName;
    private String lastName;
    private List<ListOfPlaces> listsOfPlaces;
    private LocalDateTime lastAccess;

    public User() {
    	
    }

    public User(String firstName, String lastName, String username, String password, Role role) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.listsOfPlaces = new ArrayList<>();
        this.role = role;
        this.password = password;
    }

    public void initialize(String encryptedPassword) {
        this.listsOfPlaces = new ArrayList<>();
        this.role = Role.USER;
        setLastAccess(null);
        setPassword(encryptedPassword);
    }
    
    public boolean areThereEmptyFieldsToSignUp() {
        return username == null || password == null || firstName == null || lastName == null;
    }

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

    public String getFirstName() {
        return firstName;
    }

    public LocalDateTime getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(LocalDateTime lastAccess) {
        this.lastAccess = lastAccess;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ListOfPlaces> getListsOfPlaces() {
        return listsOfPlaces;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setListsOfPlaces(List<ListOfPlaces> listsOfPlaces) {
        this.listsOfPlaces = listsOfPlaces;
    }
    public Role getRole() {
    	return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
