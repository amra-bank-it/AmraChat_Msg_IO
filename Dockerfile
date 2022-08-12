FROM openjdk:18
EXPOSE 8081
ADD target/AmraChat_msg_io_API-0.0.1-SNAPSHOT.jar AmraChat_msg_io_API-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/AmraChat_msg_io_API-0.0.1-SNAPSHOT.jar"]
