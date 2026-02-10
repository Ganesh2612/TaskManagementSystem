package org.example.service;

import org.example.exception.UserNotFoundException;
import org.example.model.dto.request.UserRequest;
import org.example.model.dto.response.UserResponse;
import org.example.model.entity.User;
import org.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for User-related business logic.
 * Contains all operations related to user management including CRUD operations.
 * This layer sits between the controller and repository, handling business rules and validations.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * Creates a new user in the system.
     *
     * @param request UserRequest containing user details (name and email)
     * @return UserResponse with the created user's information including generated ID
     */
    @Transactional
    public UserResponse createUser(UserRequest request) {
        // Create new User entity from request data
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());

        // Save to database
        User savedUser = userRepository.save(user);

        // Convert entity to response DTO and return
        return mapToResponse(savedUser);
    }

    /**
     * Retrieves all users from the database.
     *
     * @return List of UserResponse containing all users in the system
     */
    public List<UserResponse> getAllUsers() {
        // Fetch all users and convert each to UserResponse
        return userRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a specific user by their ID.
     *
     * @param id The unique identifier of the user
     * @return UserResponse with the user's information
     * @throws UserNotFoundException if no user exists with the given ID
     */
    public UserResponse getUserById(Long id) {
        // Find user or throw exception if not found
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        return mapToResponse(user);
    }

    /**
     * Updates an existing user's information.
     *
     * @param id The ID of the user to update
     * @param request UserRequest containing updated user details
     * @return UserResponse with updated user information
     * @throws UserNotFoundException if no user exists with the given ID
     */
    @Transactional
    public UserResponse updateUser(Long id, UserRequest request) {
        // Find existing user or throw exception
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        // Update user fields
        user.setName(request.getName());
        user.setEmail(request.getEmail());

        // Save changes and return updated user
        User updatedUser = userRepository.save(user);
        return mapToResponse(updatedUser);
    }

    /**
     * Deletes a user from the system.
     *
     * @param id The ID of the user to delete
     * @throws UserNotFoundException if no user exists with the given ID
     */
    @Transactional
    public void deleteUser(Long id) {
        // Check if user exists before attempting deletion
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }

        // Delete the user
        userRepository.deleteById(id);
    }

    /**
     * Helper method to convert User entity to UserResponse DTO.
     * This prevents exposing the entity directly to the API layer.
     *
     * @param user The User entity to convert
     * @return UserResponse DTO with user data
     */
    private UserResponse mapToResponse(User user) {
        UserResponse response= new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setCreatedAt(user.getCreatedAt());
        return response;
    }
}

