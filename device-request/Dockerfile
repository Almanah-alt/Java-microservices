FROM openjdk:8
ADD target/device-request.jar device-request.jar
EXPOSE 8087
ENTRYPOINT ["java", "-jar", "device-request.jar"]