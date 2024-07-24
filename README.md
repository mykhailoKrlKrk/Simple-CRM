# Simple CRM 📊
## Overview

**Simple CRM** is a streamlined customer relationship management system designed to efficiently manage client and contact information, tasks, and interactions. Developed using Spring Boot, this application leverages Spring Data JPA for database interactions with PostgreSQL.

## Features

- **Client Management**:
  - Create, view, update, and delete client information including company name, industry, and address.

- **Contact Management**:
  - Manage contact details such as name, email, phone number, and associate them with specific clients.

- **Task Management**:
  - Create and manage tasks with descriptions, statuses (Open, In Progress, Completed), due dates, and responsible contacts.

- **Interaction Tracking**:
  - Track tasks and interactions for each client and contact, and view their associated details.

- **Search Functionality**:
  - Search for clients and contacts based on various criteria.

## Technical Features

- **Spring Boot**: Core framework for building the application.
- **Spring Data JPA**: For ORM and database interactions with PostgreSQL.
- **Redis**: For caching frequently requested data, such as client and task lists.
- **Spring Security**: To handle authentication and authorization.
- **Frontend**: Implemented using either Thymeleaf or React.
- **Testing**: Unit and integration tests to cover key functionality.
  
- **API Integration**: Implement API endpoints for interacting with external systems.
- **Data Export**: Functionality to export data to Excel or PDF formats.
- **Logging**: Set up logging using ELK stack or similar technologies.




