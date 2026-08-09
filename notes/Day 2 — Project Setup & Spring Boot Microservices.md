# 🚀 CloudShop Kubernetes Capstone Project

<div align="center">

# 📚 Day 2 — Project Setup & Spring Boot Microservices

### Building the Application Foundation

![Java](https://img.shields.io/badge/Java-21-ED8B00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?logo=springboot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-Build%20Tool-C71A36?logo=apachemaven&logoColor=white)
![Git](https://img.shields.io/badge/Git-Version%20Control-F05032?logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-Repository-181717?logo=github&logoColor=white)

**400-Day DevOps Learning Roadmap**

**Project:** CloudShop

**Environment:** Local Development

</div>

---

# 📖 1. Overview

Day 2 focuses on creating the application foundation for the CloudShop Kubernetes Capstone Project.

The platform will use a **microservices architecture** consisting of three independent Spring Boot applications:

- Authentication Service
- Product Service
- Order Service

Each service will be maintained as an independent Maven project so that it can later be:

- Built independently
- Containerized independently
- Deployed independently
- Scaled independently
- Managed independently in Kubernetes

> **Important:** PostgreSQL is not configured during this stage. Database configuration and connectivity are handled in Day 3.

---

# 🎯 2. Learning Objectives

By completing Day 2, you will be able to:

- Create a GitHub repository.
- Clone the repository locally.
- Understand the CloudShop microservices structure.
- Create Spring Boot projects using Spring Initializr.
- Configure Maven projects.
- Select appropriate Spring Boot dependencies.
- Organize multiple microservices in one repository.
- Understand independent service ports.
- Prepare services for Dockerization.

---

# 🏗️ 3. Microservices Architecture

```text
                         CloudShop
                             │
             ┌───────────────┼───────────────┐
             │               │               │
             ▼               ▼               ▼
       Auth Service    Product Service   Order Service
          :8080             :8081            :8082
             │               │               │
             └───────────────┼───────────────┘
                             │
                       PostgreSQL
                    (Configured Day 3)
```

---

# 🔐 4. Authentication Service

Directory:

```text
auth-service/
```

Artifact:

```text
auth-service
```

Group:

```text
com.cloudshop
```

Port:

```text
8080
```

## Responsibilities

- User registration
- User login
- Authentication
- JWT authentication
- User management

## Dependencies

- Spring Web
- Spring Data JPA
- PostgreSQL Driver
- Spring Security
- Validation
- Lombok

> PostgreSQL Driver is added now so the project is prepared for the database integration on Day 3. The datasource is **not configured yet**.

---

# 📦 5. Product Service

Directory:

```text
product-service/
```

Artifact:

```text
product-service
```

Group:

```text
com.cloudshop
```

Port:

```text
8081
```

## Responsibilities

- Create products
- Retrieve products
- Update products
- Delete products
- Search products

## Dependencies

- Spring Web
- Spring Data JPA
- PostgreSQL Driver
- Validation
- Lombok

---

# 🛒 6. Order Service

Directory:

```text
order-service/
```

Artifact:

```text
order-service
```

Group:

```text
com.cloudshop
```

Port:

```text
8082
```

## Responsibilities

- Create orders
- Retrieve orders
- Update order status
- Order history
- Order management

## Dependencies

- Spring Web
- Spring Data JPA
- PostgreSQL Driver
- Validation
- Lombok

---

# 📁 7. Project Structure

After Day 2, the repository should look like:

```text
cloudshop-kubernetes/
│
├── auth-service/
│   ├── src/
│   ├── pom.xml
│   ├── mvnw
│   └── mvnw.cmd
│
├── product-service/
│   ├── src/
│   ├── pom.xml
│   ├── mvnw
│   └── mvnw.cmd
│
├── order-service/
│   ├── src/
│   ├── pom.xml
│   ├── mvnw
│   └── mvnw.cmd
│
├── database/
├── docker/
├── kubernetes/
├── helm/
├── monitoring/
└── docs/
```

---

# 🌐 8. Development Ports

Each service uses a different port during local development.

| Service | Port |
|---|---:|
| Authentication Service | `8080` |
| Product Service | `8081` |
| Order Service | `8082` |

This allows all three applications to run simultaneously on the developer machine.

---

# ⚙️ 9. Maven Project

Each service is an independent Maven project.

The most important file is:

```text
pom.xml
```

The `pom.xml` defines:

- Project metadata
- Java version
- Spring Boot dependencies
- Build configuration
- Plugins

Example structure:

```text
auth-service/
│
├── pom.xml
│
└── src/
    ├── main/
    │   ├── java/
    │   └── resources/
    │
    └── test/
```

---

# 🧩 10. Spring Boot Project Creation

Projects can be created using:

**Spring Initializr**

```text
https://start.spring.io/
```

Recommended configuration:

| Setting | Value |
|---|---|
| Project | Maven |
| Language | Java |
| Spring Boot | 3.x |
| Group | `com.cloudshop` |
| Packaging | Jar |
| Java | 21 |

---

# 📝 11. Dependency Strategy

## Authentication Service

```text
Spring Web
Spring Data JPA
PostgreSQL Driver
Spring Security
Validation
Lombok
```

## Product Service

```text
Spring Web
Spring Data JPA
PostgreSQL Driver
Validation
Lombok
```

## Order Service

```text
Spring Web
Spring Data JPA
PostgreSQL Driver
Validation
Lombok
```

---

# 🔄 12. Correct Implementation Sequence

The project follows this order:

```text
Day 1
Project Architecture
       ↓
Day 2
Spring Boot Projects
       ↓
Day 3
PostgreSQL Docker Container
       ↓
Database Configuration
       ↓
Run Spring Boot Applications
       ↓
Database Connectivity Test
       ↓
Dockerize Services
       ↓
Kubernetes Deployment
```

This sequence prevents datasource errors caused by starting Spring Boot before PostgreSQL configuration exists.

---

# ⚠️ 13. Important: Do Not Start Spring Boot Yet

At the end of Day 2, the Spring Boot projects have been created, but the database has not been configured.

Therefore, **do not run**:

```bash
mvn spring-boot:run
```

yet.

Why?

Spring Boot sees the PostgreSQL dependency and attempts to configure a datasource, but PostgreSQL has not been configured.

The correct approach is to set up PostgreSQL on Day 3 first.

---

# 🔧 14. Git Repository Setup

Initialize Git if it has not already been initialized:

```bash
git init
```

Check status:

```bash
git status
```

Add files:

```bash
git add .
```

Create the initial commit:

```bash
git commit -m "Initial CloudShop microservices setup"
```

Connect the GitHub repository:

```bash
git remote add origin <your-github-repository>
```

Push:

```bash
git push -u origin main
```

---

# 🔍 15. Verification Checklist

Verify that each project contains:

### Auth Service

```text
auth-service/
├── src/
├── pom.xml
├── mvnw
└── mvnw.cmd
```

### Product Service

```text
product-service/
├── src/
├── pom.xml
├── mvnw
└── mvnw.cmd
```

### Order Service

```text
order-service/
├── src/
├── pom.xml
├── mvnw
└── mvnw.cmd
```

---

# 🧠 16. Why Microservices?

A microservices architecture divides a large application into smaller independently managed services.

Instead of:

```text
One Large Application
        │
        ├── Authentication
        ├── Products
        └── Orders
```

we use:

```text
Auth Service

Product Service

Order Service
```

Advantages:

- Independent deployment
- Independent scaling
- Better separation of responsibilities
- Easier maintenance
- Failure isolation
- Independent development

---

# 🐳 17. Preparation for Docker

Each service will eventually receive its own Dockerfile.

Final structure:

```text
auth-service/
├── src/
├── pom.xml
└── Dockerfile

product-service/
├── src/
├── pom.xml
└── Dockerfile

order-service/
├── src/
├── pom.xml
└── Dockerfile
```

The Docker stage will be covered later in the project.

---

# ☸️ 18. Preparation for Kubernetes

Each microservice will eventually become a Kubernetes Deployment.

```text
Auth Service
     │
     ▼
Docker Image
     │
     ▼
Kubernetes Deployment
     │
     ▼
Auth Pods
```

The same architecture will be applied to Product and Order services.

---

# 📋 19. Day 2 Deliverables

- [x] GitHub repository created.
- [x] Repository cloned locally.
- [x] CloudShop project structure created.
- [x] Authentication Service created.
- [x] Product Service created.
- [x] Order Service created.
- [x] Maven configured.
- [x] Required Spring Boot dependencies selected.
- [x] Development ports defined.
- [x] Microservice responsibilities defined.
- [x] Projects prepared for PostgreSQL integration.
- [x] Projects prepared for Dockerization.

---

# 🎓 20. Skills Practiced

### Spring Boot

- Spring Initializr
- Spring Web
- Spring Data JPA
- Spring Security
- Validation
- Lombok

### Maven

- Maven project structure
- `pom.xml`
- Maven Wrapper
- Dependency management

### Git

- Repository initialization
- Commits
- Remote repositories
- GitHub push

### Microservices

- Service separation
- Independent projects
- Service responsibilities
- Port management

---

# 📅 21. Project Progress

| Day | Topic | Status |
|---:|---|:---:|
| 01 | Project Planning & Architecture | ✅ |
| 02 | Project Setup & Spring Boot Microservices | ✅ |
| 03 | PostgreSQL with Docker | ⬜ |
| 04 | Authentication Service | ⬜ |
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

# 🏆 22. Day 2 Outcome

At the end of Day 2, CloudShop has the basic application foundation:

```text
cloudshop-kubernetes/
│
├── auth-service/       ✅
├── product-service/    ✅
├── order-service/      ✅
│
├── database/           🚧
├── docker/             🚧
├── kubernetes/         🚧
├── helm/               🚧
├── monitoring/         🚧
└── docs/               🚧
```

---

# 🚀 23. Next Step

## Day 3 — PostgreSQL with Docker

The next stage will establish the database layer **before starting Spring Boot**.

### Day 3 Tasks

- Verify Docker
- Pull PostgreSQL 16
- Create PostgreSQL container
- Configure PostgreSQL credentials
- Create `cloudshop` database
- Verify PostgreSQL container
- Test database access
- Configure Spring Boot datasource
- Start Spring Boot
- Verify successful PostgreSQL connectivity

### Correct Sequence

```text
Docker
  ↓
PostgreSQL Image
  ↓
PostgreSQL Container
  ↓
cloudshop Database
  ↓
Spring Boot Datasource Configuration
  ↓
Spring Boot Application
  ↓
Successful Database Connection
```

---

# 🏁 Status

**Project:** CloudShop

**Day:** `2 / 20`

**Stage:** `Spring Boot Microservices Setup`

**Status:** ✅ Completed

**Environment:** Local Development

**Next:** `Day 3 — PostgreSQL with Docker`