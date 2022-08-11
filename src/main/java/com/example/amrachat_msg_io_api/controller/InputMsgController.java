package com.example.amrachat_msg_io_api.controller;

import com.example.amrachat_msg_io_api.dao.MessagesDao;
import com.example.amrachat_msg_io_api.dao.TicketsDao;
import com.example.amrachat_msg_io_api.model.Message;
import com.example.amrachat_msg_io_api.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inputMsg")
public class InputMsgController {
    @Autowired
    private TicketsDao ticketDao;
    @Autowired
    private MessagesDao messageDao;

    @PostMapping("/sendMsg")
    public ResponseEntity<?> sendMsg(@RequestBody Message message){
        try {
            Ticket ticket = new Ticket();

            if (message.getTicketId() == 0) {
                ticket.setStatus(3);
                ticket.setClientID(message.getClientId());
                ticketDao.save(ticket);
                message.setTicketId(ticket.getId());
            }
            messageDao.save(message);

            return new ResponseEntity<>(
                    HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
