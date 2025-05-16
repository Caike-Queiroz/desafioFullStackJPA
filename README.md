### Frontend do projeto está disponível em: https://github.com/jacqueline-oliveira/3356-java-desafio-front.git 

## docker-compose.yml

    version: "3.8"
    services:
      frontend:
        build:
          context: ./frontend/3356-java-desafio-front
          dockerfile: Dockerfile
        container_name: frontend-frases
        ports:
          - "3000:80"
        depends_on:
          backend:
            condition: service_healthy
  
    backend:
      build:
        context: ./backend/DesafioFullStackJPA
        dockerfile: Dockerfile
      container_name: backend-frases
      ports:
        - "8080:8080"
      environment:
        - SPRING_PROFILES_ACTIVE=docker
      healthcheck:
        test: ["CMD", "curl", "-f", "http://localhost:8080/series/frases"]
        interval: 10s
        timeout: 5s
        retries: 5
      depends_on:
        db:
          condition: service_healthy
  
    db:
      image: bitnami/postgresql:latest
      container_name: postgresql
      ports:
        - "5432:5432"
      environment:
        POSTGRES_DB: screenmatch_frases
        POSTGRES_USER: postgres
        POSTGRES_PASSWORD: Root2025@$
      volumes:
        - pgdata:/bitnami/postgresql
      healthcheck:
        test: ["CMD", "pg_isready", "-U", "postgres"]
        interval: 10s
        timeout: 5s
        retries: 5
  
    volumes:
      pgdata:

## dockerfile (frontend)

    FROM nginx:alpine
    COPY . /usr/share/nginx/html
    EXPOSE 80

## dockerfile (backend)

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

## Estrutura de arquivos

![image](https://github.com/user-attachments/assets/cfe1527e-90c8-4c4c-a7db-e6f80dabc1ad)
