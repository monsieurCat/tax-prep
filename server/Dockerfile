FROM eclipse-temurin:17-jdk

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://group11-proj2-db-instance-1.c4eqo06kg56i.us-east-1.rds.amazonaws.com:5432/tax-prep
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=password123

ENV SERVER_PORT=80
 
WORKDIR /app
 
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
 
COPY src ./src

EXPOSE 80
 
CMD ["./mvnw", "spring-boot:run"]