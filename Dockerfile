FROM maven:3.9.11-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY mvnw .
COPY .mvn/wrapper/ .mvn/wrapper/
COPY src ./src
RUN ./mvnw package -DskipTests

FROM eclipse-temurin:17-jre-jammy

RUN apt-get update && apt-get install -y bash netcat-openbsd && rm -rf /var/lib/apt/lists/*

WORKDIR /app
EXPOSE 8080
ENV SPRING_DATA_REDIS_HOST=redis
ENV SPRING_DATA_REDIS_PORT=6379
COPY --from=build /app/target/StudentManager-0.0.1-SNAPSHOT.jar StudentManager.jar

ENTRYPOINT ["java", "-jar", "StudentManager.jar"]