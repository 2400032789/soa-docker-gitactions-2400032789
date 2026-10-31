# Volunteer Activity Management

Full-stack CRUD application for volunteer activities.

## Entity fields
- `id`: Long (auto-generated)
- `activityName`: String
- `organizerName`: String
- `volunteerCount`: Integer
- `active`: Boolean

## Backend
Package root: `com.klef.soa.docker`

API endpoints:
- `POST /api/activities` - create an activity
- `GET /api/activities` - list all activities
- `DELETE /api/activities/{id}` - delete an activity

Swagger UI: http://localhost:2025/swagger-ui.html

## Run with Docker Compose
From the project root run:
```bash
docker compose up --build -d
```

Frontend: http://localhost:3000
Backend Swagger: http://localhost:2025/swagger-ui.html
PostgreSQL: localhost:5432, database `volunteeractivitydb`
