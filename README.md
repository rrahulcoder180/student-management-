# Student Management Backend

A REST API project using Java 21, Spring Boot, Spring Data JPA, MySQL, Spring Security, and JWT.

## Features
- User registration and login
- JWT authentication
- BCrypt password encryption
- Add, view, update, and delete students

## Student Details
- ID
- Name
- Email
- Branch
- Year

## Database Setup
Run the commands in student_management.sql using MySQL Workbench.
Configure your MySQL username and password in src/main/resources/application.properties.

## Run the Application
Run this command from the project folder:

.\mvnw.cmd spring-boot:run

Application URL: http://localhost:8080

## API Endpoints

| Method | Endpoint | Purpose |
|---|---|---|
| POST | /api/auth/register | Register |
| POST | /api/auth/login | Login and receive JWT |
| POST | /api/students | Add student |
| GET | /api/students | View all students |
| GET | /api/students/{id} | View one student |
| PUT | /api/students/{id} | Update student |
| DELETE | /api/students/{id} | Delete student |

Student endpoints require an Authorization header with a Bearer JWT token.

## Testing
The APIs were tested locally using PowerShell HTTP requests.
