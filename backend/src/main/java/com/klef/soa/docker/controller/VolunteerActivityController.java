package com.klef.soa.docker.controller;

import com.klef.soa.docker.dto.VolunteerActivityRequest;
import com.klef.soa.docker.dto.VolunteerActivityResponse;
import com.klef.soa.docker.service.VolunteerActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/activities")
@Tag(name = "Volunteer Activities")
public class VolunteerActivityController {
    private final VolunteerActivityService service;

    public VolunteerActivityController(VolunteerActivityService service) {
        this.service = service;
    }

    @Operation(summary = "Add a volunteer activity (ID is auto-generated)")
    @PostMapping
    public ResponseEntity<VolunteerActivityResponse> addActivity(@Valid @RequestBody VolunteerActivityRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addActivity(request));
    }

    @Operation(summary = "Get all volunteer activities")
    @GetMapping
    public List<VolunteerActivityResponse> getAllActivities() {
        return service.getAllActivities();
    }

    @Operation(summary = "Delete a volunteer activity by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteActivity(@PathVariable Long id) {
        service.deleteActivity(id);
        return ResponseEntity.ok(Map.of("message", "Activity " + id + " deleted successfully"));
    }
}
