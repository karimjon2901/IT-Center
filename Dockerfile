FROM openjdk:17
EXPOSE 9099
ADD .mvn/wrapper/maven-wrapper.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]