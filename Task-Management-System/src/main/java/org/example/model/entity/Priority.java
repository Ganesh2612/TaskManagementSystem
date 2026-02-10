package org.example.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing the Priority level of tasks.
 * Priorities help in determining which tasks should be completed first.
 */
@Entity
@Table(name = "priorities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Priority {

    /**
     * Unique identifier for the priority
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the priority level (e.g., Low, Medium, High, Critical)
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * Numeric level representing the urgency (higher number = higher priority)
     * Example: 1 = Low, 2 = Medium, 3 = High, 4 = Critical
     */
    private Integer level;
}

