ARG masterip
ARG id
FROM alpine/git
WORKDIR /app
RUN git clone https://github.com/spagnuolocarmine/p2ppublishsubscribe.git

FROM maven:3.5-jdk-8-alpine
WORKDIR /app
COPY --from=0 /app/p2ppublishsubscribe /app
RUN mvn package

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=1 /app/target/publishsubscribe-1.0.jar /app

CMD ["/usr/bin/java","-jar","publishsubscribe-1.0.jar","${masterip}","${id}"]