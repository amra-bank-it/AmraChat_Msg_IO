package com.example.amrachat_msg_io_api.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Messages")
public class Message {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long Id;
    private long ticketId;
    private LocalDateTime datetime;
    private long clientId;
    private  String msgBody;

    public Message(){}

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getTicketId() {
        return ticketId;
    }

    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    @Override
    public String toString() {
        return "Message{" +
                "Id=" + Id +
                ", ticketId=" + ticketId +
                ", datetime=" + datetime +
                '}';
    }
}
