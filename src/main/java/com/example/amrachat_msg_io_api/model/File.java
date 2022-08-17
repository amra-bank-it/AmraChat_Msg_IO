package com.example.amrachat_msg_io_api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "msg_files")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class File {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private Byte[] encrypted;

    public File(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Byte[] getEncrypted() {
        return encrypted;
    }

    public void setEncrypted(Byte[] encrypted) {
        this.encrypted = encrypted;
    }

    @Override
    public String toString() {
        return "File{" +
                "Id=" + id +
                ", encrypted=" + Arrays.toString(encrypted) +
                '}';
    }
}
