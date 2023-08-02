FROM openjdk:17
EXPOSE 9099
COPY .mvn/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]