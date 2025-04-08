# Use a Maven image to build the project
FROM maven:3.9.4-eclipse-temurin-17 as build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Use a JDK image to run the app
FROM eclipse-temurin:17
WORKDIR /app
COPY --from=build /app/target/BirthdayReminder-0.0.1-SNAPSHOT.jar BirthdayReminder.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "BirthdayReminder.jar"]