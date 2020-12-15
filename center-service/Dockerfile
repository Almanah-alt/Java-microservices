FROM openjdk:8
ADD target/center-service.jar center-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "center-service.jar"]