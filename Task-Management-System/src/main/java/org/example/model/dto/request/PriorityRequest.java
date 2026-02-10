package org.example.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for creating or updating a Priority.
 * Contains the priority name and its numeric level.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriorityRequest {

    /**
     * Name of the priority level (e.g., Low, High)
     */
    private String name;

    /**
     * Numeric representation of priority urgency
     */
    private Integer level;
}

