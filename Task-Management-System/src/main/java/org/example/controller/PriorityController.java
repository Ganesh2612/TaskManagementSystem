package org.example.controller;

import org.example.model.dto.request.PriorityRequest;
import org.example.model.dto.response.PriorityResponse;
import org.example.service.PriorityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Priority management endpoints.
 * Manages all HTTP requests related to task priorities.
 */
@RestController
@RequestMapping("/api/priorities")
@RequiredArgsConstructor
@Tag(name = "Priority Management", description = "APIs for managing task priority levels")
public class PriorityController {

    private final PriorityService priorityService;

    /**
     * Creates a new priority level.
     * Endpoint: POST /api/priorities
     *
     * @param request PriorityRequest containing priority name and level
     * @return ResponseEntity with PriorityResponse and HTTP 201 (CREATED) status
     */
    @PostMapping
    @Operation(summary = "Create a new priority", description = "Creates a new priority level for tasks")
    public ResponseEntity<PriorityResponse> createPriority(@RequestBody PriorityRequest request) {
        PriorityResponse response= priorityService.createPriority(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Retrieves all priority levels.
     * Endpoint: GET /api/priorities
     *
     * @return ResponseEntity with List of PriorityResponse and HTTP 200 (OK) status
     */
    @GetMapping
    @Operation(summary = "Get all priorities", description = "Retrieves a list of all priority levels")
    public ResponseEntity<List<PriorityResponse>> getAllPriorities() {
        List<PriorityResponse> priorities = priorityService.getAllPriorities();
        return ResponseEntity.ok(priorities);
    }

    /**
     * Retrieves a specific priority by ID.
     * Endpoint: GET /api/priorities/{priorityId}
     *
     * @param priorityId The unique identifier of the priority
     * @return ResponseEntity with PriorityResponse and HTTP 200 (OK) status
     */
    @GetMapping("/{priorityId}")
    @Operation(summary = "Get priority by ID", description = "Retrieves a specific priority by its unique identifier")
    public ResponseEntity<PriorityResponse> getPriorityById(@PathVariable Long priorityId) {
        PriorityResponse response= priorityService.getPriorityById(priorityId);
        return ResponseEntity.ok(response);
    }

    /**
     * Updates an existing priority.
     * Endpoint: PUT /api/priorities/{priorityId}
     *
     * @param priorityId The ID of the priority to update
     * @param request PriorityRequest containing updated priority details
     * @return ResponseEntity with updated PriorityResponse and HTTP 200 (OK) status
     */
    @PutMapping("/{priorityId}")
    @Operation(summary = "Update priority", description = "Updates an existing priority's information")
    public ResponseEntity<PriorityResponse> updatePriority(
            @PathVariable Long priorityId,
            @RequestBody PriorityRequest request) {
        PriorityResponse response= priorityService.updatePriority(priorityId, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Deletes a priority.
     * Endpoint: DELETE /api/priorities/{priorityId}
     *
     * @param priorityId The ID of the priority to delete
     * @return ResponseEntity with HTTP 204 (NO_CONTENT) status
     */
    @DeleteMapping("/{priorityId}")
    @Operation(summary = "Delete priority", description = "Removes a priority from the system")
    public ResponseEntity<Void> deletePriority(@PathVariable Long priorityId) {
        priorityService.deletePriority(priorityId);
        return ResponseEntity.noContent().build();
    }
}

