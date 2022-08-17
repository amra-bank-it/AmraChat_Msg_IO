package com.example.amrachat_msg_io_api.dao;

import com.example.amrachat_msg_io_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersDao extends JpaRepository<User,Long> {
    User findByFirstname(String firstname);
    User findById(long id);
}
