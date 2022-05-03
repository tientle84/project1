FROM gradle:7.4.2-jdk8 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:8-jre-slim

EXPOSE 7777

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/project1-1.0-SNAPSHOT.jar /app/spring-boot-application.jar
COPY silent-bolt-343919-c45a384f77c0.json /app/silent-bolt-343919-c45a384f77c0.json

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/spring-boot-application.jar"]