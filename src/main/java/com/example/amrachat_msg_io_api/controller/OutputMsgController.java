package com.example.amrachat_msg_io_api.controller;

import com.example.amrachat_msg_io_api.dao.MessagesDao;
import com.example.amrachat_msg_io_api.model.Message;
import com.example.amrachat_msg_io_api.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/outputMsg")
public class OutputMsgController {
    @Autowired
    MessagesDao messagesDao;


    @PostMapping("/ByTicketId")
    public ResponseEntity<?> sendMsg(@RequestBody Ticket ticket){
        try{
            List<Message> messages = messagesDao.findAllByTicketId(ticket.getId());
            return new ResponseEntity<>(
                    messages,
                    HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
