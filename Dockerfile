FROM maven:3.9.5-eclipse-temurin-21-alpine AS build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app
RUN mvn clean install

FROM openjdk:21

COPY --from=build /app/target/payment-api-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app

ARG CURRENT_ENV=dev-local

ENV FASTFOOD_API_PORT=${PORT}
ENV FASTFOOD_API_ENVIRONMENT=${CURRENT_ENV}

EXPOSE ${PORT}

RUN echo "### This is a basic dockerfile to Tech Challenge ###"
RUN echo "The application will start in port: ${FASTFOOD_API_PORT};"

ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=${FASTFOOD_API_ENVIRONMENT}", "app.jar"]
