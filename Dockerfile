#FROM gradle:7.1.1-jdk8 AS build
#COPY --chown=gradle:gradle . /build/inversion-spring-boot-main/
#COPY --chown=gradle:gradle ../inversion-engine /build/inversion-engine/

#WORKDIR /build/inversion-spring-boot-main
#RUN gradle build --refresh-dependencies --no-daemon --console verbose

FROM openjdk:17-alpine
EXPOSE 8080
# Add a volume pointing to /tmp
VOLUME /tmp

ADD /build/libs/helloworld.jar helloworld.jar

#ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/helloworld.jar"]
ENTRYPOINT ["java", "-jar","/helloworld.jar"]