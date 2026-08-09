# 🚀 CloudShop Kubernetes Capstone Project

<div align="center">

# 📚 Day 3 — PostgreSQL with Docker

### Database Infrastructure & Spring Boot Connectivity

![Docker](https://img.shields.io/badge/Docker-Containerization-2496ED?logo=docker&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?logo=postgresql&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?logo=springboot&logoColor=white)
![Java](https://img.shields.io/badge/Java-21-ED8B00?logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-Build%20Tool-C71A36?logo=apachemaven&logoColor=white)

**400-Day DevOps Learning Roadmap**

**Project:** CloudShop

**Environment:** Local Docker + Spring Boot

</div>

---

# 📖 1. Overview

Day 3 establishes the **database layer** of the CloudShop microservices platform.

PostgreSQL is deployed as a Docker container rather than being installed directly on the host machine.

The database is then configured as the datasource for the `auth-service` Spring Boot application.

The correct implementation sequence is:

```text
Docker
   ↓
PostgreSQL Image
   ↓
PostgreSQL Container
   ↓
cloudshop Database
   ↓
Spring Boot Datasource
   ↓
Database Connection
   ↓
Application Startup
```

This sequence prevents Spring Boot from attempting database authentication before PostgreSQL and its credentials have been configured.

---

# 🎯 2. Learning Objectives

By completing Day 3, you will be able to:

- Run PostgreSQL using Docker.
- Understand PostgreSQL Docker environment variables.
- Map container ports to the host machine.
- Verify a running PostgreSQL container.
- Inspect PostgreSQL logs.
- Access PostgreSQL using `psql`.
- Create a database through container initialization.
- Configure a Spring Boot PostgreSQL datasource.
- Connect Spring Boot to PostgreSQL running in Docker.
- Troubleshoot basic PostgreSQL connectivity issues.

---

# 🏗️ 3. Architecture

```text
                    Developer Machine
                           │
                           ▼
                    Docker Desktop
                           │
                           ▼
              ┌────────────────────────┐
              │  PostgreSQL Container  │
              │  cloudshop-postgres    │
              │                        │
              │  PostgreSQL 16         │
              └───────────┬────────────┘
                          │
                    Port 5432
                          │
                          ▼
                  cloudshop Database
                          ▲
                          │
                 JDBC Connection
                          │
                          ▼
                ┌──────────────────┐
                │   Auth Service   │
                │   Spring Boot    │
                │      :8080       │
                └──────────────────┘
```

---

# 🐳 4. Docker PostgreSQL Setup

## 4.1 Verify Docker

```bash
docker --version
```

Verify that the Docker daemon is running:

```bash
docker ps
```

An empty container list is acceptable.

---

# 📦 5. Pull PostgreSQL Image

Download PostgreSQL 16:

```bash
docker pull postgres:16
```

Verify the image:

```bash
docker images
```

Expected image:

```text
REPOSITORY   TAG
postgres     16
```

---

# 🗄️ 6. Create PostgreSQL Container

## Windows PowerShell

```powershell
docker run -d `
  --name cloudshop-postgres `
  -e POSTGRES_USER=postgres `
  -e POSTGRES_PASSWORD=postgres `
  -e POSTGRES_DB=cloudshop `
  -p 5432:5432 `
  postgres:16
```

## Git Bash / Linux / macOS

```bash
docker run -d \
  --name cloudshop-postgres \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -e POSTGRES_DB=cloudshop \
  -p 5432:5432 \
  postgres:16
```

---

# ⚙️ 7. Docker Configuration Explained

| Configuration | Value | Purpose |
|---|---|---|
| `-d` | Detached mode | Runs container in background |
| `--name` | `cloudshop-postgres` | Container name |
| `POSTGRES_USER` | `postgres` | PostgreSQL username |
| `POSTGRES_PASSWORD` | `postgres` | PostgreSQL password |
| `POSTGRES_DB` | `cloudshop` | Initial database |
| `-p` | `5432:5432` | Host-to-container port mapping |
| Image | `postgres:16` | PostgreSQL 16 image |

---

# 🔌 8. PostgreSQL Port Mapping

```text
Host Machine
     │
     │ localhost:5432
     ▼
Docker Container
     │
     │ :5432
     ▼
PostgreSQL
```

The mapping:

```text
5432:5432
```

means:

```text
Host Port : Container Port
```

Therefore Spring Boot can connect using:

```text
localhost:5432
```

---

# 🔍 9. Verify PostgreSQL Container

Run:

```bash
docker ps
```

Expected:

```text
cloudshop-postgres
```

with a status similar to:

```text
Up ...
```

---

# 📋 10. Check PostgreSQL Logs

Run:

```bash
docker logs cloudshop-postgres
```

Look for:

```text
database system is ready to accept connections
```

This confirms that PostgreSQL has successfully started.

---

# 🖥️ 11. Access PostgreSQL

Open the PostgreSQL command-line client inside the container:

```bash
docker exec -it cloudshop-postgres psql -U postgres
```

You should see:

```text
postgres=#
```

---

# 🗂️ 12. Verify Databases

Inside `psql`, run:

```sql
\l
```

The database list should include:

```text
cloudshop
postgres
template0
template1
```

The project database is:

```text
cloudshop
```

Exit PostgreSQL:

```sql
\q
```

---

# 🔗 13. Configure Spring Boot

After PostgreSQL is running, configure the `auth-service`.

File:

```text
auth-service/src/main/resources/application.properties
```

Add:

```properties
spring.application.name=auth-service

server.port=8080

spring.datasource.url=jdbc:postgresql://localhost:5432/cloudshop
spring.datasource.username=postgres
spring.datasource.password=postgres

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

---

# 🔍 14. Datasource Configuration Explained

### Database URL

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/cloudshop
```

Breakdown:

```text
jdbc
 │
 └── PostgreSQL
       │
       ├── Host: localhost
       ├── Port: 5432
       └── Database: cloudshop
```

### Username

```properties
spring.datasource.username=postgres
```

### Password

```properties
spring.datasource.password=postgres
```

These values must match the credentials used when creating the PostgreSQL container.

---

# 🚀 15. Start Auth Service

Navigate into the service:

```bash
cd auth-service
```

### Windows

```bash
mvnw.cmd spring-boot:run
```

### Linux / macOS

```bash
./mvnw spring-boot:run
```

Or if Maven is installed globally:

```bash
mvn spring-boot:run
```

---

# 🔄 16. Complete Connection Flow

```text
Spring Boot
     │
     ▼
application.properties
     │
     ▼
JDBC PostgreSQL Driver
     │
     ▼
localhost:5432
     │
     ▼
Docker Port Mapping
     │
     ▼
cloudshop-postgres
     │
     ▼
PostgreSQL 16
     │
     ▼
cloudshop Database
```

---

# ✅ 17. Successful Startup

A successful application startup should contain messages indicating that:

- PostgreSQL connectivity was established.
- Hibernate initialized.
- Tomcat started.
- Spring Boot application started.

Example:

```text
Tomcat started on port 8080
Started AuthServiceApplication
```

---

# ❌ 18. Why We Did Not Run Spring Boot Earlier

Before Day 3, the project only contained the PostgreSQL dependency.

The database itself did not exist.

Incorrect sequence:

```text
Spring Boot
     ↓
PostgreSQL Driver
     ↓
❌ No Database
```

Correct sequence:

```text
PostgreSQL Docker Container
     ↓
cloudshop Database
     ↓
Datasource Configuration
     ↓
Spring Boot
     ↓
✅ Database Connection
```

---

# ⚠️ 19. Common Problems

## Container Name Already Exists

Error:

```text
Conflict. The container name is already in use
```

Check:

```bash
docker ps -a
```

If the old container is not needed:

```bash
docker rm -f cloudshop-postgres
```

Then recreate it.

---

## PostgreSQL Container Is Not Running

Check:

```bash
docker ps -a
```

View logs:

```bash
docker logs cloudshop-postgres
```

---

## Port 5432 Already in Use

Check:

```bash
docker ps
```

If another PostgreSQL service is using port `5432`, either stop it or use another host port.

Example:

```text
5433:5432
```

Then the Spring Boot URL becomes:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5433/cloudshop
```

---

## Authentication Failed

If you see:

```text
password authentication failed
```

verify that:

```properties
spring.datasource.username=postgres
spring.datasource.password=postgres
```

matches the PostgreSQL container configuration.

---

# 🔐 20. Development Security Note

For this local learning project, the database credentials are:

```text
Username: postgres
Password: postgres
```

These are intentionally simple for local development.

**Do not use these credentials in a real production environment.**

Later, Kubernetes Secrets and stronger credential management will be introduced.

---

# 🧪 21. Verification Checklist

### Docker

```bash
docker --version
```

- [x] Docker available

### PostgreSQL Image

```bash
docker images
```

- [x] `postgres:16` available

### PostgreSQL Container

```bash
docker ps
```

- [x] `cloudshop-postgres` running

### PostgreSQL Logs

```bash
docker logs cloudshop-postgres
```

- [x] Database ready

### Database

```bash
docker exec -it cloudshop-postgres psql -U postgres
```

```sql
\l
```

- [x] `cloudshop` database exists

### Spring Boot

```bash
mvnw.cmd spring-boot:run
```

- [x] Auth service starts
- [x] PostgreSQL connection successful

---

# 📁 22. Project Structure After Day 3

```text
cloudshop-kubernetes/
│
├── auth-service/
│   ├── src/
│   │   └── main/
│   │       └── resources/
│   │           └── application.properties
│   ├── pom.xml
│   ├── mvnw
│   └── mvnw.cmd
│
├── product-service/
│   ├── src/
│   └── pom.xml
│
├── order-service/
│   ├── src/
│   └── pom.xml
│
├── database/
├── docker/
├── kubernetes/
├── helm/
├── monitoring/
└── docs/
```

---

# 🎓 23. Skills Practiced

## Docker

- Pulling images
- Creating containers
- Environment variables
- Port mapping
- Container inspection
- Container logs
- Executing commands inside containers

## PostgreSQL

- PostgreSQL 16
- Database initialization
- `psql`
- Database verification
- PostgreSQL authentication

## Spring Boot

- Datasource configuration
- JDBC connection
- PostgreSQL integration
- Hibernate configuration

## DevOps

- Infrastructure preparation
- Environment configuration
- Service connectivity
- Troubleshooting

---

# 📋 24. Day 3 Deliverables

- [x] Docker verified.
- [x] PostgreSQL 16 image downloaded.
- [x] PostgreSQL container created.
- [x] PostgreSQL credentials configured.
- [x] `cloudshop` database created.
- [x] PostgreSQL logs verified.
- [x] Database accessed using `psql`.
- [x] `auth-service` datasource configured.
- [x] Spring Boot PostgreSQL connectivity established.

---

# 📅 25. Project Progress

| Day | Topic | Status |
|---:|---|:---:|
| 01 | Project Planning & Architecture | ✅ |
| 02 | Project Setup & Spring Boot Microservices | ✅ |
| 03 | PostgreSQL with Docker | ✅ |
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

# 🏆 26. Day 3 Outcome

The CloudShop project now has its first infrastructure component:

```text
                 CloudShop
                    │
                    ▼
          ┌───────────────────┐
          │ Docker            │
          │                   │
          │ PostgreSQL 16     │
          │                   │
          │ cloudshop DB      │
          └─────────┬─────────┘
                    │
                    ▼
             Auth Service
             Spring Boot
```

The database foundation is now ready for application development.

---

# 🚀 27. Next Step

## Day 4 — Authentication Service

We will build the actual authentication functionality.

### Topics

- User Entity
- User Repository
- User registration
- Password handling
- Authentication API
- Login API
- Validation
- Database persistence
- Testing APIs

The service will evolve from:

```text
Spring Boot
     ↓
PostgreSQL Connection
```

into:

```text
Client
   ↓
Auth REST API
   ↓
Service Layer
   ↓
Repository
   ↓
PostgreSQL
```

---

# 🏁 Status

**Project:** CloudShop

**Day:** `3 / 20`

**Stage:** `PostgreSQL Database Infrastructure`

**Status:** ✅ Completed

**Environment:** `Local Docker + Spring Boot`

**Next:** `Day 4 — Authentication Service`