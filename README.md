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
  - `/users`
  - `/auth/login`
  - `/auth/introspect`
- Protected all other endpoints
- Disabled CSRF (stateless API)

---

## 🔐 Security Highlights

- Token-based authentication using `JWT`
- Introspection endpoint to validate token state
- Stateless API with CSRF disabled
- Role-based access control (planned for future)

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
git clone https://github.com/your-username/todoapp-backend.git
cd todoapp-backend
./mvnw spring-boot:run
