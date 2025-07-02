package sk.coolguy.weather_app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Sessions {

    @Id
    private UUID sessionId;

    private int userId;
    private int expireAt;

    public Sessions() {}

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
