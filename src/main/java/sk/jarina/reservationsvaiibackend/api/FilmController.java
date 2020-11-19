package sk.jarina.reservationsvaiibackend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.jarina.reservationsvaiibackend.model.Film;
import sk.jarina.reservationsvaiibackend.service.FilmService;

import java.util.UUID;

@RequestMapping("api/film")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET})
@RestController
public class FilmController {
    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public Iterable<Film> getAllFilms() {
        return this.filmService.getAllFilms();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public void addFilm(@RequestBody Film newFilm) {
        this.filmService.insertFilm(newFilm);
    }

    @GetMapping(path = "{id}")
    public Film getFilmById(@PathVariable("id") UUID id) {
        return this.filmService.findFilmById(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteFilmById(@PathVariable("id") UUID id) {
        this.filmService.deleteFilm(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping()
    public void updateFilm(@RequestBody Film editedFilm) {
        this.filmService.updateFilm(editedFilm);
    }
}
