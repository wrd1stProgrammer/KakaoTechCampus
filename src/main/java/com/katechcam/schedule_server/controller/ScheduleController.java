package com.katechcam.schedule_server.controller;

import com.katechcam.schedule_server.dto.*;
import com.katechcam.schedule_server.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService service;

    @PostMapping
    public ResponseEntity<ScheduleResponse> create(@RequestBody @Valid ScheduleRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(req));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }
    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponse> update(
            @PathVariable Long id, @RequestBody @Valid ScheduleRequest req) {
        return ResponseEntity.ok(service.update(id, req));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id, @RequestParam String password) {
        service.delete(id, password);
        return ResponseEntity.noContent().build();
    }

}
