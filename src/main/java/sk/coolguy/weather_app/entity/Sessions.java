package sk.coolguy.weather_app.entity;

import jakarta.persistence.Id;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.UUID;

@SessionScope
@Component
public class Sessions {

    @Id
    String sessionId = UUID.randomUUID().toString();

    private int userId;
    private int expireAt = 3;

    public Sessions() {}

    public String getSessionId() {
        return sessionId;
    }

    //public void setSessionId(UUID sessionId) {
    //  this.sessionId = sessionId;
    //}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(int expireAt) {
        this.expireAt = expireAt;
    }
}
