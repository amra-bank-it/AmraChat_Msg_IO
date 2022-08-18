package com.example.amrachat_msg_io_api.dbInit;

import com.example.amrachat_msg_io_api.dao.*;
import com.example.amrachat_msg_io_api.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class AfterSetup {

    private final TicketsDao ticketsDao;
    private final MessagesDao messagesDao;

    @Autowired
    public AfterSetup(TicketsDao ticketsDao,  MessagesDao messagesDao) {
        this.ticketsDao = ticketsDao;
        this.messagesDao = messagesDao;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {

        if (!ticketsDao.existsById(1L)){
            ticketsDao.addFirstTicket();
        }
        if(!messagesDao.existsById(1L)){
            messagesDao.addFirstMessage();
        }
        System.out.println("Yaaah, I am running........");
    }
}


