FROM openjdk:18
COPY target/AmraChat_msg_io_API-0.0.1-SNAPSHOT.jar /tmp
WORKDIR /tmp
CMD java -jar /tmp/AmraChat_msg_io_API-0.0.1-SNAPSHOT.jar
EXPOSE 8082