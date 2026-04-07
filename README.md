# Finance Dashboard Backend

A robust backend for a finance dashboard system with role-based access control, JWT authentication, and comprehensive financial record management.

## 📋 Table of Contents
- [Features](#features)
- [Tech Stack](#tech-stack)
- [System Requirements](#system-requirements)
- [Installation & Setup](#installation--setup)
- [Database Setup](#database-setup)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Role Based Access Control](#role-based-access-control)
- [Sample Users](#sample-users)
- [Project Structure](#project-structure)
- [Error Handling](#error-handling)
- [Testing](#testing)
- [Deployment](#deployment)
- [Contributing](#contributing)
- [License](#license)

## ✨ Features

### Core Features
- ✅ **User Management** - Create, read, update, delete users with role assignment
- ✅ **Role-Based Access Control** - ADMIN, ANALYST, VIEWER roles with proper permissions
- ✅ **Financial Records Management** - Full CRUD operations for financial transactions
- ✅ **Dashboard Analytics** - Real-time calculations of income, expenses, and trends
- ✅ **JWT Authentication** - Secure token-based authentication
- ✅ **Input Validation** - Comprehensive request validation with meaningful error messages

### Advanced Features
- 🔍 **Filtering** - Filter records by date, category, and type
- 📊 **Category Analytics** - Category-wise expense breakdown
- 📈 **Monthly Trends** - Income vs expense trends over time
- 🔄 **Soft Delete** - Records can be soft deleted instead of permanent removal
- 🚦 **User Status Management** - Activate/Deactivate user accounts

## 🛠 Tech Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 21 | Core language |
| Spring Boot | 3.1.5 | Application framework |
| Spring Security | 6.1.5 | Authentication & Authorization |
| Spring Data JPA | 3.1.5 | Database operations |
| JWT | 0.11.5 | Token generation/validation |
| MySQL | 8.0+ | Database |
| Maven | 3.8+ | Build tool |
| Lombok | 1.18.30 | Boilerplate reduction |

## 💻 System Requirements

- **Java JDK 17 or higher** 
- **MySQL 8.0 or higher** 
- **Maven 3.8 or higher** 

## 🔧 Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/Shivtanaya-2003/finance-dashboard-backend.git
cd finance-dashboard-backend
