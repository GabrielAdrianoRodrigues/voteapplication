FROM maven:3-openjdk-17-slim as build
COPY pom.xml /build/
COPY src /build/src
WORKDIR /build
RUN mvn package -DskipTests
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /build/target/voteapplication-0.0.1-SNAPSHOT.jar /app/
VOLUME /tmp
EXPOSE $PORT
ENTRYPOINT ["java", "-Dserver.port=$PORT", "-Xmx256m", "-Xss512k", "-jar", "voteapplication-0.0.1-SNAPSHOT.jar"]