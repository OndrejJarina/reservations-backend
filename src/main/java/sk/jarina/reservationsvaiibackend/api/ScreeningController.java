package sk.jarina.reservationsvaiibackend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.jarina.reservationsvaiibackend.model.Screening;
import sk.jarina.reservationsvaiibackend.service.ScreeningService;

import java.util.UUID;

@RequestMapping("api/screening")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET})
@RestController
public class ScreeningController {

    private final ScreeningService screeningService;

    @Autowired
    public ScreeningController(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    @GetMapping
    public Iterable<Screening> getAllScreenings() {
        return this.screeningService.getAllScreenings();
    }

    @GetMapping(path = "show/{id}")
    public Iterable<Screening> getScreeningsByFilm(@PathVariable UUID id) {
        return this.screeningService.getScreeningsByFilm(id);
    }

    @PostMapping
    public void addScreening(@RequestBody Screening newScreening) {
        this.screeningService.insertScreening(newScreening);
    }

    @GetMapping(path = "{id}")
    public Screening getScreeningById(@PathVariable("id") UUID id) {
        return this.screeningService.findScreeningById(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteScreeningById(@PathVariable("id") UUID id) {
        this.screeningService.deleteScreening(id);
    }

    @PutMapping(path = "{id}")
    public void updateScreening(@PathVariable("id") UUID id, @RequestBody Screening editedScreening) {
        this.screeningService.updateScreening(id, editedScreening);
    }
}
