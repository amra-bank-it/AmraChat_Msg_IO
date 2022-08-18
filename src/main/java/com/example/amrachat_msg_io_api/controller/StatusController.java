package com.example.amrachat_msg_io_api.controller;

import com.example.amrachat_msg_io_api.dao.TicketStatusDao;
import com.example.amrachat_msg_io_api.model.TicketStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/status")
public class StatusController {

    private final TicketStatusDao ticketStatusDao;

    @Autowired
    public StatusController(TicketStatusDao ticketStatusDao) {
        this.ticketStatusDao = ticketStatusDao;
    }

    @PostMapping("/getStatus")
    public ResponseEntity<?> getStatus(@RequestBody TicketStatus ticketStatus) {
        try {
            TicketStatus status = ticketStatusDao.findById(ticketStatus.getId());
            return new ResponseEntity<>(
                    status.getTitle(),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/allStatuses")
    public ResponseEntity<?> allStatuses() {
        try {
            List<TicketStatus> statuses = ticketStatusDao.findAll();
            return new ResponseEntity<>(
                    statuses,
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
