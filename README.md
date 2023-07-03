# News REST project #
The service is just a simple news REST service. It uses an in-memory database (H2) to store the data.

## Stack of technologies ##
Spring Boot, H2 database, Spring Data JPA, REST, Spring Security(with JWT), AOP logging

## Overview ##
Users should be able to create news or comments to news. A user with the admin role can perform any operations. A user with the role of journalist can only perform crud operations on his own news. A user with the subscriber role can only perform crud operations with his comments. Unregistered users can view news and comments.

## Features include ##

* News CRUD
* Comments CRUD
* Post limiting for pagination
* Post sorting
* Role based authentication
* Custom Exception handling
* JWT authentication
* Role specific API access
* Data Validation using Hibernate validator
* Documentation using Swagger
## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/grisatkac/news-rest.git
```

**2. Run the application using maven**

```bash
From from the command line, from the root of the project:
* mvn package
* mvn spring-boot:run
 ```
The app will start running at http://localhost:8080

## Explore Rest APIs

**1. Swagger documentation**
```bash
You can see swagger documentation of the project using link: http://localhost:8080/swagger-ui/index.html.
 ```
**1. Postman requests**
```bash
You can test aaplication using postman request. You can see postman requests here: https://github.com/grisatkac/news-rest/blob/master/src/main/resources/news.postman_collection.json
 ```


