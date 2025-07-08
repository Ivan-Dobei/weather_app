package sk.coolguy.weather_app.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import sk.coolguy.weather_app.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        User user = new User();
        user.setUsername(resultSet.getString("username"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));

        return user;
    }
}
