package org.example.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for returning Priority data to the client.
 * Contains all priority information needed for display.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriorityResponse {

    /**
     * Unique identifier of the priority
     */
    private Long id;

    /**
     * Name of the priority level
     */
    private String name;

    /**
     * Numeric level of the priority
     */
    private Integer level;
}

