# 🚀 CloudShop Kubernetes Capstone Project

<div align="center">

# 📚 Day 4 — Authentication Service

### User Registration, Password Security & Login

![Java](https://img.shields.io/badge/Java-21-ED8B00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-Security-6DB33F?logo=springsecurity&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?logo=postgresql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?logo=apachemaven&logoColor=white)

**400-Day DevOps Learning Roadmap**

**Project:** CloudShop

**Day:** 4 / 20

**Environment:** Local Docker + Spring Boot

</div>

---

# 📖 1. Overview

Day 4 implements the **Authentication Service** for the CloudShop microservices platform.

The service provides the initial foundation for:

- User registration
- Password encryption
- User login
- Authentication
- JWT integration preparation

The Authentication Service communicates with the PostgreSQL database created on Day 3.

---

# 🎯 2. Learning Objectives

By completing Day 4, you will understand:

- Spring Boot layered architecture
- REST Controllers
- Service Layer
- Repository Layer
- JPA Entities
- DTOs
- PostgreSQL persistence
- Password hashing with BCrypt
- Spring Security configuration
- User registration
- User login
- JWT authentication architecture

---

# 🏗️ 3. Authentication Architecture

```text
                       Client
                          │
                          ▼
                 ┌─────────────────┐
                 │ Auth Controller │
                 └────────┬────────┘
                          │
                          ▼
                 ┌─────────────────┐
                 │  Auth Service   │
                 └────────┬────────┘
                          │
                          ▼
                 ┌─────────────────┐
                 │ User Repository │
                 └────────┬────────┘
                          │
                          ▼
                 ┌─────────────────┐
                 │   PostgreSQL    │
                 │    cloudshop    │
                 └─────────────────┘
```

---

# 📁 4. Project Structure

```text
auth-service/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── cloudshop/
│       │           └── auth/
│       │               │
│       │               ├── config/
│       │               │   └── SecurityConfig.java
│       │               │
│       │               ├── controller/
│       │               │   └── AuthController.java
│       │               │
│       │               ├── dto/
│       │               │   ├── RegisterRequest.java
│       │               │   └── LoginRequest.java
│       │               │
│       │               ├── entity/
│       │               │   └── User.java
│       │               │
│       │               ├── repository/
│       │               │   └── UserRepository.java
│       │               │
│       │               ├── service/
│       │               │   └── AuthService.java
│       │               │
│       │               └── AuthServiceApplication.java
│       │
│       └── resources/
│           └── application.properties
│
├── pom.xml
├── mvnw
└── mvnw.cmd
```

---

# 🧩 5. Layered Architecture

The service follows a layered architecture.

```text
Controller
    │
    ▼
Service
    │
    ▼
Repository
    │
    ▼
Database
```

### Controller

Handles HTTP requests.

### Service

Contains business logic.

### Repository

Handles database operations.

### Entity

Represents database data.

### DTO

Represents API request data.

---

# 👤 6. User Entity

The application uses a `User` entity.

```text
User
│
├── id
├── username
├── email
├── password
└── role
```

The entity is mapped to:

```text
users
```

in PostgreSQL.

Example:

```java
@Entity
@Table(name = "users")
public class User {
}
```

---

# 🗃️ 7. User Repository

The repository extends:

```java
JpaRepository<User, Long>
```

This provides standard database operations such as:

```text
save()
findById()
findAll()
delete()
```

Custom methods include:

```text
findByEmail()
existsByEmail()
```

---

# 📝 8. Registration DTO

The registration request contains:

```json
{
  "username": "leenas",
  "email": "leenas@example.com",
  "password": "password123"
}
```

The DTO separates incoming API data from the database entity.

---

# 🔐 9. Password Security

Passwords must not be stored as plain text.

### Incorrect

```text
password123
```

### Correct

```text
Plain Password
       │
       ▼
 BCryptPasswordEncoder
       │
       ▼
Hashed Password
       │
       ▼
PostgreSQL
```

BCrypt is used to hash passwords before persistence.

---

# ⚙️ 10. Spring Security Configuration

A `SecurityConfig` class configures Spring Security.

The authentication endpoints are initially allowed:

```text
/api/auth/**
```

Other application endpoints can later require authentication.

Example configuration:

```java
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

---

# 🌐 11. Authentication APIs

## Register

```http
POST /api/auth/register
```

Example:

```json
{
  "username": "leenas",
  "email": "leenas@example.com",
  "password": "password123"
}
```

---

## Login

```http
POST /api/auth/login
```

Example:

```json
{
  "email": "leenas@example.com",
  "password": "password123"
}
```

---

# 🔄 12. Registration Flow

```text
Client
  │
  │ POST /api/auth/register
  ▼
AuthController
  │
  ▼
AuthService
  │
  ├── Validate user
  │
  ├── Check existing email
  │
  ├── Encode password
  │
  ▼
UserRepository
  │
  ▼
PostgreSQL
```

---

# 🔑 13. Login Flow

```text
Client
  │
  │ POST /api/auth/login
  ▼
AuthController
  │
  ▼
AuthService
  │
  ├── Find user by email
  │
  ├── Verify BCrypt password
  │
  ▼
Authentication Successful
```

JWT will be added as the next authentication layer.

---

# 🔑 14. JWT Architecture

The final authentication flow will be:

```text
User
 │
 ▼
Login
 │
 ▼
Auth Service
 │
 ▼
Validate Credentials
 │
 ▼
Generate JWT
 │
 ▼
Return JWT
```

For protected requests:

```text
Client
 │
 │ Authorization: Bearer <JWT>
 ▼
Protected Service
 │
 ▼
JWT Validation
 │
 ├── Invalid → 401 Unauthorized
 │
 └── Valid → Request Allowed
```

---

# 🗄️ 15. PostgreSQL Integration

The Authentication Service connects to PostgreSQL through:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/cloudshop
spring.datasource.username=postgres
spring.datasource.password=postgres
```

Architecture:

```text
Auth Service
     │
     ▼
JDBC Driver
     │
     ▼
localhost:5432
     │
     ▼
Docker PostgreSQL
     │
     ▼
cloudshop
```

---

# 🧪 16. Registration Testing

Start PostgreSQL:

```bash
docker start cloudshop-postgres
```

Start the application on Windows:

```bash
mvnw.cmd spring-boot:run
```

Send:

```http
POST http://localhost:8080/api/auth/register
```

Body:

```json
{
  "username": "leenas",
  "email": "leenas@example.com",
  "password": "password123"
}
```

---

# 🔍 17. Verify Database

Connect to PostgreSQL:

```bash
docker exec -it cloudshop-postgres psql -U postgres -d cloudshop
```

Check tables:

```sql
\dt
```

Check users:

```sql
SELECT id, username, email, password, role FROM users;
```

The password should be a BCrypt hash, not the original password.

---

# 🧪 18. Login Testing

Send:

```http
POST http://localhost:8080/api/auth/login
```

Body:

```json
{
  "email": "leenas@example.com",
  "password": "password123"
}
```

The service should verify:

```text
Email
  ↓
Find User
  ↓
Stored BCrypt Hash
  ↓
Compare Password
  ↓
Authentication Result
```

---

# ⚠️ 19. Important Security Note

The following credentials are used only for local development:

```text
Username: postgres
Password: postgres
```

They must not be used in production.

Later in the Kubernetes phase, sensitive values will be moved to:

```text
Kubernetes Secrets
```

Production authentication will also use:

- Strong credentials
- Password hashing
- JWT secrets
- Secret management
- Least-privilege access
- HTTPS/TLS

---

# 🧪 20. Testing Checklist

### PostgreSQL

- [x] PostgreSQL container running
- [x] `cloudshop` database available

### Authentication Service

- [x] User entity created
- [x] User repository created
- [x] Registration DTO created
- [x] Login DTO created
- [x] Auth service created
- [x] Auth controller created
- [x] Security configuration created

### Registration

- [x] Registration endpoint created
- [x] User saved to PostgreSQL
- [x] Password encoded

### Login

- [x] Login endpoint created
- [x] Email lookup implemented
- [x] BCrypt password verification implemented

### JWT

- [ ] JWT token generation
- [ ] JWT validation
- [ ] Protected endpoints

---

# 🛠️ 21. Common Issues

## PostgreSQL Not Running

Check:

```bash
docker ps
```

Start it:

```bash
docker start cloudshop-postgres
```

---

## Database Connection Error

Check:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/cloudshop
spring.datasource.username=postgres
spring.datasource.password=postgres
```

---

## Port 8080 Already in Use

Check which application is using port 8080 and stop it, or change:

```properties
server.port=8080
```

to another available port.

---

## Email Already Registered

The service checks:

```java
userRepository.existsByEmail(...)
```

If the email already exists, registration should be rejected.

---

# 🧠 22. Key Concepts Learned

### Entity

Represents persistent database data.

### Repository

Provides database access.

### Service

Contains business logic.

### Controller

Provides REST endpoints.

### DTO

Transfers API request data.

### BCrypt

Securely hashes passwords.

### Spring Security

Provides authentication and authorization functionality.

### JWT

Will provide stateless authentication between the client and services.

---

# 📊 23. Day 4 Architecture

```text
                         Client
                           │
              ┌────────────┴────────────┐
              │                         │
              ▼                         ▼
          Register                    Login
              │                         │
              └────────────┬────────────┘
                           ▼
                    Auth Controller
                           │
                           ▼
                     Auth Service
                           │
                           ▼
                    User Repository
                           │
                           ▼
                       PostgreSQL
```

---

# 📋 24. Day 4 Deliverables

- [x] Authentication microservice structure
- [x] User entity
- [x] User repository
- [x] Registration DTO
- [x] Login DTO
- [x] Authentication service
- [x] Authentication controller
- [x] Spring Security configuration
- [x] BCrypt password encoding
- [x] Registration API
- [x] Login API
- [x] PostgreSQL persistence
- [ ] JWT implementation

---

# 📅 25. Project Progress

| Day | Topic | Status |
|---:|---|:---:|
| 01 | Project Planning & Architecture | ✅ |
| 02 | Project Setup & Spring Boot Microservices | ✅ |
| 03 | PostgreSQL with Docker | ✅ |
| 04 | Authentication Service | ✅ |
| 05 | Product Service | ⬜ |
| 06 | Order Service | ⬜ |
| 07 | Docker Images | ⬜ |
| 08 | Kubernetes Deployments | ⬜ |
| 09 | Kubernetes Services | ⬜ |
| 10 | ConfigMaps & Secrets | ⬜ |
| 11 | Persistent Storage | ⬜ |
| 12 | Ingress | ⬜ |
| 13 | Resource Requests, Limits & HPA | ⬜ |
| 14 | RBAC & Service Accounts | ⬜ |
| 15 | Helm | ⬜ |
| 16 | Prometheus | ⬜ |
| 17 | Grafana | ⬜ |
| 18 | Loki, Promtail & Alertmanager | ⬜ |
| 19 | Production Hardening & Troubleshooting | ⬜ |
| 20 | Final Testing & Documentation | ⬜ |

---

# 🏆 26. Day 4 Outcome

The CloudShop Authentication Service now follows a proper layered architecture:

```text
Client
  ↓
REST Controller
  ↓
Service Layer
  ↓
Repository Layer
  ↓
PostgreSQL
```

The service supports:

```text
User Registration
        ↓
Password Encryption
        ↓
Database Persistence
        ↓
User Login
        ↓
Credential Verification
```

The next authentication enhancement is JWT-based authentication.

---

# 🚀 27. Next Step

## Day 5 — Product Service

We will build the Product microservice.

### Planned Components

```text
Product Entity
       ↓
Product Repository
       ↓
Product Service
       ↓
Product Controller
       ↓
REST API
       ↓
PostgreSQL
```

### Planned APIs

```http
GET    /api/products
GET    /api/products/{id}
POST   /api/products
PUT    /api/products/{id}
DELETE /api/products/{id}
```

---

# 🏁 Project Status

**Project:** CloudShop

**Day:** `4 / 20`

**Stage:** `Authentication Service`

**Status:** ✅ Completed

**Environment:** `Local Docker + Spring Boot`

**Next:** `Day 5 — Product Service`