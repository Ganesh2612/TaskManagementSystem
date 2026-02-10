package org.example.repository;

import org.example.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Category entity.
 * Handles all database operations related to categories.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Inherits standard CRUD methods from JpaRepository
}

