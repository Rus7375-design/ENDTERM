FROM openjdk:21-oracle
MAINTAINER ali
COPY backend.jar my-backend-spring.jar
ENTRYPOINT ["java", "-jar", "my-backend-spring.jar"]