package org.example.weekfourassignment;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final List<Room> rooms = new ArrayList<>(List.of(
            new Room(1L, "Seminar A", 8),
            new Room(2L, "Study Pod", 4),
            new Room(3L, "Rooftop Room", 12)
    ));

    // GET /api/rooms
    @GetMapping
    public List<Room> findAll(
            @RequestParam(required = false) Integer minCapacity,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String sort) {

        List<Room> result = rooms.stream()
                .filter(room -> minCapacity == null || room.capacity() >= minCapacity)
                .filter(room -> keyword == null ||
                        room.name().toLowerCase().contains(keyword.toLowerCase()))
                .toList();

        if ("capacity".equals(sort)) {
            return result.stream()
                    .sorted(Comparator.comparing(Room::capacity))
                    .toList();
        }

        if ("name".equals(sort)) {
            return result.stream()
                    .sorted(Comparator.comparing(Room::name))
                    .toList();
        }

        return result;
    }


    // GET /api/rooms/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Room> findOne(@PathVariable Long id) {
        return rooms.stream()
                .filter(room -> room.id().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/rooms
    @PostMapping
    public ResponseEntity<Room> create(@RequestBody Room room) {
        Room newRoom = new Room(
                (long) (rooms.size() + 1),
                room.name(),
                room.capacity()
        );

        rooms.add(newRoom);

        return ResponseEntity
                .created(URI.create("/api/rooms/" + newRoom.id()))
                .body(newRoom);
    }

    // PUT /api/rooms/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Room> update(
            @PathVariable Long id,
            @RequestBody Room room) {

        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).id().equals(id)) {
                Room updatedRoom = new Room(
                        id,
                        room.name(),
                        room.capacity()
                );

                rooms.set(i, updatedRoom);

                return ResponseEntity.ok(updatedRoom);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // DELETE /api/rooms/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        boolean removed = rooms.removeIf(room -> room.id().equals(id));

        if (removed) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
