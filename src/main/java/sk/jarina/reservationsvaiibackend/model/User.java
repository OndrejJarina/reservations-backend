package sk.jarina.reservationsvaiibackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="users")
public class User {

    @Id
    private UUID id;
    private String name;
    private String surnmame;
    private String email;
    private String password;
    private Date birthDate;
    private String accountType;

    public User(
            @JsonProperty("id") UUID id,
            @JsonProperty("email") String email,
            @JsonProperty("name") String name,
            @JsonProperty("surname") String surnmame,
            @JsonProperty("password") String password,
            @JsonProperty("birthDate") Date birthDate,
            @JsonProperty("accountType") String accountType) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.accountType = accountType;
        this.name = name;
        this.surnmame = surnmame;
    }

    public User() {
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getSurnmame() {
        return surnmame;
    }

    public void setSurnmame(String surnmame) {
        this.surnmame = surnmame;
    }
}
