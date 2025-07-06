package sk.coolguy.weather_app.exeptions;

public class LocationAlreadyExistsException extends Exception {
    public LocationAlreadyExistsException(String message) {
        super(message);
    }
}
