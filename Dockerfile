FROM openjdk:17-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
WORKDIR /app
COPY target/*.jar /app/vote.jar
EXPOSE $PORT
ENTRYPOINT ["java","-Xmx512m","-DServer.port=$PORT","-jar","vote.jar"]