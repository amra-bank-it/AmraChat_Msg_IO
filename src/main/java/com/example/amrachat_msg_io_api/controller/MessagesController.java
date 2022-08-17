package com.example.amrachat_msg_io_api.controller;

import com.example.amrachat_msg_io_api.dao.*;
import com.example.amrachat_msg_io_api.model.Client;
import com.example.amrachat_msg_io_api.model.Message;
import com.example.amrachat_msg_io_api.model.Ticket;
import com.example.amrachat_msg_io_api.model.User;
import net.bytebuddy.implementation.bytecode.Throw;
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
    @Autowired
    private TicketStatusDao tickerStatusDao;
    @Autowired
    private ClientsDao clientsDao;

    @PostMapping("/send")
    public ResponseEntity<?> sendMsg(@RequestBody Message message) {
        String resMsg;
        try {
                long clientId = message.getClient().getId();
                long ticketId = message.getTicket().getId();

                if(!clientsDao.existsById(clientId)) {
                    throw new Exception("Client with id: "+ clientId + " does not exist");
                }

                if(!ticketDao.existsById(ticketId)) {
                    throw new Exception("Ticket with id: "+ ticketId + " does not exist");
                }

                Client client = clientsDao.findById(clientId);


            if (message.getTicket().getId() == 0) {
                Ticket ticket = new Ticket();
                ticket.setTicketStatus(tickerStatusDao.findById(3));
                ticket.setClient(client);
                ticket.setUser(usersDao.findByFirstname("support"));
                ticketDao.save(ticket);
                message.setTicket(ticket);
            }else {
                message.setTicket(ticketDao.findById(message.getTicket().getId()));
            }
                message.setClient(client);
                messageDao.save(message);
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
