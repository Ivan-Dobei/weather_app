package sk.coolguy.weather_app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import sk.coolguy.weather_app.entity.User;
import sk.coolguy.weather_app.mappers.UserMapper;

@Component
public class UserDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findUserById(int id) {
        String sql = "select * from Users where id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, new UserMapper())
                .stream().findAny().orElse(null);
    }

    public User findUserByUsername(String username) {
        String sql = "select * from Users where username = ?";

        return jdbcTemplate.query(sql, new Object[]{username}, new UserMapper())
                .stream().findAny().orElse(null);
    }

    public void createUser(@NonNull User user) {
        String sql = "insert into users (id, username, email, password) values (?, ?, ?, ?)";

        User newUser = new User();

        Object[] params = new Object[]{
                newUser.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword()
        };

        jdbcTemplate.update(sql, params);
    }

}
