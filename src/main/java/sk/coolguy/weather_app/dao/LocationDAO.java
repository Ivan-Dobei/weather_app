package sk.coolguy.weather_app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import sk.coolguy.weather_app.entity.Locations;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LocationDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LocationDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Locations findLocationByName(String name) {
        String sql = "select * from locations where name = ?";
        return jdbcTemplate.query(sql, new Object[]{name}, new LocationMapper()).stream().findFirst().orElse(null);
    }

    public List<Locations> findLocationsByUserId(int userId) {
        String sql = "select * from locations where userid = ?";

        return jdbcTemplate.query(sql, new Object[]{userId}, new LocationMapper())
                .stream().collect(Collectors.toList());
    }

    public void createLocation(@NonNull Locations location) {
        String sql = "insert into locations (id, name, latitude, longitude, userId) values (?, ?, ?, ?, ?)";

        Object[] values = new Object[] {
                location.getId(),
                location.getName(),
                location.getLatitude(),
                location.getLongitude(),
                location.getUserId()
        };

        jdbcTemplate.update(sql, values);
    }

    public void deleteLocationById(int id) {
        String sql = "delete from locations where id = ?";

        jdbcTemplate.update(sql, id);
    }

}
