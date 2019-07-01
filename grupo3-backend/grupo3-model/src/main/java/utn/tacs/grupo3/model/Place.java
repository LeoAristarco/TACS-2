package utn.tacs.grupo3.model;

public class Place {

    private String name;
    private String address;
    private Coordinates coordinates;
	private String foursquareId;
	private Boolean visited;


	public Place() {}

    public Place(String name, String address) {
        this.name = name;
        this.address = address;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getFoursquareId() {
		return foursquareId;
	}
	
	public void setFoursquareId(String foursquareId) {
		this.foursquareId = foursquareId;
	}

	public Boolean getVisited() {
		return visited;
	}

	public void setVisited(Boolean visited) {
		this.visited = visited;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
}

