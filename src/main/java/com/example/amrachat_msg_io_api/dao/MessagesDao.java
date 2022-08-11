package com.example.amrachat_msg_io_api.dao;

import com.example.amrachat_msg_io_api.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessagesDao extends JpaRepository<Message,Long> {
    List<Message> findAllByTicketId(long Id);
}
