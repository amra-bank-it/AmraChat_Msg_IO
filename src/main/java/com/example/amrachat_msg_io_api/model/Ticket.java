package com.example.amrachat_msg_io_api.model;

import com.example.amrachat_msg_io_api.dao.TicketStatusDao;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "Tickets")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private long ticketStatusId;

    private long clientId;

    @Nullable
    private long userId;

    private String theme;


    public Ticket(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTicketStatusId(long ticketStatusId) {
        this.ticketStatusId = ticketStatusId;
    }

    public long getTicketStatusId() {
        return ticketStatusId;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", ticketStatusId=" + ticketStatusId +
                ", clientId=" + clientId +
                ", userId=" + userId +
                ", theme='" + theme + '\'' +
                '}';
    }
}
