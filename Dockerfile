FROM eclipse-temurin:17-jdk-alpine as builder

WORKDIR /app

COPY src src
COPY .mvn .mvn
COPY pom.xml pom.xml
COPY mvnw mvnw

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=builder /app/target/*.jar /app/application.jar

CMD ["java", "-jar", "/app/application.jar"]