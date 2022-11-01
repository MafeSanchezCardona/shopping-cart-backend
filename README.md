# shopping-cart-backend

This project is the backend and contains the APIs that will be used from the frontend.

The project was made with Java 17, Spring Boot 2.7.5, H2 Database, JPA. The lombok library is used to simplify the creation of get and set and some other methods such
as constructors.

## The structure of the project
The structure of the project is composed of:
● controller: In this folder are the controllers to expose the REST API
● dto: In this folder are the Data Transfer Objects
● entities: Are the entities equivalent to the tables in the database (JPA)
● repositories: Equivalent of DAO
● services: In this folder are the classes with business logic.

In the project there are other folders as resources, it's Contains the data.sql and application.yml
