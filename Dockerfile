FROM openjdk:17-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
EXPOSE $PORT
ENTRYPOINT ["java","-Xmx512m","-DServer.port=$PORT","-jar","voteapplication-0.0.1-SNAPSHOT.jar"]