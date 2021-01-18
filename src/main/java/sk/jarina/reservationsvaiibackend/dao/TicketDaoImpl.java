package sk.jarina.reservationsvaiibackend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import sk.jarina.reservationsvaiibackend.model.Ticket;
import sk.jarina.reservationsvaiibackend.model.TicketMapper;

import java.util.UUID;

public class TicketDaoImpl {

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private TicketMapper ticketMapper;

    public Ticket saveWithRandomUuid(Ticket ticket) {
        ticket.setId(UUID.randomUUID());
        return ticketDao.save(ticket);
    }

}
