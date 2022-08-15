package com.example.amrachat_msg_io_api.dao;

import com.example.amrachat_msg_io_api.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketsDao extends JpaRepository<Ticket,Long> {
    Ticket findById(long Id);
    Ticket findByStatusId(long Id);

}
