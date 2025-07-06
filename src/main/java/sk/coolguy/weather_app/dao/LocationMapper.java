package sk.coolguy.weather_app.dao;

import org.springframework.jdbc.core.RowMapper;
import sk.coolguy.weather_app.entity.Locations;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationMapper implements RowMapper<Locations> {


    @Override
    public Locations mapRow(ResultSet rs, int rowNum) throws SQLException {

        Locations location = new Locations();

        location.setId(rs.getInt("id"));
        location.setName(rs.getString("name"));
        location.setLatitude(rs.getDouble("latitude"));
        location.setLongitude(rs.getDouble("longitude"));
        location.setUserId(rs.getInt("userId"));


        return location;
    }
}
