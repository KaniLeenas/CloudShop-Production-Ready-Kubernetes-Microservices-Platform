# ==============================
# Stage 1: Build
# ==============================
FROM eclipse-temurin:25-jdk-ubi10-minimal AS builder

WORKDIR /build

COPY . .

RUN chmod +x mvnw

RUN ./mvnw clean package -DskipTests


# ==============================
# Stage 2: Runtime
# ==============================
FROM eclipse-temurin:25-jre-ubi10-minimal

WORKDIR /app

COPY --from=builder /build/target/*.jar app.jar

RUN useradd --system --create-home appuser

USER appuser

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
