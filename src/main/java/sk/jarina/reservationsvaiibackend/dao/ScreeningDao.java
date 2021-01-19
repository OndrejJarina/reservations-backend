package sk.jarina.reservationsvaiibackend.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.jarina.reservationsvaiibackend.model.Screening;

import java.util.ArrayList;
import java.util.UUID;

@Repository("ScreeningDao")
public interface ScreeningDao extends CrudRepository<Screening, UUID> {
    Screening saveWithRandomUuid(Screening screening);

    Iterable<Screening> getAllByFilmId(UUID filmId);
}
