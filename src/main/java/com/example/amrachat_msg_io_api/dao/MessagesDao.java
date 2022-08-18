package com.example.amrachat_msg_io_api.dao;

import com.example.amrachat_msg_io_api.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MessagesDao extends JpaRepository<Message,Long> {
    List<Message> findAllByTicketId(long Id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO AmraChat.messages (id, datetime, msg_body, ticket_id, client_id, is_client_msg)\n" +
            "VALUES (1, '2022-08-18 16:00:18', 'TestMessage', 1, 1, false);",nativeQuery = true)
    void addFirstMessage();
}
