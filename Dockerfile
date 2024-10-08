FROM openjdk:17-alpine
VOLUME /tmp
ARG JAR_FILE=target/shop-user-api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT [ "java","-jar","/app.jar"]