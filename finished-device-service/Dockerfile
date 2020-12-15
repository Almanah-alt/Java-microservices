FROM openjdk:8
ADD target/finished-device-service.jar finished-device-service.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "finished-device-service.jar"]