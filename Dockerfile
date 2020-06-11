FROM openjdk:8-jdk-alpine
COPY /target/RoomsApi.jar room-app.jar
WORKDIR /opt/app
ENTRYPOINT ["java", "-jar", "room-app.jar" ]