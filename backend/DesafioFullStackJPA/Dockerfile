FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

COPY src/main/resources/application-docker.properties /app/application-docker.properties
COPY src/main/resources/schema.sql /app/schema.sql
COPY src/main/resources/initial_data.sql /app/initial_data.sql

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]