package com.example.amrachat_msg_io_api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Messages")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name = "ticket_id")
    private long ticketId;

    @Column(name = "client_id")
    @JsonProperty(value = "clientId")
    private long clientId;

    @Column(name = "datetime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime datetime;

    private  String msgBody;

    boolean isClientMsg;

    @Transient
    @JsonProperty(value = "theme")
    private String theme;

    public Message(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonIgnore
    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public boolean isClientMsg() {
        return isClientMsg;
    }

    public void setClientMsg(boolean clientMsg) {
        isClientMsg = clientMsg;
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

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public String getMsgBody() {
        return msgBody;
    }


    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }
    @JsonIgnore
    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "Id=" + id +
                ", ticketId=" + ticketId +
                ", datetime=" + datetime +
                ", msgBody='" + msgBody + '\'' +
                '}';
    }
}
