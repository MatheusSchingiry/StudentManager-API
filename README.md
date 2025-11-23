## Student Manager API

## Description

**Student Manager** is a RESTful API designed for managing an educational institution, such as a college or university. The project is currently in a strong development phase, aiming to implement all the functionalities represented in the entity-relationship diagram (ERD), which includes the comprehensive management of students, courses, professors, classes, and enrollments.

-----

## Current Features

The API currently features robust management capabilities (CRUD or partial CRUD) for the following core entities:

  *  **Student Management:** Full CRUD lifecycle including Create, Read All, Read by ID, Update, and **Soft Delete**.
  *  **Course Management:** Full CRUD lifecycle including Create, Read All, Read by ID, Update, and **Soft Delete**.
  *  **Teacher Management:** Full CRUD lifecycle including Create, Read All, Read by ID, Update, and **Soft Delete**.
  *  **Subject Management:** Full CRUD lifecycle including Create, Read All, Read by ID, Update, and **Soft Delete**.
  *  **Unit Management (Campus):** Full CRUD lifecycle including Create, Read All, Read by ID, Update, and **Soft Delete**.
  *  **College Class Management:** Includes Create, Read All, Read by ID, and **Soft Delete**.
  *  **Registration Management:** Includes Create, Read All, Read by ID, **Soft Delete**, and a Service method for **Semester Advancement**.

-----

## Roadmap (Next Steps)

The next steps are focused on strengthening the project's technical foundation, improving architecture, and fully implementing advanced academic business rules to complete the system.

### Technical Improvements

These steps are prioritized to enhance the API's scalability, security, and maintainability:

  * **Implement Robust Validation:** Integrate Jakarta Bean Validation for all DTOs and enforce unique constraints (e.g., student register numbers and emails) at the service layer.
  * **Centralized Error Handling:** Implement a Global Exception Handler to treat errors and return consistent, meaningful error responses.
  * **Architectural Refactoring:** **Remove Repository/Service calls from Mappers** to adhere to layered architecture best practices, potentially implementing **MapStruct** for highly performant and cleaner mapping logic.
  * **API Security:** Implement **Spring Security** for authentication and authorization across all endpoints.
  * **Scalable Data Retrieval:** Implement **Pagination (`Pageable`)** on all `GET /all` endpoints to support large datasets.
  * **API Documentation:** Document the API using **SpringDoc OpenAPI** (Swagger UI) for clear, accessible endpoint specifications.
  * **Testing Coverage:** Develop comprehensive **Integration and Unit Tests** to ensure code quality and stability.
  * **Monitoring:** Implement **Spring Boot Actuator** to provide operational monitoring endpoints.

### Business Rules and Core Functionality Expansion

These updates focus on completing the academic data model and business logic:

  * **Academic Data Modeling:** Introduce new entities for **Grades**, **Subject Status** (e.g., `Enrolling`, `Approved`, `Reproved`), and **Attendance/Absences**.
  * **Registration Status Expansion:** Introduce new statuses for `Registration` (e.g., **LOCKED**, **CONCLUDED**) and implement the necessary business logic, including a global method for **Semester Update**.
  * **Student Transcript:** Implement a comprehensive feature to return a Student's full **Academic Transcript**, including personal data, all active/inactive registrations, classes, and associated grades.
  * **Business Rule Enforcement:**
      * Ensure a Student cannot have duplicate active registrations for the same class.
      * Validate that **Students and Classes are active** before creating a new `Registration`.
      * Implement logic to ensure a **Course's workload equals the sum of its linked Subjects' credit hours**.
      * Enforce the rule that a `CollegeClass` can only be created and remain active if its associated `Unit` and `Course` are also active.
  * **Auditing:** Implement a mechanism for **Access/Alteration Logs** tied to a `userId` for compliance and tracking changes.

-----

## Entity-Relationship Diagram (ERD)

This data model serves as the architectural guide for the project's development:

![img.png](img.png)

-----

## Technologies Used

This project is built using the following technologies:

  * **Backend:**
      * [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
      * [Spring Boot 3](https://spring.io/projects/spring-boot)
      * [Spring Web](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
      * [Spring Data JPA](https://www.google.com/search?q=https://spring.io/projects/spring-data-jpa)
      * [Lombok](https://projectlombok.org/)
  * **Database:**
      * [PostgreSQL](https://www.postgresql.org/)
      * [Flyway](https://flywaydb.org/) (for database schema versioning)
  * **Build and Management:**
      * [Apache Maven](https://maven.apache.org/)

-----

## How to Run the Project

Follow the steps below to run the application locally.

### Prerequisites

  * **Java Development Kit (JDK) 17** or higher.
  * **Apache Maven** 3.9 or higher.
  * A running instance of **PostgreSQL**.

### Steps

1.  **Clone the repository:**

    ```bash
    git clone https://github.com/MatheusSchingiry/StudentManager-API.git
    cd StudentManager-API
    ```

2.  **Configure the Database:**
    The application uses environment variables to configure the database connection. You can either:

      * Create a `.env` file in the project root.
      * Or set the variables directly in your operating system.

    The `application.properties` file expects the following variables:

    ```properties
    DATABASE_URL=jdbc:postgresql://localhost:5432/your_db
    DATABASE_USERNAME=your_username
    DATABASE_PASSWORD=your_password
    ```

    *Don't forget to create the database in your PostgreSQL instance before starting the application.*

3.  **Run the application:**
    Use the Maven Wrapper included in the project to ensure compatibility.

    ```bash
    # For Linux/macOS
    ./mvnw spring-boot:run

    # For Windows
    ./mvnw.cmd spring-boot:run
    ```

    The API will be available at `http://localhost:8080`.

-----

## Project Structure

The project follows a standard structure for Spring Boot applications, dividing responsibilities into packages:

```
com.StudentManager.StudentManager
├── Controller/  # Presentation layer (REST Endpoints)
├── DTO/         # Data Transfer Objects (Request/Response)
├── Mapper/      # Mapping classes between DTOs and Entities
├── Model/       # JPA Entities and Enums
├── Repository/  # Spring Data JPA interfaces
└── Service/     # Business logic layer
```

-----

## API Endpoints

Below are the currently available endpoints, grouped by entity.

### Students (`/students`)

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/students` | Returns a list of all active students. |
| `GET` | `/students/{id}` | Returns a specific student by ID. |
| `POST` | `/students` | Creates a new student. |
| `PUT` | `/students/{id}` | Updates an existing student's data. |
| `DELETE` | `/students/{id}` | Performs a soft delete (sets status to `INACTIVE`). |

### Courses (`/courses`)

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/courses` | Returns a list of all active courses. |
| `GET` | `/courses/{id}` | Returns a specific course by ID. |
| `POST` | `/courses` | Creates a new course. |
| `PUT` | `/courses/{id}` | Updates an existing course's data. |
| `DELETE` | `/courses/{id}` | Performs a soft delete (sets status to `INACTIVE`). |

### Teachers (`/teachers`)

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/teachers` | Returns a list of all active teachers. |
| `GET` | `/teachers/{id}` | Returns a specific teacher by ID. |
| `POST` | `/teachers` | Creates a new teacher. |
| `PUT` | `/teachers/{id}` | Updates an existing teacher's data. |
| `DELETE` | `/teachers/{id}` | Performs a soft delete (sets status to `INACTIVE`). |

### Subjects (`/subjects`)

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/subjects` | Returns a list of all active subjects. |
| `GET` | `/subjects/{id}` | Returns a specific subject by ID. |
| `POST` | `/subjects` | Creates a new subject. |
| `PUT` | `/subjects/{id}` | Updates an existing subject's data. |
| `DELETE` | `/subjects/{id}` | Performs a soft delete (sets status to `INACTIVE`). |

### Units (`/units`)

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/units` | Returns a list of all active units (campuses). |
| `GET` | `/units/{id}` | Returns a specific unit by ID. |
| `POST` | `/units` | Creates a new unit. |
| `PUT` | `/units/{id}` | Updates an existing unit's data. |
| `DELETE` | `/units/{id}` | Performs a soft delete (sets status to `INACTIVE`). |

### College Classes (`/college-classes`)

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/college-classes` | Returns a list of all active college classes. |
| `GET` | `/college-classes/{id}` | Returns a specific class by ID. |
| `POST` | `/college-classes` | Creates a new college class. |
| `DELETE` | `/college-classes/{id}` | Performs a soft delete (sets status to `INACTIVE`). |

### Registrations (`/registrations`)

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/registrations` | Returns a list of all active registrations. |
| `GET` | `/registrations/{id}` | Returns a specific registration by ID. |
| `POST` | `/registrations` | Creates a new registration. |
| `DELETE` | `/registrations/{id}` | Performs a soft delete (sets status to `INACTIVE`). |
