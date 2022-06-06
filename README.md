# SECURE API TEST

Small application created to test my knowledge about APIs.

Made with Spring Boot using Spring Web, Spring Security and Spring JPA. Using also H2DataBase as a simple and quick to implement a database.

### Requirements

For Building and running the application you need:
 - [JDK11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)

### Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `dev.auma.secure_api.SecureApiApplication` class from your IDE.

### Structure

I have organized this project having in mind the following structure:

```
.
└── dev.auma.secure_api
    └── controller                  <-- Controllers of the different endpoints
    └── exception                   <-- Custom exceptions in the application
    └── model                       <-- Entities of my application
    └── repository                  <-- Interfaces of the repositories
    └── security                    <-- Security configuration of the application
        └── filter                  <-- Custom authentication and authorization
    └── service                     <-- Interfaces of the services
        └── impl                    <-- Implementation of the services (Business Logic)
    └── SecureApiApplication.java   <-- Main class to initialize the application
```

### Next steps

These will be the following points for the development of this app:

 1. Implement testing with JUnit
    1. Add unit tests for the Services mocking the repositories
    2. Add tests for the controllers mocking the possible results of the service
    3. Add security tests
 2. Improve response of the endpoints of the controllers
    1. Improve the endpoint documentation
 3. Add refresh token
 4. Add hexagonal architecture: separate model from entities
 5. Clean SecureApiApplication.java:
    1. Add OpenAPIConfig
    2. Initialize data in another utils class
