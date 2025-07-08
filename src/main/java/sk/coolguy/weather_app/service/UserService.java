package sk.coolguy.weather_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import sk.coolguy.weather_app.dao.UserDAO;
import sk.coolguy.weather_app.entity.Sessions;
import sk.coolguy.weather_app.entity.User;
import sk.coolguy.weather_app.exeptions.UserAlreadyExistsException;
import sk.coolguy.weather_app.exeptions.UserWasNotFoundException;

@Component
public class UserService {

    private final UserDAO userDAO;
    //private final Sessions sessions;

    @Autowired
    public UserService (UserDAO userDAO, Sessions sessions) {
        this.userDAO = userDAO;
        //this.sessions = sessions;
    }

    public void registration(@NonNull User user) throws UserAlreadyExistsException {
        if(userDAO.findUserByUsername(user.getUsername()) == null) {
            userDAO.createUser(user);
            return;
        }
        throw new UserAlreadyExistsException("User with this nickname already exists!");
    }

    // todo: need to find a better way how to do that
    public String login(@NonNull String username, @NonNull String password) throws UserWasNotFoundException {
        User user = userDAO.findUserByUsername(username);
        if(user == null) {
            return null;
        }
        if(!user.getPassword().equals(password)) {
            throw new UserWasNotFoundException("Wrong password");
        }
        return user.getId() + "";
    }
}
