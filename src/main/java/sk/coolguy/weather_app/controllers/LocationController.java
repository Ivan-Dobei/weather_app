package sk.coolguy.weather_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.coolguy.weather_app.dao.LocationDAO;
import sk.coolguy.weather_app.entity.Locations;
import sk.coolguy.weather_app.exeptions.ErrorResponse;
import sk.coolguy.weather_app.exeptions.LocationAlreadyExistsException;
import sk.coolguy.weather_app.exeptions.LocationWasNotFoundException;
import sk.coolguy.weather_app.exeptions.UserWasNotFoundException;
import sk.coolguy.weather_app.service.LocationService;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;
    private final LocationDAO locationDAO;

    @Autowired
    public LocationController(LocationService locationService, LocationDAO locationDAO) {
        this.locationService = locationService;
        this.locationDAO = locationDAO;
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getLocationByName(@PathVariable("name") String name) {
        try {
            Locations location = this.locationService.getLocationByName(name);
            return new ResponseEntity<>(location, HttpStatus.OK);

        } catch (LocationWasNotFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(e.getMessage()));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getLocationByUserId(@PathVariable("id") int userId){
        try {
            List<Locations> locations = this.locationService.getLocationsByUserId(userId);
            return new ResponseEntity<>(locations, HttpStatus.OK);
        } catch (UserWasNotFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(e.getMessage()));
        } catch (Exception e) {
           throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<?> addLocation(@RequestBody Locations location) {
        try {
            this.locationService.createLocation(location);
            return new ResponseEntity<>(location, HttpStatus.CREATED);
        } catch (LocationAlreadyExistsException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.CONFLICT);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLocation(@PathVariable("id") int id) {
        try {
            this.locationDAO.deleteLocationById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
