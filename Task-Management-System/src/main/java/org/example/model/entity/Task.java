package org.example.model.entity;

import org.example.model.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entity class representing a Task in the system.
 * Tasks are the core of the application and contain all information about work to be done.
 * Each task is associated with a User, Category, and Priority.
 */
@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    /**
     * Unique identifier for the task
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Short title describing the task
     */
    @Column(nullable = false)
    private String title;

    /**
     * Detailed description of what needs to be done
     */
    @Column(length = 1000)
    private String description;

    /**
     * Current status of the task (PENDING, IN_PROGRESS, DONE)
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    /**
     * The user assigned to this task.
     * Many tasks can be assigned to one user (ManyToOne relationship).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * The category this task belongs to.
     * Many tasks can belong to one category (ManyToOne relationship).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    /**
     * The priority level of this task.
     * Many tasks can have the same priority (ManyToOne relationship).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "priority_id", nullable = false)
    private Priority priority;

    /**
     * Timestamp when the task was created
     */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * Timestamp when the task was last updated
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * Automatically sets creation and update timestamps before persisting.
     * Sets default status to PENDING if not specified.
     * This method is called by JPA before the entity is saved for the first time.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) {
            status = TaskStatus.PENDING;
        }
    }

    /**
     * Automatically updates the updatedAt timestamp before updating the entity.
     * This method is called by JPA before the entity is updated.
     */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

