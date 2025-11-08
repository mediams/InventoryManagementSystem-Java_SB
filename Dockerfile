FROM eclipse-temurin:21-alpine AS builder

RUN apk add --no-cache bash

WORKDIR /app

COPY mvnw pom.xml ./
COPY .mvn .mvn

RUN chmod +x ./mvnw

RUN ./mvnw dependency:go-offline

COPY src ./src

RUN ./mvnw clean package -DskipTests && \
    echo "Build complete. Contents of target:" && \
    ls -la target/

FROM eclipse-temurin:21-alpine

RUN apk add --no-cache bash

ENV SPRING_PROFILES_ACTIVE=docker

WORKDIR /app

COPY --from=builder /app/target/*-SNAPSHOT.jar app.jar

RUN addgroup -g 1000 spring && \
    adduser -D -u 1000 -G spring spring && \
    chown spring:spring app.jar

USER spring

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]