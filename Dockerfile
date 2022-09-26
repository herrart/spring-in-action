#FROM maven:3.8.6-eclipse-temurin-17-alpine as builder
#COPY src /usr/src/app/src
#COPY pom.xml /usr/src/app
#RUN mvn -f /usr/src/app/pom.xml clean compile assembly:single -Dmaven.test.skip
#COPY --from=builder /usr/src/app/target/spring-in-action-0.0.1-SNAPSHOT.jar /usr/app/spring-in-action-0.0.1-SNAPSHOT.jar

#Copy from local target foler
FROM eclipse-temurin:17.0.3_7-jre-alpine
COPY /target/spring-in-action-0.0.1-SNAPSHOT.jar /usr/app/spring-in-action-0.0.1-SNAPSHOT.jar



ENTRYPOINT ["java","-jar","/usr/app/spring-in-action-0.0.1-SNAPSHOT.jar"]