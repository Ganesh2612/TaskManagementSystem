package org.example.controller;

import org.example.model.dto.request.TaskRequest;
import org.example.model.dto.request.TaskStatusUpdateRequest;
import org.example.model.dto.response.TaskResponse;
import org.example.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Task management endpoints.
 * This is the main controller handling all task-related operations.
 * Provides endpoints for complete CRUD operations plus status updates.
 */
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@Tag(name = "Task Management", description = "APIs for managing tasks")
public class TaskController {

    private final TaskService taskService;

    /**
     * Creates a new task.
     * Endpoint: POST /api/tasks
     * Requires valid user, category, and priority IDs.
     *
     * @param request TaskRequest containing task details and related entity IDs
     * @return ResponseEntity with TaskResponse and HTTP 201 (CREATED) status
     */
    @PostMapping
    @Operation(summary = "Create a new task",
            description = "Creates a new task with associated user, category, and priority")
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest request) {
        TaskResponse response= taskService.createTask(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Retrieves all tasks with complete information.
     * Endpoint: GET /api/tasks
     * Returns tasks with nested user, category, and priority details.
     *
     * @return ResponseEntity with List of TaskResponse and HTTP 200 (OK) status
     */
    @GetMapping
    @Operation(summary = "Get all tasks",
            description = "Retrieves a list of all tasks with complete details")
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        List<TaskResponse> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    /**
     * Retrieves a specific task by ID.
     * Endpoint: GET /api/tasks/{taskId}
     *
     * @param taskId The unique identifier of the task
     * @return ResponseEntity with TaskResponse and HTTP 200 (OK) status
     */
    @GetMapping("/{taskId}")
    @Operation(summary = "Get task by ID",
            description = "Retrieves a specific task by its unique identifier")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long taskId) {
        TaskResponse response= taskService.getTaskById(taskId);
        return ResponseEntity.ok(response);
    }

    /**
     * Updates an existing task completely.
     * Endpoint: PUT /api/tasks/{taskId}
     * All fields including user, category, and priority can be updated.
     *
     * @param taskId The ID of the task to update
     * @param request TaskRequest containing all updated task details
     * @return ResponseEntity with updated TaskResponse and HTTP 200 (OK) status
     */
    @PutMapping("/{taskId}")
    @Operation(summary = "Update task",
            description = "Updates all information of an existing task")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long taskId,
            @RequestBody TaskRequest request) {
        TaskResponse response= taskService.updateTask(taskId, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Updates only the status of a task.
     * Endpoint: PUT /api/tasks/{taskId}/status
     * This is a lighter operation for changing task status without modifying other fields.
     * Common use case: Moving task from PENDING → IN_PROGRESS → DONE
     *
     * @param taskId The ID of the task to update
     * @param request TaskStatusUpdateRequest containing the new status
     * @return ResponseEntity with updated TaskResponse and HTTP 200 (OK) status
     */
    @PutMapping("/{taskId}/status")
    @Operation(summary = "Update task status",
            description = "Updates only the status of a task (PENDING, IN_PROGRESS, DONE)")
    public ResponseEntity<TaskResponse> updateTaskStatus(
            @PathVariable Long taskId,
            @RequestBody TaskStatusUpdateRequest request) {
        TaskResponse response= taskService.updateTaskStatus(taskId, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Deletes a task from the system.
     * Endpoint: DELETE /api/tasks/{taskId}
     *
     * @param taskId The ID of the task to delete
     * @return ResponseEntity with HTTP 204 (NO_CONTENT) status
     */
    @DeleteMapping("/{taskId}")
    @Operation(summary = "Delete task", description = "Removes a task from the system")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
}

