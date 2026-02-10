package org.example.repository;

import org.example.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Task entity.
 * Provides database access methods for task operations.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // JpaRepository automatically provides methods for CRUD operations
}

