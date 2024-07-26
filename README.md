# Simple CRM 📊
 ![Java Badge](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring Boot Badge](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot) ![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=Spring-Security&logoColor=white) ![Hibernate Badge](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white) ![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=white) ![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white) ![Redis](https://img.shields.io/badge/redis-%23DD0031.svg?style=for-the-badge&logo=redis&logoColor=white)
## Overview

**Simple CRM** is a streamlined customer relationship management system designed to efficiently manage client and contact information, tasks, and interactions. Developed using Spring Boot, this application leverages Spring Data JPA for database interactions with PostgreSQL.

## Features
| Feature                       | Description                                                                 |
|-------------------------------|-----------------------------------------------------------------------------|
| **Client Management**         | Create, view, update, and delete client information including company name, industry, and address. |
| **Contact Management**        | Manage contact details such as name, email, phone number, and associate them with specific clients. |
| **Task Management**           | Create and manage tasks with descriptions, statuses (Open, In Progress, Completed), due dates, and responsible contacts. |
| **Interaction Tracking**      | Track tasks and interactions for each client and contact, and view their associated details. |
| **Search Functionality**      | Search for clients and contacts based on various criteria. |

## ⚙️ Technical Features
---
The main framework of the project is Spring Boot which provides various tools for developing web applications with wide functionality.

### Pom.xml dependencies overview

| Feature                           | Usage                                                                  |
|-----------------------------------|------------------------------------------------------------------------|
| **Spring Boot Security and JWT Token** | Enable secure user authentication and data protection.                  |
| **Spring Data JPA**               | This feature enables users to interact with the database and perform various operations. It provides a range of functionalities to manipulate and manage the data stored in the database, including creation, updating, and removal. |
| **Liquibase**                     | Effectively manage database structure with generic scripts that can be adapted to different types of databases. |
| **Integration tests using Testcontainers** | With Testcontainers, tests run against a real database instance, ensuring the validity of SQL queries and transactions. |
| **Hibernate**                     | An object-relational mapping (ORM) framework that simplifies database interaction in Spring Boot applications. It automates the mapping of Java classes to database tables, streamlining data persistence and retrieval operations. |
| **Mapstruct and Lombok**          | These tools simplify the process of mapping objects between Java beans. By generating mapping code and providing annotations for common methods like getters, setters, and constructors, these tools reduce boilerplate code and make Java code more concise and readable. When used together in Spring Boot applications, they enhance code maintainability and improve developer productivity. |
| **Redis**                         | Used for caching frequently requested data, such as client and task lists, to improve performance and reduce load on the database. |
| **PostgreSQL**                    | The primary database used for storing all application data, ensuring robust and reliable data management. |
| **Apache POI**                    | A library used to export data from DB to Exel format.  |
| **Jacoco**                        | A code coverage tool used to measure the effectiveness of tests by reporting the percentage of code covered. This helps ensure that critical parts of the application are thoroughly tested. |
| **Swagger**                       | Provides interactive API documentation, allowing users to explore and test API endpoints directly from a web interface. This improves the usability and accessibility of the API. |


### 🛠️ Integration and Usage
---
List of technologies required for use: 

- Docker
- PostgreSQL
- Redis
- Additional: Postman (for testing endpoints)  
#### 🚀 Run Project

Please make sure that you have all the required technologies installed before proceeding. Once that's done, follow the steps below:

1. Fork this repository.
2. Run Docker, Radis and PostgreSQL servers.
3. For testing use Swagger, open your browser and enter the URL: http://localhost:8080/api/swagger-ui/index.html#/. To authenticate, use the following credentials: User: test@mail.com, Password: password (this is the default user created by Liquibase, but you can change the details if needed).




