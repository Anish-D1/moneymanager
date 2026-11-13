# Money Manager  – Backend

A secure RESTful backend for **Money Manager Pro**, a full-stack personal finance management application built using **Java 21 and Spring Boot**.

The backend manages user authentication, account activation, income and expense records, profile management, and database operations. It provides REST APIs consumed by the React frontend.

## 🌐 Live Application

- **Frontend:** [Money Manager Pro](https://money-manager-frontend-one-wine.vercel.app/)
- **Backend:** [Money Manager Pro API](https://money-manager-backend-riu9.onrender.com)
- **Frontend Repository:** [moneymanagerwebapp](https://github.com/Anish-D1/moneymanagerwebapp)

## ✨ Key Features

### Authentication and Security
- User registration and login
- JWT-based authentication
- Secure password hashing
- Protected REST API endpoints
- Email-based account activation

### Financial Management
- Income management
- Expense management
- Personal financial data management
- Persistent database storage

### User Profile
- User profile management
- Profile image uploads using Cloudinary

### External Integrations
- Brevo HTTPS API for account activation emails
- Cloudinary for profile image storage

## 🛠️ Technology Stack

| Technology | Purpose |
|---|---|
| Java 21 | Backend programming language |
| Spring Boot 3.5.3 | Backend framework |
| Spring Security | Authentication and authorization |
| JWT | Token-based authentication |
| Spring Data JPA | Database access |
| Hibernate | Object-relational mapping |
| PostgreSQL | Relational database |
| Maven | Dependency management and build |
| Brevo HTTPS API | Email delivery |
| Cloudinary | Image storage |
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
              +---------+---------+
              |         |         |
              v         v         v
          Security   Controllers Services
              |         |         |
              +---------+---------+
                        |
                        v
                 Spring Data JPA
                        |
                        v
                    Hibernate
                        |
                        v
                   PostgreSQL

              External Integrations
                 /            \
                v              v
          Brevo HTTPS API   Cloudinary
```

## 🔐 Authentication Flow

The application uses Spring Security and JSON Web Tokens (JWT) to secure API communication.

1. A user registers through the frontend.
2. The backend processes the registration request.
3. An account activation email is sent through Brevo.
4. The user activates their account through the verification link.
5. The user logs in with their credentials.
6. The backend authenticates the user and issues a JWT.
7. The frontend uses the token when accessing protected API endpoints.

**Security highlights:**
- Password hashing
- JWT-based authentication
- Protected API endpoints
- Account activation through email verification

## 📧 Email Integration

The application uses the **Brevo HTTPS API** for sending account activation emails.

This integration handles email delivery through HTTPS API requests rather than traditional SMTP-based delivery.

## 🖼️ Image Upload Integration

**Cloudinary** is used for profile image storage.

The backend integrates with Cloudinary to handle user profile image uploads and storage.

## 🗄️ Database

The application uses **PostgreSQL** for persistent data storage.

Database operations are handled using:

- Spring Data JPA
- Hibernate
- Entity-based database mapping

## 📡 REST API

The backend exposes REST endpoints for authentication, user profile management, and financial record management.

The API is organized around the following functional areas:

| Module | Responsibility |
|---|---|
| Authentication | Registration, login, and account activation |
| Profile | User profile and profile image management |
| Income | Income record management |
| Expense | Expense record management |

*Refer to the source code for the exact endpoint paths, HTTP methods, and request/response structures.*

## ⚙️ Getting Started

### Prerequisites

Make sure you have installed:

- Java 21
- Maven
- PostgreSQL
- Git
- IntelliJ IDEA or another Java IDE

### 1. Clone the repository

```bash
git clone https://github.com/Anish-D1/moneymanager.git
```

### 2. Navigate to the project directory

```bash
cd moneymanager
```

### 3. Configure environment variables

Configure the following application settings through environment variables or your local application configuration:

- PostgreSQL database URL
- Database username and password
- JWT signing secret
- Brevo API credentials
- Cloudinary credentials
- Frontend origin for CORS

Use the property names expected by your application's `application.properties` or `application.yml`.

**Important:** Never commit database credentials, API keys, JWT secrets, or other sensitive information to GitHub.

### 4. Build the application

```bash
mvn clean install
```

### 5. Run the application

```bash
mvn spring-boot:run
```

The application starts on the configured server port.

## ☁️ Deployment

The backend is deployed using **Render**.

| Component | Platform |
|---|---|
| Backend API | Render |
| Database | Supabase PostgreSQL |
| Frontend | Vercel |
| Email delivery | Brevo |
| Image storage | Cloudinary |
| Source control | GitHub |

**Live Backend:**

https://money-manager-backend-riu9.onrender.com

## 🔗 Related Repository

**Frontend Repository:**  
[Money Manager Pro – React](https://github.com/Anish-D1/moneymanagerwebapp)

**Live Application:**  
[Money Manager Pro](https://money-manager-frontend-one-wine.vercel.app/)

## 👨‍💻 Developer

**Anish Devan**

- GitHub: [Anish-D1](https://github.com/Anish-D1)
- LinkedIn: [Anish Devan](https://www.linkedin.com/in/anish-devan)

---

⭐ Developed as a hands-on full-stack portfolio project to demonstrate Java backend development, REST API design, application security, database integration, and cloud deployment.
