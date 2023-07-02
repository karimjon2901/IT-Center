FROM openjdk:17
EXPOSE 9099

COPY . .
ENTRYPOINT ["java", "-jar", "/spring-boot-docker.jar"]