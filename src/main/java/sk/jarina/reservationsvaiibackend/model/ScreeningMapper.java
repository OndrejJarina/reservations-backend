package sk.jarina.reservationsvaiibackend.model;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class ScreeningMapper implements RowMapper<Screening> {
    @Override
    public Screening mapRow(ResultSet rs, int rowNum) throws SQLException {
        Screening screening = new Screening (
                UUID.fromString(rs.getString("id")),
                UUID.fromString(rs.getString("film_id")),
                rs.getTimestamp("date_time"),
                rs.getInt("count"),
                rs.getDouble("price")
        );
        return screening;
    }
}
