package org.example.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for creating or updating a Category.
 * Contains only the data needed to create or modify a category.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {

    /**
     * Name of the category
     */
    private String name;

    /**
     * Description of what this category represents
     */
    private String description;
}

