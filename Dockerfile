FROM openjdk:17
COPY .mvn/ .mvn
COPY .mvn pom.xml ./

RUN ./mvnw dependency:resolve

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]