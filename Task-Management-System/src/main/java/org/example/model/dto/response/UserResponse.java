package org.example.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO for returning User data to the client.
 * Contains all user information that should be exposed via the API.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    /**
     * Unique identifier of the user
     */
    private Long id;

    /**
     * Name of the user
     */
    private String name;

    /**
     * Email address of the user
     */
    private String email;

    /**
     * When the user was created
     */
    private LocalDateTime createdAt;
}

