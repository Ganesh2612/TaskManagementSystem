package org.example.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a Category for organizing tasks.
 * Categories help in grouping similar tasks together (e.g., Development, Testing, Documentation).
 */
@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    /**
     * Unique identifier for the category
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the category (must be unique)
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * Detailed description of what this category represents
     */
    @Column(length = 500)
    private String description;
}

