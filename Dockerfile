#FROM openjdk:18
#WORKDIR /app
#COPY ./target/spring-0.0.1-SNAPSHOT.jar /app
#EXPOSE 8080
#CMD ["java", "-jar", "spring-0.0.1-SNAPSHOT.jar"]

# Start with a base image containing Java runtime
#FROM openjdk:8-jdk-alpine
#
## Add Maintainer Info
#LABEL maintainer="your.email@example.com"
#
## Add a volume pointing to /tmp
#VOLUME /tmp
#
## Make port 8080 available to the world outside this container
#EXPOSE 8080
#
## The application's jar file
#ARG JAR_FILE=target/example-1.0-SNAPSHOT.jar
#
## Add the application's jar to the container
#ADD ${JAR_FILE} app.jar
#
## Run the jar file
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]


FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY ./target/example-1.0-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java", "-jar", "example-1.0-SNAPSHOT.jar"]
