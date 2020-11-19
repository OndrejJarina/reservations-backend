package sk.jarina.reservationsvaiibackend.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.jarina.reservationsvaiibackend.model.Film;

import java.util.UUID;

@Repository("FilmDao")
public interface FilmDao extends CrudRepository<Film, UUID> {

    Film saveWithRandomUuid(Film film);

}
