package org.example.repository;

import org.example.model.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Priority entity.
 * Manages database access for priority levels.
 */
@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {
    // Uses JpaRepository's built-in methods for data access
}

