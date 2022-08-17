package com.example.amrachat_msg_io_api.controller;

import com.example.amrachat_msg_io_api.dao.ClientsDao;
import com.example.amrachat_msg_io_api.dao.TicketStatusDao;
import com.example.amrachat_msg_io_api.dao.TicketsDao;
import com.example.amrachat_msg_io_api.dao.UsersDao;
import com.example.amrachat_msg_io_api.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketsController {

    private final TicketsDao ticketsDao;
    private final ClientsDao clientsDao;
    private final UsersDao usersDao;
    private final TicketStatusDao ticketStatusDao;

    @Autowired
    public TicketsController(TicketsDao ticketsDao, ClientsDao clientsDao, UsersDao usersDao, TicketStatusDao ticketStatusDao) {
        this.ticketsDao = ticketsDao;
        this.clientsDao = clientsDao;
        this.usersDao = usersDao;
        this.ticketStatusDao = ticketStatusDao;
    }


    @GetMapping("/getAll")
    public ResponseEntity<?> getTickets() {
        try {
            List<Ticket> tickets = ticketsDao.findAll();
            return new ResponseEntity<>(
                    tickets,
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getById")
    public ResponseEntity<?> getByTicketId(@RequestBody Ticket ticket) {
        try {
            return new ResponseEntity<>(
                    ticketsDao.findById(ticket.getId()),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<?> deleteTicket(@RequestBody Ticket ticket) {
        String resMsg;
        try {
            if(ticketsDao.existsById(ticket.getId())) {
                ticketsDao.delete(ticket);
                resMsg = "{Ticket with id: " + ticket.getId() + " deleted}";
            }else {
                resMsg = "{Ticket with id: " + ticket.getId() + " not exists}";
            }
                return new ResponseEntity<>(
                        resMsg,
                             HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveTicket(@RequestBody Ticket ticket){
        String exsMsg;
        try {
            Ticket lTicket = ticketsDao.existsById(ticket.getId())? ticket : new Ticket();

                lTicket.setUser(
                        usersDao.findById(ticket.getUser().getId())
                );
                lTicket.setClient(
                        clientsDao.findById(ticket.getClient().getId())
                );
                lTicket.setTicketStatus(
                        ticketStatusDao.findById(ticket.getTicketStatus().getId())
                );
                lTicket.setTheme(ticket.getTheme());

                ticketsDao.save(lTicket);

            return new ResponseEntity<>(
                    lTicket,
                    HttpStatus.OK);
        } catch (Exception e) {
            exsMsg = "{ Ticket save exception " + e.getMessage() + " }";
            return new ResponseEntity<>(
                    exsMsg,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
