package com.example.amrachat_msg_io_api.controller;

import com.example.amrachat_msg_io_api.dao.MessagesDao;
import com.example.amrachat_msg_io_api.dao.TicketsDao;
import com.example.amrachat_msg_io_api.dao.UsersDao;
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
    @Autowired
    private TicketsDao ticketDao;
    @Autowired
    private MessagesDao messageDao;
    @Autowired
    private UsersDao usersDao;

    @PostMapping("/send")
    public ResponseEntity<?> sendMsg(@RequestBody Message message) {
        String resMsg;
        try {
            Ticket ticket = new Ticket();

            if (message.getTicketId() == 0) {
                ticket.setStatus(3);
                ticket.setClientID(message.getClientId());
                ticket.setUserId(
                        usersDao.findByFirstname("support").getId()
                );
                ticketDao.save(ticket);
                message.setTicketId(ticket.getId());
            }
            messageDao.save(message);
            resMsg = "{ " + message + " successfully sent}";

            return new ResponseEntity<>(
                    resMsg,
                    HttpStatus.OK);

        } catch (Exception e) {
            resMsg = "{ Send message error }";
            return new ResponseEntity<>(
                    resMsg,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping("/getAllByTicket")
    public ResponseEntity<?> sendMsg(@RequestBody Ticket ticket){
        try{
            List<Message> messages = messageDao.findAllByTicketId(ticket.getId());
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
                    messageDao.findById(message.getId()),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<?> deleteTicket(@RequestBody Message message) {
        String resMsg;
        try {
            if(messageDao.existsById(message.getId())) {
                messageDao.delete(message);
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
