package org.example.controller;

import org.example.model.dto.request.UserRequest;
import org.example.model.dto.response.UserResponse;
import org.example.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for User management endpoints.
 * This controller handles all HTTP requests related to user operations.
 * It acts as the entry point for the API, delegating business logic to the service layer.
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "APIs for managing users in the system")
public class UserController {

    private final UserService userService;

    /**
     * Creates a new user.
     * Endpoint: POST /api/users
     *
     * @param request UserRequest containing user details (name and email)
     * @return ResponseEntity with UserResponse and HTTP 201 (CREATED) status
     */
    @PostMapping
    @Operation(summary = "Create a new user", description = "Creates a new user with the provided name and email")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        UserResponse response= userService.createUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Retrieves all users in the system.
     * Endpoint: GET /api/users
     *
     * @return ResponseEntity with List of UserResponse and HTTP 200 (OK) status
     */
    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieves a list of all users in the system")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Retrieves a specific user by their ID.
     * Endpoint: GET /api/users/{userId}
     *
     * @param userId The unique identifier of the user to retrieve
     * @return ResponseEntity with UserResponse and HTTP 200 (OK) status
     */
    @GetMapping("/{userId}")
    @Operation(summary = "Get user by ID", description = "Retrieves a specific user by their unique identifier")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {
        UserResponse response= userService.getUserById(userId);
        return ResponseEntity.ok(response);
    }

    /**
     * Updates an existing user's information.
     * Endpoint: PUT /api/users/{userId}
     *
     * @param userId The ID of the user to update
     * @param request UserRequest containing updated user details
     * @return ResponseEntity with updated UserResponse and HTTP 200 (OK) status
     */
    @PutMapping("/{userId}")
    @Operation(summary = "Update user", description = "Updates an existing user's information")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long userId,
            @RequestBody UserRequest request) {
        UserResponse response= userService.updateUser(userId, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Deletes a user from the system.
     * Endpoint: DELETE /api/users/{userId}
     *
     * @param userId The ID of the user to delete
     * @return ResponseEntity with HTTP 204 (NO_CONTENT) status
     */
    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete user", description = "Removes a user from the system")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}

