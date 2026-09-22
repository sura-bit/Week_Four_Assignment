# Room API
This API provides a simple REST interface for managing rooms, including creating, viewing, updating, filtering, and deleting rooms.
## Endpoints

| Method | Path              | Status    | Description                                                            |
| ------ | ----------------- | --------- |------------------------------------------------------------------------|
| GET    | `/api/rooms`      | 200       | Get all rooms, with optional `minCapacity`, `keyword` and `sort` filters |
| GET    | `/api/rooms/{id}` | 200 / 404 | Get a room by ID                                                       |
| POST   | `/api/rooms`      | 201       | Create a new room and return its location                              |
| PUT    | `/api/rooms/{id}` | 200 / 404 | Update an existing room                                                |
| DELETE | `/api/rooms/{id}` | 204 / 404 | Delete a room                                                          |

### GET /api/rooms Filters

The `minCapacity` and `keyword` parameters are optional and can be used separately or together.

| Request                                 | Result  |
| --------------------------------------- | ------- |
| `/api/rooms?minCapacity=6`              | 2 rooms |
| `/api/rooms?keyword=pod`                | 1 room  |
| `/api/rooms?minCapacity=6&keyword=roof` | 1 room  |
| `/api/rooms`                            | 3 rooms |

## How to Run
Run the Spring Boot application from IntelliJ.

Use `api.http` to test the endpoints at `http://localhost:8080/api/rooms`.

Extension: Added optional sorting by capacity or name using the `sort` query parameter.