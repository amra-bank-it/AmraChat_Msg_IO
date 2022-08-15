package com.example.amrachat_msg_io_api.dbInit;


import com.example.amrachat_msg_io_api.dao.UsersDao;
import com.example.amrachat_msg_io_api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AfterSetup {
    @Autowired
    UsersDao usersDao;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        if(usersDao.findByFirstname("support") == null){
            User user = new User();
                user.setFirstname("support");
                user.setLastname("amra");
                user.setSurname("chat");
                user.setDepartment("Amra");
                user.setPermissions("support");
            usersDao.save(user);
        }
        System.out.println("Yaaah, I am running........");
    }
}


