package com.example.amrachat_msg_io_api.model;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "msg_files")
public class File {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long Id;
    private Byte[] encrypted;

    public File(){}

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
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
                "Id=" + Id +
                ", encrypted=" + Arrays.toString(encrypted) +
                '}';
    }
}
