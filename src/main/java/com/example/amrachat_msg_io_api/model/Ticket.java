package com.example.amrachat_msg_io_api.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "Tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private long statusId;

    private long clientId;

    private long userId;

    private String theme;

    public Ticket(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStatus() {
        return statusId;
    }

    public void setStatus(long statusId) {
        this.statusId = statusId;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setClientID(long clientId) {
        this.clientId = clientId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", status='" + statusId + '\'' +
                ", clientID=" + clientId +
                ", userId=" + userId +
                '}';
    }
}
