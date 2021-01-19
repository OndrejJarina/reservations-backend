package sk.jarina.reservationsvaiibackend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.jarina.reservationsvaiibackend.model.Ticket;
import sk.jarina.reservationsvaiibackend.model.TicketMapper;

import java.util.UUID;

public class TicketDaoImpl {

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TicketMapper ticketMapper;

    public Ticket saveWithRandomUuid(Ticket ticket) {
        ticket.setId(UUID.randomUUID());
        return ticketDao.save(ticket);
    }

    public Iterable<Ticket> getAllByUserId(UUID id){
        System.out.println("testujem!");
        Iterable<Ticket> result =jdbcTemplate.query("select * from ticket where user_id = ?", ticketMapper, id);
        for(Ticket tiket: result){
            System.out.println(tiket.getCount());
        }
        return jdbcTemplate.query("select * from ticket where user_id = ?", ticketMapper, id);
    }
}
