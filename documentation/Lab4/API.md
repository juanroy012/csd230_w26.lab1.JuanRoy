# API Design — Books & Magazines

Conventions
- Base path: `/api/rest`
- All payloads are JSON. Dates/times use ISO-8601.


## Books
Resource: `Book` (backed by `BookEntity`)

- GET /api/rest/books
  - Description: List all books
  - Response: 200 OK
  - Example response:
    ```json
    [
      {"id":1,"name":"Java 101","price":29.99,"copies":5,"author":"Alice"}
    ]
    ```

- GET /api/rest/books/{id}
  - Description: Get a single book
  - Responses: 200 OK, 404 Not Found
  - Example response (200):
    ```json
    {"id":1,"name":"Java 101","price":29.99,"copies":5,"author":"Alice"}
    ```

- POST /api/rest/books
  - Description: Create a new book
  - Request body:
    ```json
    {"name":"New Book","price":14.99,"copies":10,"author":"Juan"}
    ```
  - Responses: 201 Created (returns created Book), 400 Bad Request

- PUT /api/rest/books/{id}
  - Description: Update/replace a book
  - Request body: same as POST
  - Responses: 200 OK (updated), 201 Created (if created), 400 Bad Request, 404 Not Found

- DELETE /api/rest/books/{id}
  - Description: Delete a book
  - Responses: 204 No Content (success), 404 Not Found


## Magazines
Resource: `Magazine` (backed by `MagazineEntity`)

- GET /api/rest/magazines
  - Description: List all magazines
  - Response: 200 OK
  - Example response:
    ```json
    [
      {"id":10,"name":"Tech Monthly","price":5.99,"copies":100,"orderQty":20,"currentIssue":"2026-02-01T00:00:00Z"}
    ]
    ```

- GET /api/rest/magazines/{id}
  - Description: Get a single magazine
  - Responses: 200 OK, 404 Not Found
  - Example response (200):
    ```json
    {"id":10,"name":"Tech Monthly","price":5.99,"copies":100,"orderQty":20,"currentIssue":"2026-02-01T00:00:00Z"}
    ```

- POST /api/rest/magazines
  - Description: Create a new magazine
  - Request body:
    ```json
    {"name":"New Mag","price":4.5,"copies":50,"orderQty":10,"currentIssue":"2026-03-01T00:00:00Z"}
    ```
  - Responses: 201 Created (returns created Magazine), 400 Bad Request

- PUT /api/rest/magazines/{id}
  - Description: Update/replace a magazine
  - Request body: same as POST
  - Responses: 200 OK (updated), 201 Created (if created), 400 Bad Request, 404 Not Found

- DELETE /api/rest/magazines/{id}
  - Description: Delete a magazine
  - Responses: 204 No Content (success), 404 Not Found


---

Errors (common)
- 200 OK, 201 Created, 204 No Content
- 400 Bad Request — invalid payload
- 401 Unauthorized / 403 Forbidden — auth required or insufficient role
- 404 Not Found — resource missing
- 500 Internal Server Error — server error

