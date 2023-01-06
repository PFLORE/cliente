FROM openjdk:8-jdk-alpine

EXPOSE 8002

ADD ./target/ws-cliente.jar ws-cliente.jar

ENTRYPOINT ["java","-jar","/ws-cliente.jar"]