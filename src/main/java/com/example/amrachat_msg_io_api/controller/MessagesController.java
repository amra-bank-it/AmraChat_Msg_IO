package com.example.amrachat_msg_io_api.controller;

import com.example.amrachat_msg_io_api.dao.*;
import com.example.amrachat_msg_io_api.model.Message;
import com.example.amrachat_msg_io_api.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/msg")
public class MessagesController {

    private final TicketsDao ticketsDao;
    private final MessagesDao messagesDao;

    @Autowired
    public MessagesController(TicketsDao ticketsDao, MessagesDao messagesDao) {
        this.ticketsDao = ticketsDao;
        this.messagesDao = messagesDao;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendMsg(@RequestBody Message message) {
        String resMsg;
        try {
                long ticketId = message.getTicketId();


                if(!ticketsDao.existsById(ticketId) && message.getTicketId() !=0L) {
                    throw new Exception("Ticket with id: "+ ticketId + " does not exist");
                }


            if (!ticketsDao.existsById(message.getTicketId())) {
                Ticket ticket = new Ticket();
                ticket.setTicketStatusId(3);
                ticket.setUserId(1);
                ticket.setClientId(message.getClientId());
                ticket.setTheme(message.getTheme());
                ticketsDao.save(ticket);
                ticketId = ticket.getId();
                message.setTicketId(ticket.getId());
            }

                messagesDao.save(message);
                resMsg = "{ Message{id: "+message.getId()+"} "+"in Ticket{id: "+ticketId+"}"+" successfully sent }";

            return new ResponseEntity<>(
                    resMsg,
                    HttpStatus.OK);

        } catch (Exception e) {
            resMsg = "{ Send message error }" + e.getMessage();
            return new ResponseEntity<>(
                    resMsg,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping("/getAllByTicket")
    public ResponseEntity<?> sendMsg(@RequestBody Ticket ticket){
        try{
            List<Message> messages = messagesDao.findAllByTicketId(ticket.getId());
            return new ResponseEntity<>(
                    messages,
                    HttpStatus.OK);
        }catch (Exception e){
            String resMsg = "{ Get messages error }";
            return new ResponseEntity<>(
                    resMsg,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getById")
    public ResponseEntity<?> getByTicketId(@RequestBody Message message) {
        try {
            return new ResponseEntity<>(
                    messagesDao.findById(message.getId()),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/deleteById")
    public ResponseEntity<?> deleteTicket(@RequestBody Message message) {
        String resMsg;
        try {
            if(messagesDao.existsById(message.getId())) {
                messagesDao.delete(message);
                resMsg = "{Message with id: " + message.getId() + " deleted}";
            }else {
                resMsg = "{Message with id: " + message.getId() + " not exists}";
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
