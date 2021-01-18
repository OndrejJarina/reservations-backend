package sk.jarina.reservationsvaiibackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Ticket {

    @Id
    private UUID id;
    private UUID userId;
    private UUID screeningId;
    private int count;

    public Ticket(
            @JsonProperty("id") UUID id,
            @JsonProperty("user_id") UUID userId,
            @JsonProperty("screening_id") UUID screeningId,
            @JsonProperty("count") int count) {
        this.id = id;
        this.userId = userId;
        this.screeningId = screeningId;
        this.count = count;
    }

    public Ticket() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(UUID screeningId) {
        this.screeningId = screeningId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
