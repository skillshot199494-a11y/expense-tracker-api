# Expense Tracker API

## Description

Expense Tracker API is a REST API for managing personal expenses.
It allows users to create, view, update, delete, filter expenses, calculate the total amount spent, and work with paginated and sorted results.

## Technologies

- Java 17+
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- Hibernate
- H2 Database
- MySQL
- Bean Validation
- Maven
- Docker
- Docker Compose
- Swagger / OpenAPI
- JUnit / Spring Boot Test
- Postman
- Git
- GitHub

## Features

- Create expenses
- Get all expenses
- Get an expense by ID
- Update expenses
- Delete expenses
- Filter expenses by category
- Filter expenses by date range
- Filter expenses by category and date range
- Calculate total expenses
- Validate request data
- Handle validation errors
- Pagination
- Sorting
- Request and response DTOs
- Integration tests
- MySQL profile support
- Dockerized application and MySQL database
- Swagger / OpenAPI documentation

## Expense Model

| Field | Type | Description |
|---|---|---|
| id | Long | Unique expense identifier |
| title | String | Expense title |
| amount | BigDecimal | Expense amount |
| category | ExpenseCategory | Expense category |
| expenseDate | LocalDate | Date of the expense |
| createdAt | LocalDateTime | Creation timestamp |
| updatedAt | LocalDateTime | Last update timestamp |

## Expense Categories

- `FOOD`
- `TRANSPORT`
- `ENTERTAINMENT`
- `HEALTH`
- `EDUCATION`
- `SHOPPING`
- `OTHER`

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/expenses` | Get all expenses |
| GET | `/api/expenses/{id}` | Get expense by ID |
| POST | `/api/expenses` | Create a new expense |
| PUT | `/api/expenses/{id}` | Update an existing expense |
| DELETE | `/api/expenses/{id}` | Delete an expense |
| GET | `/api/expenses?category=FOOD` | Filter expenses by category |
| GET | `/api/expenses?from=2026-10-01&to=2026-10-31` | Filter expenses by date range |
| GET | `/api/expenses?category=FOOD&from=2026-10-01&to=2026-10-31` | Filter expenses by category and date range |
| GET | `/api/expenses/total` | Get total amount of all expenses |
| GET | `/api/expenses/page?page=0&size=10&sort=expenseDate&direction=asc` | Get paginated and sorted expenses |

## Example Requests

### Create Expense

```http
POST /api/expenses
Content-Type: application/json

{
  "title": "Lunch",
  "amount": 2500.00,
  "category": "FOOD",
  "expenseDate": "2026-10-06"
}
```

### Get All Expenses

```http
GET /api/expenses
```

### Get Expense by ID

```http
GET /api/expenses/1
```

### Update Expense

```http
PUT /api/expenses/1
Content-Type: application/json

{
  "title": "Dinner",
  "amount": 3200.00,
  "category": "FOOD",
  "expenseDate": "2026-10-06"
}
```

### Delete Expense

```http
DELETE /api/expenses/1
```

### Filter by Category

```http
GET /api/expenses?category=FOOD
```

### Filter by Date Range

```http
GET /api/expenses?from=2026-10-01&to=2026-10-31
```

### Get Total Expenses

```http
GET /api/expenses/total
```

### Pagination and Sorting

```http
GET /api/expenses/page?page=0&size=10&sort=expenseDate&direction=desc
```

## Example Response

```json
{
  "id": 1,
  "title": "Lunch",
  "amount": 2500.00,
  "category": "FOOD",
  "expenseDate": "2026-10-06",
  "createdAt": "2026-10-06T11:06:45.768872",
  "updatedAt": "2026-10-06T11:06:45.768905"
}
```

## Validation

- `title` must not be blank and must be at most 100 characters
- `amount` must be greater than 0
- `category` is required
- `expenseDate` is required

## Running the Project

### Default H2 Profile

Run the application without an active profile:

```powershell
.\mvnw.cmd spring-boot:run
```

The application will use the in-memory H2 database.

### MySQL Profile

Create the `expensedb` database in MySQL and provide credentials through environment variables:

```env
DB_USERNAME=root
DB_PASSWORD=your_local_mysql_password
```

Run with the MySQL profile:

```powershell
.\mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=mysql
```

### Docker Compose

Create a `.env` file in the project root:

```env
MYSQL_ROOT_PASSWORD=your_docker_root_password
MYSQL_USER=expense_user
MYSQL_PASSWORD=your_docker_user_password
DB_USERNAME=root
DB_PASSWORD=your_local_mysql_password
```

The `.env` file is ignored by Git and must not be committed.

Build and start the application with MySQL:

```powershell
docker compose up --build
```

The API will be available at:

```text
http://localhost:8080
```

To stop the containers:

```powershell
docker compose down
```

## Swagger / OpenAPI

Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

OpenAPI JSON:

```text
http://localhost:8080/v3/api-docs
```

## Tests

Run the test suite with:

```powershell
.\mvnw.cmd test
```

Run a full clean build with tests:

```powershell
.\mvnw.cmd clean package
```
