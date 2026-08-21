# Smart HRMS

### Smart Employee Attendance & Leave Management System

A web-based HR management system developed to simplify employee, department, attendance, and leave management in one place.

---

## Overview

Smart HRMS is a Spring Boot based web application developed during my summer internship.

The application provides a centralized interface for managing employee information and day-to-day HR activities such as attendance and leave management.

The project focuses on keeping the workflow simple, organized, and easy to use.

---

## Features

### Employee Management
- Add and manage employee records
- View employee details
- Update employee information
- Search and manage employee data

### Department Management
- Create and manage departments
- Maintain department locations
- Associate employees with departments

### Attendance Management
- Employee check-in
- Employee check-out
- Attendance records
- Attendance status
- Attendance summary

### Leave Management
- Apply for leave
- View leave requests
- Approve or reject requests
- Maintain leave history
- Leave calendar

### Dashboard & Reports
- Employee statistics
- Department overview
- Attendance information
- Leave information
- Reports and summary data

### User Interface
- Secure login
- Responsive interface
- Light theme
- Dark theme
- System theme

---

## Technology Stack

| Area | Technology |
|---|---|
| Language | Java |
| Backend | Spring Boot |
| Web Layer | Spring MVC |
| Template Engine | Thymeleaf |
| Persistence | Spring Data JPA |
| ORM | Hibernate |
| Database | MySQL / H2 |
| Frontend | HTML, CSS, Bootstrap, JavaScript |
| Build Tool | Maven |
| Version Control | Git & GitHub |

---

## Application Structure

```text
Smart-HRMS
│
├── src
│   └── main
│       ├── java
│       │   └── employee_management
│       │       ├── config
│       │       ├── controller
│       │       ├── entity
│       │       ├── exception
│       │       ├── export
│       │       ├── repository
│       │       └── service
│       │
│       └── resources
│           ├── static
│           │   ├── css
│           │   └── js
│           │
│           ├── templates
│           │   ├── dashboard.html
│           │   ├── employees.html
│           │   ├── departments.html
│           │   ├── attendance.html
│           │   ├── leaves.html
│           │   ├── employee-profile.html
│           │   ├── attendance-summary.html
│           │   ├── leave-calendar.html
│           │   └── login.html
│           │
│           └── application.properties
│
├── pom.xml
├── mvnw
├── mvnw.cmd
├── .gitignore
└── README.md
