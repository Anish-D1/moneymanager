# Money Manager Pro – Backend

A RESTful backend application for **Money Manager Pro**, a personal finance management system built using Java and Spring Boot.

The backend handles user authentication, account activation, financial data management, database operations, and secure API communication with the React frontend.

## 🌐 Application

**Frontend:** [Money Manager Pro](https://money-manager-frontend-one-wine.vercel.app/)

**Backend:** [Money Manager Pro API](https://money-manager-backend-riu9.onrender.com)

## ✨ Features

- User registration and login
- JWT-based authentication
- Password hashing and secure authentication
- Email-based account activation
- Protected REST API endpoints
- Income management
- Expense management
- User profile management
- Profile image uploads using Cloudinary
- PostgreSQL database integration
- Email delivery through Brevo HTTPS API

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Java 21 | Backend programming language |
| Spring Boot 3.5.3 | Backend framework |
| Spring Security | Application security |
| JWT | Token-based authentication |
| Spring Data JPA | Database access |
| Hibernate | ORM |
| PostgreSQL | Relational database |
| Maven | Dependency management and build |
| Brevo HTTPS API | Email delivery |
| Cloudinary | Profile image storage |
| Render | Backend deployment |

## 🏗️ Application Architecture

```text
              React Frontend
                     |
                     | HTTP Requests
                     | JWT Authentication
                     v
             Spring Boot Backend
                     |
          +----------+----------+
          |          |          |
          v          v          v
      Security    REST APIs   Services
          |          |          |
          +----------+----------+
                     |
                     v
              Spring Data JPA
                     |
                     v
                Hibernate
                     |
                     v
                PostgreSQL

       External Integrations:
       - Brevo HTTPS API
       - Cloudinary
```

## ⚙️ Getting Started

### Prerequisites

- Java 21
- Maven
- PostgreSQL
- Git
- An IDE such as IntelliJ IDEA or VS Code

### 1. Clone the repository

```bash
git clone https://github.com/Anish-D1/moneymanager.git
```

### 2. Navigate to the project directory

```bash
cd moneymanager
```

### 3. Configure environment variables

Configure your database credentials and external service credentials through environment variables.

The application requires configuration for:

- PostgreSQL database URL
- Database username and password
- JWT signing secret
- Brevo API credentials
- Cloudinary credentials
- Frontend origin for CORS

**Important:** Never commit database passwords, API keys, JWT secrets, or other credentials to GitHub.

Use the configuration property names expected by your application's `application.properties` or `application.yml`.

### 4. Build the application

```bash
mvn clean install
```

### 5. Run the application

```bash
mvn spring-boot:run
```

The application runs on the configured server port.

## 🔐 Security

The backend uses Spring Security and JWT authentication.

- Passwords are securely hashed.
- JWT tokens are used to authenticate requests.
- Protected endpoints require valid authentication.
- Account activation is handled through email verification.

## 📧 Email Integration

Brevo's HTTPS API is used to deliver account activation emails.

This integration supports account verification without relying on traditional SMTP delivery.

## 🗄️ Database

PostgreSQL is used for persistent storage.

Spring Data JPA and Hibernate handle database interactions and object-relational mapping.

## ☁️ Deployment

The backend is deployed on **Render**.

- Source code managed through GitHub
- Maven-based application build
- Java 21 runtime
- PostgreSQL database hosted on Supabase
- External services integrated through secure configuration

## 🔗 Related Repository

**Frontend:** [Money Manager Pro – React](https://github.com/Anish-D1/moneymanagerwebapp)

**Live Application:** [Money Manager Pro](https://money-manager-frontend-one-wine.vercel.app/)

## 👨‍💻 Developer

**Anish Devan**

- GitHub: [Anish-D1](https://github.com/Anish-D1)
- LinkedIn: [Anish Devan](https://www.linkedin.com/in/anish-devan)

---

⭐ Developed as a full-stack portfolio project to demonstrate Java backend development, REST API design, authentication, database integration, and cloud deployment.
