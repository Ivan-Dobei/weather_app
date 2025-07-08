package sk.coolguy.weather_app.exeptions;

public class SessionWasNotFoundException extends RuntimeException {
    public SessionWasNotFoundException(String message) {
        super(message);
    }
}
