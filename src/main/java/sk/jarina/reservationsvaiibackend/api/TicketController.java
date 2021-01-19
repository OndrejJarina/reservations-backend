package sk.jarina.reservationsvaiibackend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.jarina.reservationsvaiibackend.model.Ticket;
import sk.jarina.reservationsvaiibackend.service.TicketService;

import java.util.UUID;

@RequestMapping("api/ticket")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET})
@RestController
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {

        this.ticketService = ticketService;
    }

    @GetMapping
    public Iterable<Ticket> getAllTickets() {
        return this.ticketService.getAllTickets();
    }

    @PostMapping
    public void addTicket(@RequestBody Ticket newTicket) {

        this.ticketService.insertTicket(newTicket);
    }

    @GetMapping(path = "{id}")
    public Ticket getTicketById(@PathVariable("id") UUID id) {
        return this.ticketService.findTicketById(id).orElse(null);
    }

    @DeleteMapping(path="{id}")
    public void deleteTicketById(@PathVariable("id") UUID id){
        this.ticketService.deleteTicket(id);
    }

    @PutMapping(path = "{id}")
    public void updateTicket(@PathVariable("id") UUID id, @RequestBody Ticket editedTicket){
        this.ticketService.updateTicket(id, editedTicket);
    }

    @GetMapping(path= "/customer/{id}")
    public Iterable<Ticket> getTicketByUserId(@PathVariable("id") UUID id){
       return this.ticketService.getAllByUserId(id);
    }
}
