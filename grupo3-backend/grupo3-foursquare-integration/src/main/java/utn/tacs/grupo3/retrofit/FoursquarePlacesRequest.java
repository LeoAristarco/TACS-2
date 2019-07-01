package utn.tacs.grupo3.retrofit;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import retrofit2.Call;
import retrofit2.Response;
import utn.tacs.grupo3.retrofit.pojo.Pojo;
import utn.tacs.grupo3.retrofit.pojo.Venue;
import utn.tacs.grupo3.retrofit.pojo.venue.FullVenue;
import utn.tacs.grupo3.retrofit.pojo.venue.VenuePojo;

@Component
public class FoursquarePlacesRequest {

    private final String CLIENT_ID = "VWYG3QXBIYTFHSY4ENVFIJAB2IO5TTGYCM4PQRUNCEQKXTG2";
    private final String CLIENT_SECRET = "IDYZ3GPBB1WBP5N3LLNTY1YDVDSN42XNKW1X4PO142JNORJB";
    private final String DATE = "20180703";
    private final String NEAR = "Buenos Aires";

    public List<Venue> getAllPlacesByCoordinates(String latitudeAndLongitude) {
        FoursquareService service = RetrofitClientInstance.getRetrofitInstance().create(FoursquareService.class);

        Call<Pojo> call = service.getAllPlacesByCoordinates(CLIENT_ID, CLIENT_SECRET, DATE, latitudeAndLongitude, 5);
        return executeCall(call);
    }

    public List<Venue> getAllPlacesByName(String name) {
        FoursquareService service = RetrofitClientInstance.getRetrofitInstance().create(FoursquareService.class);

        Call<Pojo> call = service.getAllPlacesByName(CLIENT_ID, CLIENT_SECRET, DATE, 5, name,NEAR);
        return executeCall(call);
    }
    
    public FullVenue getVenueById(String venueId) {
        FoursquareService service = RetrofitClientInstance.getRetrofitInstance().create(FoursquareService.class);

        Call<VenuePojo> call = service.getVenue(venueId, CLIENT_ID, CLIENT_SECRET, DATE);
        
        try {
			return call.execute().body().getResponse().getVenue();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
    }


    private List<Venue> executeCall(Call<Pojo> call) {
        try {
            Response<Pojo> response = call.execute();
            return response.body().response.venues;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
