package com.example.amrachat_msg_io_api.dao;

import com.example.amrachat_msg_io_api.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface TicketsDao extends JpaRepository<Ticket,Long> {
    Ticket findById(long Id);
    List<Ticket> findAll();

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO AmraChat.tickets (id, theme, client_id, user_id, ticket_status_id)\n" +
            "        VALUES (1, 'TestTicket', 1, 1, 1);",nativeQuery = true)
    void addFirstTicket();
}



