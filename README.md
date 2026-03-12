# 📚 Bug Tracker API

A RESTful API for bug tracking inspired by Jira and GitHub Issues. Built with **Java 21**, **Spring Boot 3.5.11**, 
**Maven**, **PostgreSQL**, and **Docker**.

It provides endpoints for user authentication, issue management, project-specific issues, and search functionality.

## 📌 Table of Contents

- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Requirements](#-requirements)
- [Getting Started](#-getting-started)
- [Running with Docker](#-running-with-docker)
- [API Endpoints](#-api-endpoints)
- [Testing](#-testing)
- [Swagger Documentation](#-swagger-documentation)
- [License](#-license)

---

## 🚀 Features

- User registration and login with JWT authentication
- Role-based access control (`ADMIN`, `DEVELOPER`, `REPORTER`)
- CRUD operations for issues
- Assign developers to issues
- Pagination, filtering, and search
- Rate limiting for security
- OpenAPI/Swagger documentation

---

## 🛠 Tech Stack

- **Java 21**
- **Spring Boot 3.5.11**
- **Spring Data JPA**
- **Spring Security + JWT**
- **PostgreSQL**
- **Lombok**
- **Validation**
- **Bucket4j** (rate limiting)
- **Springdoc OpenAPI**
- **Maven**
- **Docker**

---

## 📦 Requirements

- Java 21
- Maven
- Docker & Docker Compose (optional)
- PostgreSQL (if you don't use Docker)

---

## 🔧 Getting Started

### Clone the repository

```bash
git clone https://github.com/Gesiath/bug-tracker-api.git
cd bug-tracker-api
```

### Build the project

```bash
mvn clean package
```

### Run locally

Make sure you have PostgreSQL running and configured in `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/bugtracker
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

Run the application:

```bash
mvn spring-boot:run
```

Access the API at `http://localhost:8080`.

---

## 🐋 Running with Docker

### Build Docker image

```bash
docker build -t bug-tracker-api .
```

### Run container

```bash
docker run -d -p 8080:8080 --name bug-tracker-api \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/bugtracker \
  -e SPRING_DATASOURCE_USERNAME=your_username \
  -e SPRING_DATASOURCE_PASSWORD=your_password \
  bug-tracker-api
```

---

## 📄 API Endpoints

| Método   | Ruta                                       | Descripción                                |
|----------|--------------------------------------------|--------------------------------------------|
| `POST`   | `/api/v1/auth/register`                    | Register a new user                        |
| `POST`   | `/api/v1/auth/login`                       | Login and receive JWT                      |
| `GET`    | `/api/v1/issues`                           | Get all issues (with filters & pagination) |
| `GET`    | `/api/v1/issues/{id}`                      | Get issue by ID                            |
| `POST`   | `/api/v1/issues`                           | Create a new issue                         |
| `PUT`    | `/api/v1/issues/{id}`                      | Update an existing issue                   |
| `DELETE` | `/api/v1/issues/{id}`                      | Delete an issue                            |
| `GET`    | `/api/v1/issues/project/{projectId}`       | Get issues for a specific project          |
| `PATCH`  | `/api/v1/issues/{id}/status`               | Update issue status                        |
| `PATCH`  | `/api/v1/issues/{id}/assign/{developerId}` | Assign a developer                         |
| `GET`    | `/api/v1/issues/search?query=...`          | Search issues                              |

> The API is protected with roles (`ADMIN`, `DEVELOPER`, `REPORTER`). 
> JWT is required on endpoints that need it.

---

## 🧪 Testing

The API includes unit and integration tests with JUnit 5 and Mockito.

```bash
# Run all tests
mvn test

# Run full package (build + tests)
mvn clean package
```

---

## 📜 Swagger Documentation

Once the application is up and running, the OpenAPI/Swagger documentation will be available at:

```bash
http://localhost:8080/swagger-ui.html
```

---

## 📝 License

This project is licensed under the MIT License.