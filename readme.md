# Java Spring Boot REST API

## Overview
This project is a simple REST API with basic authentication, validation, and exception handling with Spring Boot framework.

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
