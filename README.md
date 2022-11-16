# illustration_RESTAPI

***
Short Description about the project.

## Technologies
***
A list of technologies used within the project:
* Java: Version 17
* Spring boot: Version 2.7.5
* Maven
* MYSQL

## Installation
***
A little intro about the installation.
```
We need Java installed version 17 (minimum)
* Create an MYSQL database <your_database>
* Open the file src/main/ressources/applications.yml and update the below code
server:
  port: 8080

---
spring:
  profiles:
    active: dev

---
spring:
  profiles: dev
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: <your_username>
    url: jdbc:mysql://localhost:3306/<your_database>
    password: '<your_password>'
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: 'true'

## Test API
CategoryController api endpoint
* Endpoint: /Category/Create  Methode:POST
* Endpoint: /Category/List  Methode:GET
* Endpoint: /Category/GetBy/{name}  Methode:GET
* Endpoint: /Category/DeleteByName/{name}  Methode:DELETE
* Endpoint: /Category/Update/{id}  Methode:PUT

ItemController api endpoint
* Endpoint: /Item/Create  Methode:POST
* Endpoint: /Item/List  Methode:GET
* Endpoint: /Item/ListByCategory/{category}  Methode:GET
* Endpoint: /Item/GetBy/{name}  Methode:GET
* Endpoint: /Item/DeleteById/{name}  Methode:DELETE
* Endpoint: /Item/Update/{id}  Methode:PUT




