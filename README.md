# Popular Repos

A simple spring boot application that fetches the most popular repositories from GitHub.

[![Java v17][shield-java]](https://openjdk.java.net/projects/jdk/17/)
[![Spring Boot v3.1.0][shield-spring-boot]](https://spring.io/projects/spring-boot)

## Requirements

The list of tools required to build and run the project:

* Open JDK 17.x+
* Apache Maven 3.5.x+

## Building

In order to build project use:

```bash
mvn clean install
```

If your default `java` is not from JDK 17 or higher use:

```bash
JAVA_HOME=<path_to_jdk_home> mvn package
```

## Running

In order to run the application (by default port 8080):

### Via maven
```bash
mvn spring-boot:run
```
### Via docker
```bash
# Build the Docker image
docker build -t popular-repos .

# Run the Docker container
docker run -p 8080:8080 popular-repos
```

## API used

Official Github REST API documentation: [https://docs.github.com/en/rest?apiVersion=2022-11-28](https://docs.github.com/en/rest)

## Endpoints

| Endpoint                                           | Description                                                     | Query Parameters                            |
|----------------------------------------------------|-----------------------------------------------------------------|---------------------------------------------|
| http://localhost:8080/top-repository/github        | Get highest rated repositories from Github from specified date. | - fromDate*<br/>- limit<br/>- language<br/> |
| ~~http://localhost:8080/top-repository/bitbucket~~ | Not supported yet.                                              |                                             |



## Libraries used

- **WebClient**: Webflux 
- **Lombok**: Reduce boilerplate code
- **JUnit5**: Unit testing
- **okhttp3** Integration testing
- **Mockito**: Mocking

[shield-mit]: https://img.shields.io/badge/license-MIT-blue.svg
[shield-java]: https://img.shields.io/badge/Java-17-blue.svg
[shield-spring-boot]: https://img.shields.io/badge/Spring_Boot-3.1.0-blue.svg
