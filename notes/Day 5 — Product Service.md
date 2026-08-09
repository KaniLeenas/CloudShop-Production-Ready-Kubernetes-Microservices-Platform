# 🚀 CloudShop Kubernetes Capstone Project

<div align="center">

# 📦 Day 5 — Product Service

### Product Management Microservice & RESTful CRUD API

![Java](https://img.shields.io/badge/Java-21-ED8B00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?logo=springboot&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-Database-6DB33F?logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?logo=postgresql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?logo=apachemaven&logoColor=white)

**400-Day DevOps Learning Roadmap**

**Project:** CloudShop

**Day:** 5 / 20

**Environment:** Local Docker + Spring Boot

</div>

---

# 📖 1. Overview

Day 5 focuses on building the **Product Service** of the CloudShop microservices platform.

The Product Service is responsible for managing products and provides a RESTful CRUD API.

The service connects to the PostgreSQL database created during Day 3.

The implementation follows a layered architecture:

```text
Client
   │
   ▼
Product Controller
   │
   ▼
Product Service
   │
   ▼
Product Repository
   │
   ▼
PostgreSQL
```

---

# 🎯 2. Learning Objectives

By completing Day 5, you will understand:

- Spring Boot REST API development
- CRUD operations
- JPA Entity mapping
- Spring Data JPA
- Repository pattern
- Service layer
- Controller layer
- HTTP methods
- JSON request and response handling
- PostgreSQL persistence
- REST API testing

---

# 🏗️ 3. Product Service Architecture

```text
                         Client
                           │
                           ▼
                 ┌───────────────────┐
                 │ Product Controller│
                 └─────────┬─────────┘
                           │
                           ▼
                 ┌───────────────────┐
                 │  Product Service  │
                 └─────────┬─────────┘
                           │
                           ▼
                 ┌───────────────────┐
                 │Product Repository │
                 └─────────┬─────────┘
                           │
                           ▼
                 ┌───────────────────┐
                 │    PostgreSQL     │
                 │     cloudshop     │
                 └───────────────────┘
```

---

# 📁 4. Project Structure

```text
product-service/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── cloudshop/
│       │           └── product/
│       │               │
│       │               ├── controller/
│       │               │   └── ProductController.java
│       │               │
│       │               ├── service/
│       │               │   └── ProductService.java
│       │               │
│       │               ├── repository/
│       │               │   └── ProductRepository.java
│       │               │
│       │               ├── entity/
│       │               │   └── Product.java
│       │               │
│       │               └── ProductServiceApplication.java
│       │
│       └── resources/
│           └── application.properties
│
├── pom.xml
├── mvnw
└── mvnw.cmd
```

---

# 🗄️ 5. Product Entity

The Product Service uses a `Product` entity.

```text
Product
│
├── id
├── name
├── description
├── price
└── quantity
```

The entity is mapped to:

```text
products
```

in PostgreSQL.

Example:

```java
@Entity
@Table(name = "products")
public class Product {
}
```

---

# 📊 6. Product Data Model

| Field | Type | Description |
|---|---|---|
| `id` | Long | Unique product identifier |
| `name` | String | Product name |
| `description` | String | Product description |
| `price` | BigDecimal | Product price |
| `quantity` | Integer | Available quantity |

---

# 🗃️ 7. Product Repository

The repository extends:

```java
JpaRepository<Product, Long>
```

Spring Data JPA automatically provides common database operations:

```text
save()
findAll()
findById()
delete()
deleteById()
existsById()
```

No manual SQL is required for these basic CRUD operations.

---

# ⚙️ 8. Product Service Layer

The service layer contains the business logic.

Main operations:

```text
createProduct()
getAllProducts()
getProductById()
updateProduct()
deleteProduct()
```

Architecture:

```text
ProductController
       │
       ▼
ProductService
       │
       ▼
ProductRepository
```

---

# 🌐 9. REST API

The base URL is:

```text
/api/products
```

The Product Service runs on:

```text
localhost:8081
```

Therefore:

```text
http://localhost:8081/api/products
```

---

# ➕ 10. Create Product

### HTTP Method

```http
POST
```

### Endpoint

```text
/api/products
```

### Full URL

```text
http://localhost:8081/api/products
```

### Request

```json
{
    "name": "Laptop",
    "description": "Developer Laptop",
    "price": 250000.00,
    "quantity": 10
}
```

### Expected Result

A product is created and stored in PostgreSQL.

---

# 📋 11. Get All Products

### HTTP Method

```http
GET
```

### Endpoint

```text
/api/products
```

### Full URL

```text
http://localhost:8081/api/products
```

This returns all available products.

---

# 🔎 12. Get Product by ID

### HTTP Method

```http
GET
```

### Endpoint

```text
/api/products/{id}
```

Example:

```text
http://localhost:8081/api/products/1
```

This retrieves the product with ID `1`.

---

# ✏️ 13. Update Product

### HTTP Method

```http
PUT
```

### Endpoint

```text
/api/products/{id}
```

Example:

```text
http://localhost:8081/api/products/1
```

### Request

```json
{
    "name": "Gaming Laptop",
    "description": "High Performance Laptop",
    "price": 350000.00,
    "quantity": 5
}
```

The existing product is retrieved, updated, and saved again.

---

# 🗑️ 14. Delete Product

### HTTP Method

```http
DELETE
```

### Endpoint

```text
/api/products/{id}
```

Example:

```text
http://localhost:8081/api/products/1
```

Successful deletion returns:

```text
204 No Content
```

---

# 🔄 15. Complete CRUD Flow

```text
                    Product API
                         │
        ┌────────────────┼────────────────┐
        │                │                │
        ▼                ▼                ▼
      CREATE            READ            UPDATE
      POST               GET              PUT
        │                │                │
        └────────────────┼────────────────┘
                         │
                         ▼
                      DELETE
                         │
                         ▼
                    PostgreSQL
```

---

# 🗄️ 16. PostgreSQL Configuration

The Product Service connects to the same PostgreSQL container.

File:

```text
src/main/resources/application.properties
```

Configuration:

```properties
spring.application.name=product-service

server.port=8081

spring.datasource.url=jdbc:postgresql://localhost:5432/cloudshop
spring.datasource.username=postgres
spring.datasource.password=postgres

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

---

# 🐳 17. PostgreSQL Container

The Product Service uses:

```text
cloudshop-postgres
```

Verify it:

```bash
docker ps
```

Start it if necessary:

```bash
docker start cloudshop-postgres
```

---

# 🚀 18. Start Product Service

Navigate to:

```bash
cd product-service
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

Linux/macOS:

```bash
./mvnw spring-boot:run
```

The application should start on:

```text
http://localhost:8081
```

---

# 🧪 19. API Testing

The Product API can be tested using:

- Postman
- curl
- Insomnia
- REST Client

Recommended testing order:

```text
1. POST    /api/products
2. GET     /api/products
3. GET     /api/products/{id}
4. PUT     /api/products/{id}
5. DELETE  /api/products/{id}
```

---

# 🔍 20. Database Verification

Connect to PostgreSQL:

```bash
docker exec -it cloudshop-postgres psql -U postgres -d cloudshop
```

List tables:

```sql
\dt
```

Expected tables include:

```text
users
products
```

Query products:

```sql
SELECT * FROM products;
```

---

# 🔄 21. Product Request Flow

For example:

```http
POST /api/products
```

The request travels through:

```text
HTTP Request
     │
     ▼
ProductController
     │
     ▼
ProductService
     │
     ▼
ProductRepository
     │
     ▼
Hibernate / JPA
     │
     ▼
PostgreSQL
```

---

# 🧠 22. Layer Responsibilities

## Controller

Handles:

- HTTP requests
- HTTP responses
- URL mappings

```text
/api/products
```

---

## Service

Handles:

- Business logic
- Product operations
- Validation logic

---

## Repository

Handles:

- Database access
- CRUD operations
- JPA queries

---

## Entity

Represents:

```text
Java Object
     ↕
Database Row
```

---

# ⚠️ 23. Common Problems

## PostgreSQL Not Running

Check:

```bash
docker ps
```

Start:

```bash
docker start cloudshop-postgres
```

---

## Port 8081 Already Used

Check whether another application is using port `8081`.

Change:

```properties
server.port=8081
```

to another available port if necessary.

---

## Database Connection Failed

Check:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/cloudshop
spring.datasource.username=postgres
spring.datasource.password=postgres
```

Also verify:

```bash
docker ps
```

---

## Product Not Found

If requesting:

```text
/api/products/999
```

and the product does not exist, the service will return:

```text
Product not found
```

---

# 🧪 24. Testing Checklist

### Application

- [x] Product Service created
- [x] Port `8081` configured
- [x] PostgreSQL datasource configured

### Entity

- [x] Product entity created
- [x] Product table mapped

### Repository

- [x] Product repository created
- [x] JPA CRUD operations available

### Service

- [x] Create product
- [x] Get all products
- [x] Get product by ID
- [x] Update product
- [x] Delete product

### Controller

- [x] POST endpoint
- [x] GET all endpoint
- [x] GET by ID endpoint
- [x] PUT endpoint
- [x] DELETE endpoint

### Database

- [x] PostgreSQL connection verified
- [x] Products persisted
- [x] Products retrieved from PostgreSQL

---

# 📋 25. Day 5 Deliverables

- [x] Product Service created.
- [x] Product Entity created.
- [x] Product Repository created.
- [x] Product Service layer created.
- [x] Product Controller created.
- [x] PostgreSQL integration configured.
- [x] Create Product API implemented.
- [x] Get Products API implemented.
- [x] Get Product by ID API implemented.
- [x] Update Product API implemented.
- [x] Delete Product API implemented.
- [x] CRUD operations tested.
- [x] PostgreSQL persistence verified.

---

# 🎓 26. Skills Practiced

### Spring Boot

- REST Controllers
- Dependency Injection
- Service Layer
- Application Configuration

### Spring Data JPA

- Entities
- Repositories
- CRUD operations
- Database mapping

### PostgreSQL

- Table persistence
- SQL queries
- Database verification

### REST APIs

- GET
- POST
- PUT
- DELETE
- JSON

### DevOps

- Microservice separation
- Database connectivity
- Local service testing
- Troubleshooting

---

# 📊 27. Current Architecture

After Day 5:

```text
                         CloudShop
                             │
             ┌───────────────┼────────────────┐
             │               │                │
             ▼               ▼                ▼
       Auth Service    Product Service    Order Service
          :8080             :8081             :8082
             │                │
             │                │
             └────────────────┘
                      │
                      ▼
              PostgreSQL Docker
                      │
                      ▼
                cloudshop DB
```

Current database tables:

```text
cloudshop
│
├── users
└── products
```

---

# 📅 28. Project Progress

| Day | Topic | Status |
|---:|---|:---:|
| 01 | Project Planning & Architecture | ✅ |
| 02 | Project Setup & Spring Boot Microservices | ✅ |
| 03 | PostgreSQL with Docker | ✅ |
| 04 | Authentication Service | ✅ |
| 05 | Product Service | ✅ |
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

# 🏆 29. Day 5 Outcome

The CloudShop platform now contains two functional application layers:

```text
                CloudShop
                   │
       ┌───────────┴───────────┐
       │                       │
       ▼                       ▼
 Authentication          Product Management
    Service                   Service
       │                       │
       └───────────┬───────────┘
                   │
                   ▼
              PostgreSQL
                   │
          ┌────────┴────────┐
          │                 │
          ▼                 ▼
        users            products
```

The Product Service provides complete basic CRUD functionality and is ready for later containerization.

---

# 🚀 30. Next Step

## Day 6 — Order Service

The next microservice will implement order management.

### Planned Components

```text
Order Entity
      ↓
Order Repository
      ↓
Order Service
      ↓
Order Controller
      ↓
REST API
      ↓
PostgreSQL
```

### Planned APIs

```http
POST   /api/orders
GET    /api/orders
GET    /api/orders/{id}
PUT    /api/orders/{id}
DELETE /api/orders/{id}
```

After Day 6, all three core CloudShop microservices will be implemented before we move into the **Docker containerization phase**.

---

# 🏁 Status

**Project:** CloudShop

**Day:** `5 / 20`

**Stage:** `Product Service`

**Status:** ✅ Completed

**Environment:** `Local Docker + Spring Boot`

**Next:** `Day 6 — Order Service`