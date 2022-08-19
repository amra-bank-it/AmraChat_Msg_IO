package com.example.amrachat_msg_io_api.controller;

import com.example.amrachat_msg_io_api.dao.TicketsDao;
import com.example.amrachat_msg_io_api.model.Ticket;
import com.example.amrachat_msg_io_api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketsController {

    private final TicketsDao ticketsDao;


    @Autowired
    public TicketsController(TicketsDao ticketsDao) {
        this.ticketsDao = ticketsDao;
    }


    @PostMapping("/allTickets")
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

    @PostMapping("/ById")
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

    @PostMapping("/deleteById")
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

    @PostMapping("/actualByUser")
    public ResponseEntity<?> actualByUser(@RequestBody User user) {
        try {
            List<Ticket> actual=  ticketsDao.actual(user.getId());
            return new ResponseEntity<>(
                      actual,
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateStatus")
    public ResponseEntity<?> updateStatus(@RequestBody Ticket ticket) {
        String resMsg;
        long ticketId = ticket.getId();
        try {
            if(ticketsDao.existsById(ticketId)) {
                Ticket lTicket = ticketsDao.findById(ticketId);
                lTicket.setTicketStatusId(ticket.getTicketStatusId());
                ticketsDao.save(lTicket);
                resMsg = "{Ticket with id: " + ticketId + " updated}";
            }else {
                resMsg = "{Ticket with id: " + ticketId + " not exists}";
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

    }

