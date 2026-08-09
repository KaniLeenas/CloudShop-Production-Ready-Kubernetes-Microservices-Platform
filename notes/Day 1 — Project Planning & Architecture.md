# 🚀 CloudShop Kubernetes Capstone Project

<div align="center">

# 📚 Day 1 — Project Planning & Architecture

### Production-Style Microservices Platform on Local Kubernetes

![Docker](https://img.shields.io/badge/Docker-Containerization-2496ED?logo=docker&logoColor=white)
![Kubernetes](https://img.shields.io/badge/Kubernetes-Orchestration-326CE5?logo=kubernetes&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-Microservices-6DB33F?logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-4169E1?logo=postgresql&logoColor=white)
![Minikube](https://img.shields.io/badge/Minikube-Local%20Cluster-94399E?logo=kubernetes&logoColor=white)
![Helm](https://img.shields.io/badge/Helm-Package%20Manager-0F1689?logo=helm&logoColor=white)

**400-Day DevOps Learning Roadmap**

</div>

---

## 📖 Project Overview

**CloudShop** is a production-style e-commerce microservices platform designed to consolidate the Docker and Kubernetes concepts learned throughout the DevOps learning roadmap.

The entire platform will initially run on a **local Kubernetes environment using Minikube**, with Docker providing containerization.

The project will progressively introduce application deployment, networking, configuration management, persistent storage, security, autoscaling, Helm, monitoring, logging, and alerting.

---

## 🎯 Project Objectives

The project is designed to demonstrate practical DevOps and Kubernetes skills through a complete application lifecycle.

### Core Objectives

- Build a microservices-based application.
- Containerize services with Docker.
- Deploy applications to Kubernetes.
- Configure Kubernetes networking.
- Manage application configuration.
- Secure sensitive configuration.
- Implement persistent storage.
- Configure Ingress.
- Configure resource requests and limits.
- Implement Horizontal Pod Autoscaling.
- Configure Kubernetes RBAC.
- Package applications with Helm.
- Implement monitoring and observability.
- Centralize application logs.
- Configure production-style alerts.

---

# 🏗️ High-Level Architecture

```text
                         ┌──────────────┐
                         │    Client    │
                         │   Browser    │
                         └──────┬───────┘
                                │
                                ▼
                         ┌──────────────┐
                         │    Ingress   │
                         │    NGINX     │
                         └──────┬───────┘
                                │
                ┌───────────────┼───────────────┐
                │               │               │
                ▼               ▼               ▼
        ┌──────────────┐ ┌──────────────┐ ┌──────────────┐
        │ Auth Service │ │Product Service│ │ Order Service│
        └──────┬───────┘ └──────┬───────┘ └──────┬───────┘
               │                │                │
               └────────────────┼────────────────┘
                                │
                                ▼
                       ┌─────────────────┐
                       │   PostgreSQL    │
                       │    Database     │
                       └────────┬────────┘
                                │
                                ▼
                       ┌─────────────────┐
                       │ Persistent      │
                       │ Storage         │
                       └─────────────────┘


              ─────── Observability Layer ───────

        ┌──────────────┐       ┌──────────────┐
        │  Prometheus  │──────▶│   Grafana    │
        │   Metrics    │       │  Dashboards  │
        └──────────────┘       └──────────────┘

        ┌──────────────┐       ┌──────────────┐
        │   Promtail   │──────▶│     Loki     │
        │ Log Collector│       │ Log Storage  │
        └──────────────┘       └──────────────┘

                 Prometheus
                      │
                      ▼
               ┌──────────────┐
               │ Alertmanager │
               │    Alerts    │
               └──────────────┘