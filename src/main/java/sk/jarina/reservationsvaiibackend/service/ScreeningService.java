package sk.jarina.reservationsvaiibackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sk.jarina.reservationsvaiibackend.dao.ScreeningDao;
import sk.jarina.reservationsvaiibackend.model.Screening;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class ScreeningService {

    private final ScreeningDao screeningDao;

    @Autowired
    public ScreeningService(@Qualifier("ScreeningDao") ScreeningDao screeningDao) {
        this.screeningDao = screeningDao;
    }

    public Iterable<Screening> getAllScreenings(){
        return screeningDao.findAll();
    }

    public void insertScreening(Screening newScreening){
        this.screeningDao.saveWithRandomUuid(newScreening);
    }

    public Optional<Screening> findScreeningById(UUID id){
        return this.screeningDao.findById(id);
    }

    public void deleteScreening(UUID id){
        this.screeningDao.deleteById(id);
    }

    public void updateScreening(UUID id, Screening screening){
        this.screeningDao.save(screening);
    }

    public Iterable<Screening> getScreeningsByFilm(UUID id){
        return this.screeningDao.getAllByFilmId(id);
    }

}
