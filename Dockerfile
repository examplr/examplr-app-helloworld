
FROM openjdk:17-alpine
EXPOSE 8080

# Add a volume pointing to /tmp
VOLUME /tmp

ARG JAR_FILE=/build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

ENTRYPOINT ["java", "-jar","/app.j