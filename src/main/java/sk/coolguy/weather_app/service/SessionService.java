package sk.coolguy.weather_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.coolguy.weather_app.dao.SessionDAO;
import sk.coolguy.weather_app.entity.Sessions;
import sk.coolguy.weather_app.exeptions.SessionWasNotFoundException;

@Service
public class SessionService {

    private final SessionDAO sessionDAO;

    @Autowired
    public SessionService(SessionDAO sessionDAO) {
        this.sessionDAO = sessionDAO;
    }

    public void createSession(Sessions session) {
        this.sessionDAO.createSession(session);
    }

    public Sessions getSessionById(String sessionId) {

        Sessions session = this.sessionDAO.findSessionById(sessionId);

        if (session == null) {
            throw new SessionWasNotFoundException("Session was no found");
        }

        return session;
    }

}
