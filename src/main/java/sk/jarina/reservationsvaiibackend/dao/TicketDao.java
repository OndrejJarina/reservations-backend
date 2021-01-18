package sk.jarina.reservationsvaiibackend.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.jarina.reservationsvaiibackend.model.Ticket;

import java.util.UUID;

@Repository("TicketDao")
public interface TicketDao extends CrudRepository<Ticket, UUID> {

    Ticket saveWithRandomUuid(Ticket ticket);
}
