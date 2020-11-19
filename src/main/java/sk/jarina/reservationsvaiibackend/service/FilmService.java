package sk.jarina.reservationsvaiibackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sk.jarina.reservationsvaiibackend.dao.FilmDao;
import sk.jarina.reservationsvaiibackend.model.Film;

import java.util.Optional;
import java.util.UUID;

@Service
public class FilmService {

    private final FilmDao filmDao;

    @Autowired
    public FilmService(@Qualifier("FilmDao") FilmDao filmDao) {
        this.filmDao = filmDao;
    }

    public Iterable<Film> getAllFilms() {
        return this.filmDao.findAll();
    }

    public Optional<Film> findFilmById(UUID id) {
        return this.filmDao.findById(id);
    }

    public void insertFilm(Film newFilm) {
        this.filmDao.saveWithRandomUuid(newFilm);
    }

    public void updateFilm(Film film) {
        this.filmDao.save(film);
    }

    public void deleteFilm(UUID id) {
        this.filmDao.deleteById(id);
    }
}
