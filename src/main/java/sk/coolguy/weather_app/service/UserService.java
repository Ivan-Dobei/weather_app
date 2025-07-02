package sk.coolguy.weather_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import sk.coolguy.weather_app.dao.UserDAO;
import sk.coolguy.weather_app.entity.User;
import sk.coolguy.weather_app.exeptions.UserAlreadyExistsException;

@Component
public class UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserService (UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void registration(@NonNull User user) {
        if(userDAO.findUserByUsername(user.getUsername()) == null) {
            userDAO.createUser(user);
        }
        throw new UserAlreadyExistsException("User with this nickname already exists!");
    }
}
