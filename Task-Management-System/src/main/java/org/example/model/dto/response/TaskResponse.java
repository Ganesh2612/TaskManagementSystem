package org.example.model.dto.response;

import org.example.model.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO for returning Task data to the client.
 * Contains complete task information including nested user, category, and priority data.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {

    /**
     * Unique identifier of the task
     */
    private Long id;

    /**
     * Title of the task
     */
    private String title;

    /**
     * Detailed description of the task
     */
    private String description;

    /**
     * Current status of the task
     */
    private TaskStatus status;

    /**
     * Complete user information for the assigned user
     */
    private UserResponse user;

    /**
     * Complete category information
     */
    private CategoryResponse category;

    /**
     * Complete priority information
     */
    private PriorityResponse priority;

    /**
     * When the task was created
     */
    private LocalDateTime createdAt;

    /**
     * When the task was last updated
     */
    private LocalDateTime updatedAt;
}

