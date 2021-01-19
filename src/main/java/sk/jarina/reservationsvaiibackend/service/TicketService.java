package sk.jarina.reservationsvaiibackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sk.jarina.reservationsvaiibackend.dao.TicketDao;
import sk.jarina.reservationsvaiibackend.model.Ticket;

import java.util.Optional;
import java.util.UUID;

@Service
public class TicketService {

    private final TicketDao ticketDao;
    
    @Autowired
    public TicketService(@Qualifier("TicketDao") TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public Iterable<Ticket> getAllTickets(){
        return ticketDao.findAll();
    }

    public void insertTicket(Ticket newTicket) {
        this.ticketDao.saveWithRandomUuid(newTicket);
    }

    public Optional<Ticket> findTicketById(UUID id){
        return this.ticketDao.findById(id);
    }

    public void deleteTicket(UUID id){
        this.ticketDao.deleteById(id);
    }

    public void updateTicket(UUID id, Ticket ticket){
        this.ticketDao.save(ticket);
    }

    public Iterable<Ticket> getAllByUserId(UUID id){
        return this.ticketDao.getAllByUserId(id);
    }
}
