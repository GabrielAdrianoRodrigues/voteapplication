# Utilizo a openjdk 17 slim por ser mais leve durante o build
FROM maven:3-openjdk-17-slim as build
# Copio o pom para uma pasta build dentro do container
COPY pom.xml /build/
# Copio a src tambem
COPY src /build/src
# Defino que vou utilizar a /build
WORKDIR /build
# Entao buildo o maven copiado
RUN mvn package 
# Defino a jdk da aplicacao
FROM openjdk:17-jdk-slim
# Mudo para o diretorio /app
WORKDIR /app
# Copio a pasta resultante do build para a /app do container
COPY --from=build /build/target/voteapplication-0.0.1-SNAPSHOT.jar /app/
# Defino o volume
VOLUME /tmp
# Exponho a porta
EXPOSE $PORT
# E por fim executo o jar
ENTRYPOINT ["java", "-Dserver.port=$PORT", "-Xmx256m", "-Xss512k", "-jar", "voteapplication-0.0.1-SNAPSHOT.jar"]