package sk.coolguy.weather_app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import sk.coolguy.weather_app.entity.Sessions;
import sk.coolguy.weather_app.mappers.SessionMapper;

@Component
public class SessionDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SessionDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createSession(Sessions session) {
        String sql = "insert into sessions values(?,?,?)";

        Object[] params = new Object[] {
                session.getSessionId(),
                session.getUserId(),
                session.getExpireAt()
        };

        this.jdbcTemplate.query(sql, params, new SessionMapper());
    }

    public Sessions findSessionById(String sessionId) {
        String sql = "select * from sessions where sessionId = ?";

        return jdbcTemplate.query(sql, new Object[]{sessionId}, new SessionMapper()).stream().findFirst().orElse(null);
    }

}
