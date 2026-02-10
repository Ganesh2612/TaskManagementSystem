package org.example.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entity class representing a User in the system.
 * Users can be assigned to tasks and are responsible for completing them.
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /**
     * Unique identifier for the user
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Full name of the user
     */
    @Column(nullable = false)
    private String name;

    /**
     * Email address of the user (must be unique)
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Timestamp when the user was created
     */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * Automatically sets the creation timestamp before persisting the entity.
     * This method is called by JPA before the entity is saved to the database.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}

