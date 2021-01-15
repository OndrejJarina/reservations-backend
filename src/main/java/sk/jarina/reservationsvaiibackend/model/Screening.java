package sk.jarina.reservationsvaiibackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
public class Screening {

    @Id
    private UUID id;
    private UUID filmId;
    private Date dateTime;
    private int count;
    private double price;

    public Screening() {
    }

    public Screening(
            @JsonProperty("id") UUID id,
            @JsonProperty("film_id") UUID filmId,
            @JsonProperty("date_time") Date dateTime,
            @JsonProperty("count") int count,
            @JsonProperty("price") double price) {
        this.id = id;
        this.filmId = filmId;
        this.dateTime = dateTime;
        this.count = count;
        this.price = price;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getCount() {
        return this.count;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setFilmId(UUID filmId) {
        this.filmId = filmId;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public UUID getId() {
        return this.id;
    }

    public UUID getFilmId() {
        return this.filmId;
    }

    public Date getDateTime() {
        return this.dateTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
