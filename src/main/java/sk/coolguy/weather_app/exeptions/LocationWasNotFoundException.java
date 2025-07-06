package sk.coolguy.weather_app.exeptions;

public class LocationWasNotFoundException extends Exception {
    public LocationWasNotFoundException(String message) {
        super(message);
    }
}
