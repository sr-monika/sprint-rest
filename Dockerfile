FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /workspace/app

USER root

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN chmod +x mvnw
RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

COPY target/edip-inventory-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/workspace/app/app.jar"]