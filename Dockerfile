FROM openjdk:17
EXPOSE 8080
COPY .mvn/wrapper/maven-wrapper.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
