FROM gradle:7.6.3-jdk17-alpine AS build
COPY --chown=gradle:gradle . /build/app/

WORKDIR /build/app/
RUN gradle build --refresh-dependencies --no-daemon --console verbose


FROM openjdk:17-alpine
EXPOSE 8080

# Add a volume pointing to /tmp
VOLUME /tmp

COPY --from=build /build/app/build/libs/app.jar /build/app/.env* /

ENTRYPOINT ["java","-jar","/app.jar"]