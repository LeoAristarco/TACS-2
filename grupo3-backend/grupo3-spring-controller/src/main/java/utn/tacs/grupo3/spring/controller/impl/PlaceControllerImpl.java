package utn.tacs.grupo3.spring.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import utn.tacs.grupo3.service.PlaceService;
import utn.tacs.grupo3.spring.controller.PlaceController;
import utn.tacs.grupo3.spring.controller.response.Response;
import utn.tacs.grupo3.spring.controller.response.ResponseHandler;

@RestController
@RequestMapping("/places")
public class PlaceControllerImpl implements PlaceController {

	@Autowired
	private PlaceService placeService;
	@Autowired
	private ResponseHandler responseHandler;	

    @GetMapping
    public ResponseEntity<Response> places() {
        return responseHandler.handle(() -> new Response(HttpStatus.OK, "All registered places", placeService.allRegisteredPlaces()));
    }

    @Override
    @GetMapping("/{place-id}")
    public ResponseEntity<Response> placeById(@PathVariable("place-id") String placeId) {
        return responseHandler.handle(() -> new Response(HttpStatus.OK, "Place", placeService.findById(placeId)));
    }

    @Override
    @GetMapping("/near")
    public ResponseEntity<Response> near(@RequestParam("coordinates") String coordinates) {
        return responseHandler.handle(() -> new Response(HttpStatus.OK, "Places near coordinates", placeService.venuesNearByCoordinates(coordinates)));
    }

    @Override
    @GetMapping("/near-by-name")
    public ResponseEntity<Response> nearByName(@RequestParam("name") String name) {
        return responseHandler.handle(() -> new Response(HttpStatus.OK, "Places by name", placeService.venuesNearByName(name)));
    }

}
