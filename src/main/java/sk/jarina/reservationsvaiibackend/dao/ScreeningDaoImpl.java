package sk.jarina.reservationsvaiibackend.dao;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import sk.jarina.reservationsvaiibackend.model.Screening;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.jarina.reservationsvaiibackend.model.ScreeningMapper;


import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;

public class ScreeningDaoImpl {

    @Autowired
    private ScreeningDao screeningDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ScreeningMapper screeningMapper;

    public ScreeningDaoImpl() {
    }

    public Screening saveWithRandomUuid(Screening screening){
        screening.setId(UUID.randomUUID());
        return screeningDao.save(screening);
    }

    Iterable<Screening> getAllByFilmId(UUID filmId){
        return jdbcTemplate.query("select * from screening where film_id = ?", screeningMapper, filmId);
    }

}
