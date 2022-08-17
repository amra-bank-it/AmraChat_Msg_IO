package com.example.amrachat_msg_io_api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AmraChatMsgIoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmraChatMsgIoApiApplication.class, args);
    }

}
