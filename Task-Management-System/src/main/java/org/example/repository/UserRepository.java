package org.example.repository;

import org.example.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for User entity.
 * Provides CRUD operations and database access for User entities.
 * JpaRepository provides built-in methods like save(), findById(), findAll(), delete(), etc.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository provides all basic CRUD operations
    // No custom queries needed at this stage
}

