package sk.jarina.reservationsvaiibackend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import sk.jarina.reservationsvaiibackend.model.Film;

import java.util.UUID;

public class FilmDaoImpl {

    @Autowired
    private FilmDao filmDao;

    public FilmDaoImpl() {
    }

    public Film saveWithRandomUuid(Film film){
        film.setId(UUID.randomUUID());
        return filmDao.save(film);
    }
}
