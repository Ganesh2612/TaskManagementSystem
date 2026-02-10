package org.example.model.dto.request;

import org.example.model.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO specifically for updating only the status of a task.
 * This allows status updates without requiring all task information.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskStatusUpdateRequest {

    /**
     * New status to be set for the task
     */
    private TaskStatus status;
}

