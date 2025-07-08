package sk.coolguy.weather_app.mappers;

import org.springframework.jdbc.core.RowMapper;
import sk.coolguy.weather_app.entity.Location;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationMapper implements RowMapper<Location> {


    @Override
    public Location mapRow(ResultSet rs, int rowNum) throws SQLException {

        Location location = new Location();

        location.setId(rs.getInt("id"));
        location.setName(rs.getString("name"));
        location.setLatitude(rs.getDouble("latitude"));
        location.setLongitude(rs.getDouble("longitude"));
        location.setUserId(rs.getInt("userId"));


        return location;
    }
}
