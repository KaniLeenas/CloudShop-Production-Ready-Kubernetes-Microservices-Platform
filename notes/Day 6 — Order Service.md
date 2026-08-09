# 🚀 CloudShop Kubernetes Capstone Project

<div align="center">

# 🛒 Day 6 — Order Service

### Production-Style Order Management Microservice

![Java](https://img.shields.io/badge/Java-21-ED8B00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?logo=springboot&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-3.x-6DB33F?logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Ready-2496ED?logo=docker&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?logo=apachemaven&logoColor=white)

**400-Day DevOps Learning Roadmap**

**Project:** CloudShop  
**Day:** `06 / 20`  
**Stage:** Application Development  
**Environment:** Local Docker + Spring Boot

</div>

---

## 📌 1. Overview

Day 6 completes the third core microservice of the CloudShop platform: the **Order Service**.

The Order Service is responsible for creating, retrieving, updating, and deleting customer orders.

It follows a layered microservice architecture:

```text
                    Client
                       │
                       ▼
              ┌─────────────────┐
              │ Order Controller│
              └────────┬────────┘
                       │
                       ▼
              ┌─────────────────┐
              │  Order Service  │
              └────────┬────────┘
                       │
                       ▼
              ┌─────────────────┐
              │Order Repository │
              └────────┬────────┘
                       │
                       ▼
              ┌─────────────────┐
              │   PostgreSQL    │
              │    cloudshop    │
              └─────────────────┘
```

---

# 🎯 2. Objectives

By the end of Day 6, the following capabilities are implemented:

- Order microservice structure
- JPA entity mapping
- Repository-based persistence
- Service-layer business logic
- REST API endpoints
- CRUD operations
- Order status management
- PostgreSQL integration
- Local API testing
- Independent microservice execution

---

# 🏗️ 3. CloudShop Application Architecture

After Day 6, the application contains three core services:

```text
                         CloudShop
                             │
          ┌──────────────────┼──────────────────┐
          │                  │                  │
          ▼                  ▼                  ▼
    Auth Service       Product Service      Order Service
       :8080                :8081               :8082
          │                  │                  │
          └──────────────────┼──────────────────┘
                             │
                             ▼
                     PostgreSQL 16
                             │
                ┌────────────┼────────────┐
                ▼            ▼            ▼
              users       products       orders
```

### Service Ports

| Service | Port | Responsibility |
|---|---:|---|
| Auth Service | `8080` | Authentication & users |
| Product Service | `8081` | Product management |
| Order Service | `8082` | Order management |
| PostgreSQL | `5432` | Persistent database |

---

# 📁 4. Project Structure

```text
order-service/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── cloudshop/
│       │           └── order/
│       │               │
│       │               ├── controller/
│       │               │   └── OrderController.java
│       │               │
│       │               ├── service/
│       │               │   └── OrderService.java
│       │               │
│       │               ├── repository/
│       │               │   └── OrderRepository.java
│       │               │
│       │               ├── entity/
│       │               │   └── Order.java
│       │               │
│       │               └── OrderServiceApplication.java
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

The service follows separation of concerns.

```text
┌─────────────────────────────┐
│       REST Controller       │
│       HTTP / API Layer      │
└──────────────┬──────────────┘
               │
               ▼
┌─────────────────────────────┐
│        Service Layer        │
│       Business Logic        │
└──────────────┬──────────────┘
               │
               ▼
┌─────────────────────────────┐
│       Repository Layer      │
│       Data Access           │
└──────────────┬──────────────┘
               │
               ▼
┌─────────────────────────────┐
│        PostgreSQL           │
│        Persistence          │
└─────────────────────────────┘
```

### Responsibilities

| Layer | Responsibility |
|---|---|
| Controller | HTTP requests/responses |
| Service | Business logic |
| Repository | Database operations |
| Entity | Persistent data model |

---

# 🗄️ 6. Order Domain Model

The Order entity contains:

```text
Order
│
├── id
├── userId
├── productId
├── quantity
├── totalPrice
└── status
```

### Data Dictionary

| Field | Type | Description |
|---|---|---|
| `id` | `Long` | Unique order identifier |
| `userId` | `Long` | ID of the customer |
| `productId` | `Long` | ID of the ordered product |
| `quantity` | `Integer` | Number of units |
| `totalPrice` | `BigDecimal` | Total order amount |
| `status` | `String` | Current order state |

---

# 🔄 7. Order Lifecycle

New orders receive the default status:

```text
PENDING
```

The basic lifecycle is:

```text
PENDING
   │
   ▼
CONFIRMED
   │
   ▼
COMPLETED
```

The current implementation keeps the status model intentionally simple for the application-development phase.

---

# 🗃️ 8. Repository Layer

`OrderRepository` extends:

```java
JpaRepository<Order, Long>
```

This provides standard persistence operations:

```text
save()
findAll()
findById()
delete()
deleteById()
existsById()
```

This allows the application to interact with PostgreSQL through Spring Data JPA without manually implementing basic SQL operations.

---

# ⚙️ 9. Service Layer

`OrderService` implements the application operations:

```text
createOrder()
getAllOrders()
getOrderById()
updateOrder()
deleteOrder()
```

### Create Order

If no status is provided:

```text
status = PENDING
```

### Retrieve Order

The service searches for an order by its ID.

If the order does not exist:

```text
Order not found
```

### Update Order

The existing order is retrieved, modified, and persisted.

### Delete Order

The specified order is retrieved and removed from the database.

---

# 🌐 10. REST API Specification

Base URL:

```text
http://localhost:8082/api/orders
```

| Method | Endpoint | Purpose |
|---|---|---|
| `POST` | `/api/orders` | Create order |
| `GET` | `/api/orders` | Get all orders |
| `GET` | `/api/orders/{id}` | Get order |
| `PUT` | `/api/orders/{id}` | Update order |
| `DELETE` | `/api/orders/{id}` | Delete order |

---

# ➕ 11. Create Order

### Request

```http
POST /api/orders
```

### Example

```json
{
    "userId": 1,
    "productId": 1,
    "quantity": 2,
    "totalPrice": 500000.00
}
```

### Processing

```text
HTTP Request
     │
     ▼
OrderController
     │
     ▼
OrderService
     │
     ├── Set PENDING status
     │
     ▼
OrderRepository
     │
     ▼
PostgreSQL
```

---

# 📋 12. Get All Orders

```http
GET /api/orders
```

Returns all persisted orders.

Example:

```json
[
    {
        "id": 1,
        "userId": 1,
        "productId": 1,
        "quantity": 2,
        "totalPrice": 500000.00,
        "status": "PENDING"
    }
]
```

---

# 🔎 13. Get Order by ID

```http
GET /api/orders/1
```

The service retrieves order `1`.

If it exists, the order is returned.

If it does not exist, the service reports:

```text
Order not found
```

---

# ✏️ 14. Update Order

```http
PUT /api/orders/1
```

Example:

```json
{
    "userId": 1,
    "productId": 1,
    "quantity": 2,
    "totalPrice": 500000.00,
    "status": "CONFIRMED"
}
```

The existing record is updated and persisted.

---

# 🗑️ 15. Delete Order

```http
DELETE /api/orders/1
```

Successful deletion:

```text
HTTP 204 No Content
```

---

# 🗄️ 16. Database Configuration

The Order Service connects to the PostgreSQL container created during Day 3.

`application.properties`:

```properties
spring.application.name=order-service

server.port=8082

spring.datasource.url=jdbc:postgresql://localhost:5432/cloudshop
spring.datasource.username=postgres
spring.datasource.password=postgres

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

Connection path:

```text
Order Service
      │
      ▼
localhost:5432
      │
      ▼
cloudshop-postgres
      │
      ▼
PostgreSQL 16
      │
      ▼
cloudshop
```

---

# 🐳 17. PostgreSQL Verification

Check the container:

```bash
docker ps
```

Expected container:

```text
cloudshop-postgres
```

If stopped:

```bash
docker start cloudshop-postgres
```

Connect to the database:

```bash
docker exec -it cloudshop-postgres psql -U postgres -d cloudshop
```

List tables:

```sql
\dt
```

Expected application tables:

```text
users
products
orders
```

Query orders:

```sql
SELECT * FROM orders;
```

---

# 🚀 18. Running the Service

Navigate to:

```bash
cd order-service
```

### Windows

```bash
mvnw.cmd spring-boot:run
```

### Linux/macOS

```bash
./mvnw spring-boot:run
```

Expected application state:

```text
Order Service
     │
     ▼
Port 8082
     │
     ▼
Running
```

---

# 🧪 19. Testing Strategy

Test the API in this sequence:

```text
1. Create Order
        ↓
2. Get All Orders
        ↓
3. Get Order by ID
        ↓
4. Update Order
        ↓
5. Get Updated Order
        ↓
6. Delete Order
        ↓
7. Verify Database
```

Recommended tools:

- Postman
- curl
- Insomnia
- VS Code REST Client

---

# 🔍 20. Database Validation

After creating an order:

```bash
docker exec -it cloudshop-postgres psql -U postgres -d cloudshop
```

Run:

```sql
SELECT * FROM orders;
```

This validates the complete persistence path:

```text
REST API
   ↓
Controller
   ↓
Service
   ↓
Repository
   ↓
Hibernate
   ↓
PostgreSQL
```

---

# 🔗 21. Current Service Boundaries

The current Order Service stores:

```text
userId
productId
```

as identifiers.

Conceptually:

```text
Order
 ├── userId ──────► Auth/User Domain
 │
 └── productId ───► Product Domain
```

At this stage, the Order Service does **not** directly validate those IDs through other services.

That service-to-service communication will be introduced later.

---

# ⚠️ 22. Current Scope

The current implementation intentionally does not include:

- JWT authorization
- User-service communication
- Product-service communication
- Inventory reservation
- Payment processing
- Distributed transactions
- Event-driven order processing
- Message queues

These are advanced capabilities that can be introduced after the foundational microservices are containerized and deployed to Kubernetes.

---

# 🔐 23. Security Considerations

The current implementation is for local development.

Production systems should additionally implement:

- JWT authentication
- Authorization
- Kubernetes Secrets
- Strong database credentials
- Input validation
- Centralized exception handling
- HTTPS/TLS
- Least-privilege database access
- Audit logging

These concerns will be addressed progressively during the Kubernetes and security stages.

---

# 🧪 24. Verification Checklist

## Infrastructure

- [x] Docker running
- [x] PostgreSQL 16 running
- [x] `cloudshop` database available

## Application

- [x] Order Service created
- [x] Port `8082` configured
- [x] PostgreSQL datasource configured

## Domain

- [x] Order entity created
- [x] Order table mapped

## Persistence

- [x] Order repository created
- [x] JPA persistence configured

## Business Logic

- [x] Order creation
- [x] Default `PENDING` status
- [x] Order retrieval
- [x] Order update
- [x] Order deletion

## API

- [x] POST endpoint
- [x] GET endpoint
- [x] GET by ID endpoint
- [x] PUT endpoint
- [x] DELETE endpoint

## Database

- [x] Orders persisted
- [x] Orders retrieved
- [x] Orders verified using PostgreSQL

---

# 📋 25. Day 6 Deliverables

| Deliverable | Status |
|---|:---:|
| Order Service project | ✅ |
| Order Entity | ✅ |
| Order Repository | ✅ |
| Order Service layer | ✅ |
| Order Controller | ✅ |
| PostgreSQL integration | ✅ |
| Create Order API | ✅ |
| Get Orders API | ✅ |
| Get Order by ID API | ✅ |
| Update Order API | ✅ |
| Delete Order API | ✅ |
| Order status handling | ✅ |
| Database verification | ✅ |

---

# 🎓 26. Technical Skills

### Spring Boot

- REST API development
- Dependency Injection
- Layered architecture
- Application configuration

### Spring Data JPA

- Entity mapping
- Repository abstraction
- CRUD persistence
- Hibernate integration

### PostgreSQL

- Relational persistence
- SQL verification
- Database connectivity

### REST

- HTTP methods
- Resource-oriented endpoints
- JSON payloads
- HTTP status codes

### Microservices

- Service boundaries
- Independent ports
- Independent application modules
- Shared infrastructure

### DevOps

- Local containerized infrastructure
- Service startup
- Application/database connectivity
- Troubleshooting

---

# 📊 27. CloudShop Database

Current database structure:

```text
cloudshop
│
├── users
│
├── products
│
└── orders
```

The three tables represent the initial application domains:

```text
Users
  │
  ├───────────────┐
  │               │
  ▼               ▼
Products        Orders
```

---

# 🏆 28. Application Development Milestone

The first six days complete the initial application-development stage:

```text
Day 1
Architecture
   ↓
Day 2
Spring Boot Setup
   ↓
Day 3
PostgreSQL + Docker
   ↓
Day 4
Authentication Service
   ↓
Day 5
Product Service
   ↓
Day 6
Order Service
```

### Current Status

```text
┌──────────────────────────────────────┐
│       APPLICATION FOUNDATION         │
│                                      │
│  Auth Service      ✅                │
│  Product Service   ✅                │
│  Order Service     ✅                │
│  PostgreSQL        ✅                │
│                                      │
│  Status: COMPLETE                   │
└──────────────────────────────────────┘
```

---

# 📅 29. Project Progress

| Day | Topic | Status |
|---:|---|:---:|
| 01 | Project Planning & Architecture | ✅ |
| 02 | Spring Boot Microservices Setup | ✅ |
| 03 | PostgreSQL with Docker | ✅ |
| 04 | Authentication Service | ✅ |
| 05 | Product Service | ✅ |
| 06 | Order Service | ✅ |
| 07 | Dockerize Microservices | ⬜ |
| 08 | Kubernetes Deployments | ⬜ |
| 09 | Kubernetes Services | ⬜ |
| 10 | ConfigMaps & Secrets | ⬜ |
| 11 | Persistent Storage | ⬜ |
| 12 | Ingress | ⬜ |
| 13 | Resource Requests, Limits & HPA | ⬜ |
| 14 | RBAC & Kubernetes Security | ⬜ |
| 15 | Helm | ⬜ |
| 16 | Prometheus Monitoring | ⬜ |
| 17 | Grafana Dashboards | ⬜ |
| 18 | Logging & Alerting | ⬜ |
| 19 | Production Hardening & Troubleshooting | ⬜ |
| 20 | Final Integration & Documentation | ⬜ |

---

# 🚀 30. Next Phase — Day 7

## Dockerize the CloudShop Microservices

The application layer is now ready for containerization.

We will transform:

```text
Spring Boot Application
        │
        ▼
       JAR
        │
        ▼
   Docker Image
        │
        ▼
Docker Container
```

The following Dockerfiles will be created:

```text
auth-service/
└── Dockerfile

product-service/
└── Dockerfile

order-service/
└── Dockerfile
```

We will then build and run all three services as Docker containers.

---

# 🏁 Final Status

**Project:** CloudShop Kubernetes Capstone

**Day:** `06 / 20`

**Stage:** `Order Service`

**Status:** ✅ **COMPLETED**

**Application Development:** ✅ **COMPLETE**

**Infrastructure:** Docker PostgreSQL

**Next:** `Day 7 — Dockerize Microservices`

---