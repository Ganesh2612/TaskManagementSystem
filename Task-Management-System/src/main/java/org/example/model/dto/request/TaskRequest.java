package org.example.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for creating or updating a Task.
 * Contains all necessary information to create a new task including
 * references to the user, category, and priority by their IDs.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest {

    /**
     * Title of the task
     */
    private String title;

    /**
     * Detailed description of the task
     */
    private String description;

    /**
     * ID of the user to whom this task is assigned
     */
    private Long userId;

    /**
     * ID of the category this task belongs to
     */
    private Long categoryId;

    /**
     * ID of the priority level for this task
     */
    private Long priorityId;
}

