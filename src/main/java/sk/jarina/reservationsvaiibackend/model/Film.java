package sk.jarina.reservationsvaiibackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Film {

    @Id
    private UUID id;
    private String name;
    private int runtime;
    private String description;

    public Film(
            @JsonProperty("id") UUID id,
            @JsonProperty("name") String name,
            @JsonProperty("runtime") int runtime,
            @JsonProperty("description") String description
    ) {
        this.id = id;
        this.name = name;
        this.runtime = runtime;
        this.description = description;
    }

    public Film() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
