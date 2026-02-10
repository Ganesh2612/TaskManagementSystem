package org.example.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for returning Category data to the client.
 * Contains all category information needed for display.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {

    /**
     * Unique identifier of the category
     */
    private Long id;

    /**
     * Name of the category
     */
    private String name;

    /**
     * Description of the category
     */
    private String description;
}

