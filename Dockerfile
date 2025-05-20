# Fase 1: compilar el .jar con dependencias
FROM maven:3.9.5-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Fase 2: imagen final ligera
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/testgrupocastilla-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
