# Use lightweight Eclipse Temurin JDK 21 image (supports ARM/M1/M2)
FROM eclipse-temurin:21-jdk-jammy AS build

WORKDIR /app

# Install dos2unix to fix line endings if necessary
RUN apt-get update && apt-get install -y dos2unix

# Copy Maven files and normalize line endings
COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN dos2unix mvnw && chmod +x mvnw
RUN ./mvnw dependency:go-offline -B

# Copy the source code
COPY src src

# Build the Spring Boot jar
RUN ./mvnw clean package -DskipTests

# Runtime stage (minimal JRE)
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copy jar from build stage
COPY --from=build /app/target/quiz-connect-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8080

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
