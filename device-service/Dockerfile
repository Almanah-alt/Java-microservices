FROM openjdk:8
ADD target/device-service.jar device-service.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "device-service.jar"]