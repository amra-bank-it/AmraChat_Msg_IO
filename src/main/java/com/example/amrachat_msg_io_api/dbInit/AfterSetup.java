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
    private final ClientsDao clientsDao;
    private final UsersDao usersDao;
    private final TicketStatusDao ticketStatusDao;
    private final MessagesDao messagesDao;

    @Autowired
    public AfterSetup(TicketsDao ticketsDao, ClientsDao clientsDao, UsersDao usersDao, TicketStatusDao ticketStatusDao, MessagesDao messagesDao) {
        this.ticketsDao = ticketsDao;
        this.clientsDao = clientsDao;
        this.usersDao = usersDao;
        this.ticketStatusDao = ticketStatusDao;
        this.messagesDao = messagesDao;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        if(!usersDao.existsById(1L)){
            User user =
                    new User(1L,
                            "support",
                            "amra",
                            "chat",
                            "Support",
                            "Amra"
                            );

            usersDao.save(user);
        }
        if(!clientsDao.existsById(1L)){
            Client client = new Client(
                    1L,
                    "TestClient",
                    "TestClient",
                    "TestClient",
                    "+00000000000"

            );
            clientsDao.save(client);
        }
        if (!ticketsDao.existsById(1L)){
            Ticket ticket = new Ticket(
                    1L,
                    ticketStatusDao.findById(4L),
                    clientsDao.findById(1L),
                    usersDao.findById(1L),
                    "testTicket"
            );
            ticketsDao.saveAndFlush(ticket);
        }
        if(!messagesDao.existsById(1L)){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            Message message = new Message(
                    1L,
                    ticketsDao.findById(1L),
                    LocalDateTime.parse("2020-01-01 21:00:01",formatter),
                    //DateTime.currentDT("yyyy-MM-dd HH:mm:ss"),
                    clientsDao.findById(1L),
                    "test message"
            );
            messagesDao.save(message);
        }
        System.out.println("Yaaah, I am running........");
    }
}


