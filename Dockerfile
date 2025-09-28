# Use lightweight Eclipse Temurin JDK 21 image (supports ARM/M1/M2)
FROM eclipse-temurin:21-jdk-jammy AS build

WORKDIR /app

# Copy Maven files and download dependencies first (for caching)
COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN chmod +x mvnw
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
