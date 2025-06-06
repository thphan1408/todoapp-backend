# ✅ Todo App API (Spring Boot + JWT + Spring Security)

A backend REST API built with **Spring Boot**, demonstrating real-world features such as user authentication, JWT handling, secure endpoints, CI with GitHub Actions, and clean project structure.

---

## 📌 Project Structure

This repository is structured into 3 learning phases:

### 🔹 v1 - Basic
> Core app setup and task management

- CRUD API for `Tasks`
- Configured:
  - Global Exception Handling (`AppException`, `ErrorCode`)
  - Standard API Response Format
- DTO usage with MapStruct
- Lombok annotations for boilerplate reduction
- Connected to MySQL (or any other relational DB)
- CI pipeline using GitHub Actions

---

### 🔹 v2 - Intermediate
> User entity, authentication, and JWT integration

- Added `User` entity and persistence
- Password hashing using `BCryptPasswordEncoder`
- JWT token generation and validation
- Created `/auth/login` and `/auth/introspect` endpoints

---

### 🔹 v3 - Advanced
> Spring Security integration

- Applied `SecurityFilterChain` configuration (no WebSecurityConfigurerAdapter)
- Integrated `spring-boot-starter-oauth2-resource-server` for JWT authentication
- JWT verification using built-in **Nimbus** decoder (`HS512`)
- Enabled public access to selected endpoints:
  - `METHOD: POST - /users` => Create new user
  - `/auth/login`
  - `/auth/introspect`
- Protected all other endpoints using Spring Security
- Role-based access control with `@PreAuthorize and @PostAuthorize (via @EnableMethodSecurity)`
- Automatically creates default `admin` account on first run (if none exists)
- Centralized exception handling:
  - Custom exception classes with error codes
  - Unified API error response structure (`status`, `message`, `code`)
- Disabled CSRF for stateless JWT-based API
---

## 🔐 Security Highlights

- Token-based authentication using JWT
- Role-based authorization with method-level annotations: `@PreAuthorize / @PostAuthorize`
- Stateless API with CSRF disabled
- Public and private endpoint management via config
- Automatic bootstrapping of `admin` account on app startup
- Centralized error handling for consistent API responses

---

## ⚙️ Tech Stack

- Java 21
- Spring Boot 3.4.5
- Spring Security
- OAuth2 Resource Server (JWT support)
- MapStruct
- Lombok
- MySQL
- GitHub Actions (CI)

---

## 🧪 How to Run

You can configure `jwt.signerKey` and database settings via:

- `application.yml`
- or environment variables (`SPRING_DATASOURCE_URL`, etc.)

### Clone & Run

```bash
git clone https://github.com/thphan1408/todoapp-backend.git
cd todoapp-backend
./mvnw spring-boot:run
