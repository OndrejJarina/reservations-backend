package sk.jarina.reservationsvaiibackend.model;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(
                UUID.fromString(rs.getString("id")),
                rs.getString("email"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString("password"),
                rs.getDate("birthDate"),
                rs.getString("accountType")
        );
    }
}
