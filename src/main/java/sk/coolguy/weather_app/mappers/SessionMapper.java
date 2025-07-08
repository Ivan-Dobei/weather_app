package sk.coolguy.weather_app.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import sk.coolguy.weather_app.entity.Sessions;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SessionMapper implements RowMapper<Sessions> {
    @Override
    public Sessions mapRow(ResultSet rs, int rowNum) throws SQLException {

        Sessions session = new Sessions();

        session.setUserId(rs.getInt("user_id"));
        session.setExpireAt(rs.getInt("expire_at"));

        return session;
    }
}
