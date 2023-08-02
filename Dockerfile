FROM openjdk:17
EXPOSE 9099
COPY .mvn/wrapper/maven-wrapper.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]