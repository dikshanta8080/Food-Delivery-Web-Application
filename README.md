# Full-Stack Food Delivery System

A **full-stack Food Delivery System** built using **Spring Boot** (Backend) and **React** (Frontend).  
This project allows users to **browse restaurants**, **place orders**, and **track deliveries in real-time**.  
It includes separate dashboards for **Admin**, **Restaurant Owners**, and **Customers**.

- Designed and implemented a **scalable user address management ** using Spring Boot and JPA, handling multiple addresses per user.  
- Built **hierarchical location tables** (Province → District → Municipality) with bidirectional and unidirectional relationships for optimized queries and frontend filtering.  
---

## Requirements

- **IntelliJ IDEA**   
- **JDK Eclipse Adoptium 21** – Java Development Kit  
- **Maven** – Build and dependency management tool  
- **PostgreSQL** – Relational database for data storage  

---
## Configuration

`application-dev.yml` has been added to `.gitignore` for privacy.  
Create this file in your project and use the following **boilerplate**, replacing parameters with your machine’s configuration if environment variables are not set:

```yaml
management:
  endpoint:
    show-details: always
  endpoints:
    web:
      exposure:
        include: "*"

logging:
  level:
    com:
      dikshanta:
        food:
          delivery:
            foodDeliveryBackend: DEBUG

spring:
  security:
    user:
      name: ${SPRING_SECURITY_USER:root}       # defaults to 'root' if environment variable is not set
      password: ${SPRING_SECURITY_PASSWORD:root}  # defaults to 'root' if environment variable is not set

  datasource:
    hikari:
      auto-commit: false
    driver-class-name: org.postgresql.Driver
    url: jdbc:postgresql://${DB_HOST:localhost}:${DB_PORT:5432}/${DB_NAME:syp_db}
    username: ${DB_USERNAME:postgres}
    password: ${DB_PASSWORD:root}

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect

  servlet:
    multipart:
      max-file-size: 10MB
      max-request-size: 10MB
  mail:
    host: smtp.gmail.com
    port: 587
    username: yourmailexample@gmail.com
    password: your app password
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
