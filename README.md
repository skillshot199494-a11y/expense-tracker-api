# Expense Tracker API

## Description

Expense Tracker API is a REST API for managing personal expenses.
It allows users to create, view, update, delete, filter expenses, and calculate the total amount spent.

## Technologies

- Java 17+
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- Hibernate
- H2 Database
- Bean Validation
- Maven
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
  "expenseDate": "2026-10-05"
}

###Get All Expenses
GET /api/expenses

###Get Expense by ID
GET /api/expenses/1

###Update Expense
PUT /api/expenses/1
Content-Type: application/json

{
  "title": "Dinner",
  "amount": 3200.00,
  "category": "FOOD",
  "expenseDate": "2026-10-05"
}

###Delete Expense
DELETE /api/expenses/1

###Filter by Category
GET /api/expenses?category=FOOD

###Filter by Date Range
GET /api/expenses?from=2026-10-01&to=2026-10-31

###Get Total Expenses
GET /api/expenses/total

### Pagination and Sorting
GET /api/expenses/page?page=0&size=10&sort=expenseDate&direction=desc

## Example Response

```json
{
  "id": 1,
  "title": "Lunch",
  "amount": 2500.00,
  "category": "FOOD",
  "expenseDate": "2026-10-05"
}

## Validation

- `title` must not be blank and must be at most 100 characters
- `amount` must be greater than 0
- `category` is required
- `expenseDate` is required