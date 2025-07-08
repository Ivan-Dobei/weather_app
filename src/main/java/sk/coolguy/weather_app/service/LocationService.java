package sk.coolguy.weather_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.coolguy.weather_app.dao.LocationDAO;
import sk.coolguy.weather_app.entity.Location;
import sk.coolguy.weather_app.exeptions.LocationAlreadyExistsException;
import sk.coolguy.weather_app.exeptions.LocationWasNotFoundException;
import sk.coolguy.weather_app.exeptions.UserWasNotFoundException;

import java.util.List;

@Service
public class LocationService {

    private final LocationDAO locationDAO;

    @Autowired
    public LocationService(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }

    public Location getLocationByName(String name) throws LocationWasNotFoundException {
        Location location = this.locationDAO.findLocationByName(name.toLowerCase());

        if (location == null) {
            throw new LocationWasNotFoundException("Location " + name + " was not found");
        }

        return location;
    }

    public List<Location> getLocationsByUserId(int userId) throws UserWasNotFoundException {
        List<Location> locations = this.locationDAO.findLocationsByUserId(userId);
        if (locations == null) {
            throw new UserWasNotFoundException("User " + userId + " was not found");
        }

        return locations;
    }

    public void createLocation(Location location) throws LocationAlreadyExistsException {

        location.setName(location.getName().toLowerCase());


        if (this.locationDAO.findLocationByName(location.getName()) == null) {
            this.locationDAO.createLocation(location);
        } else {
            throw new LocationAlreadyExistsException("Location " + location.getName() + " already exists");
        }
    }

}
