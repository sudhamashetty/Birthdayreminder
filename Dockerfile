# Use a Maven image to build the project
FROM maven:3.9.4-eclipse-temurin-17 as build

# Set the working directory
WORKDIR /app

# Copy all files into the container
COPY . .

# Make the Maven wrapper script executable
RUN chmod +x mvnw

# Build the project, skipping tests to save time
RUN ./mvnw clean package -DskipTests

# Use a lightweight JDK image to run the app
FROM eclipse-temurin:17

# Set working directory
WORKDIR /app

# Copy the JAR file from the build container
COPY --from=build /app/target/BirthdayReminder-0.0.1-SNAPSHOT.jar BirthdayReminder.jar

# Expose the application port
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "BirthdayReminder.jar"]