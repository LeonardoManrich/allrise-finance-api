#
# Build stage
#
FROM gradle:latest AS build
COPY src /app/src
COPY build.gradle.kts /app
COPY settings.gradle.kts /app
RUN gradle -b /app/build.gradle.kts clean build

#
# Package stage
#
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ENV JAVA_TOOL_OPTIONS="-agentlib:jdwp=transport=dt_socket,address=5005,server=y,suspend=n -Xdebug"
ARG JAR_FILE=/app/build/libs/*-SNAPSHOT.jar
COPY --from=build ${JAR_FILE} njord-api.jar
ENTRYPOINT ["java", "-jar", "/njord-api.jar"]
