package com.example.amrachat_msg_io_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "ticket_Status")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TicketStatus {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(name = "title")
    private String title;

    public TicketStatus() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String status) {
        this.title = status;
    }

    @Override
    public String toString() {
        return "TicketStatus{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
