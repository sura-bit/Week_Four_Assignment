# Room API

This API provides a simple REST interface for managing rooms, including creating, viewing, updating, filtering, sorting, and deleting rooms.

## Endpoints

| Method | Path              | Status          | Description                                                                  |
| ------ | ----------------- | --------------- | ---------------------------------------------------------------------------- |
| GET    | `/api/rooms`      | 200             | Get all rooms with optional `minCapacity`, `keyword`, and `sort` parameters  |
| GET    | `/api/rooms/{id}` | 200 / 404       | Get a room by ID                                                             |
| POST   | `/api/rooms`      | 201 / 400       | Create a new room and return its Location; capacity must be between 1 and 20 |
| PUT    | `/api/rooms/{id}` | 200 / 400 / 404 | Update an existing room; capacity must be between 1 and 20                   |
| DELETE | `/api/rooms/{id}` | 204 / 404       | Delete a room                                                                |

### GET /api/rooms Filters

The `minCapacity` and `keyword` parameters are optional and can be used separately or together.

| Request                                 | Result  |
| --------------------------------------- | ------- |
| `/api/rooms?minCapacity=6`              | 2 rooms |
| `/api/rooms?keyword=pod`                | 1 room  |
| `/api/rooms?minCapacity=6&keyword=roof` | 1 room  |
| `/api/rooms`                            | 3 rooms |

The `sort` parameter can be used to sort rooms by `capacity` or `name`.

## Capacity Rule

Room capacity must be between 1 and 20.

Invalid capacity values in POST and PUT requests return `400 Bad Request`.

## How to Run

**JDK:** 21

### macOS / Linux

```bash
./gradlew bootRun
```

### Windows

```bat
gradlew.bat bootRun
```

Use `api.http` to test the endpoints at `http://localhost:8080/api/rooms`.

Swagger UI is available at `http://localhost:8080/swagger-ui.html`.

## AI Use

No AI used.
