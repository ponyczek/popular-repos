# Use a base image with JDK
FROM openjdk:17-jdk-slim-buster

# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR file into the container
COPY target/popular-repos-0.0.1-SNAPSHOT.jar popular-repos.jar

# Expose the port on which the application runs
EXPOSE 8080

# Specify the command to run the application
CMD ["java", "-jar", "popular-repos.jar"]