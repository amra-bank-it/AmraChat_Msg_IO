package com.example.amrachat_msg_io_api.dao;

import com.example.amrachat_msg_io_api.model.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketStatusDao extends JpaRepository<TicketStatus,Long> {
    TicketStatus findById(long id);
}
