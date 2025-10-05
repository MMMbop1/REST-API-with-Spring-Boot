# Java Spring Boot REST API

## Overview
This project is a simple REST API that demonstrates CRUD operations, basic authentication, validation, and exception handling.

## Admin Endpoints
GET /admin/members – List all members  
GET /admin/members/{id} – Retrieve a single member  
POST /admin/members – Add a new member  
PUT /admin/members/{id} – Update all member data  
PATCH /admin/members/{id} – Update partial member data  
DELETE /admin/members/{id} – Delete a member (idempotent)

## Member Endpoints
GET /mypages/members – List members (limited fields)  
PUT /mypages/members/{id} – Update logged-in member data

## Technical Details
- Java 21  
- Spring Boot 3.x  
- H2 in-memory database (5 initial members)  
- JPA / Hibernate  
- Basic authentication with role-based access  
- Validation and custom exception handling  
- Separate profiles for dev, production, and disable-security
