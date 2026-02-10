package org.example.controller;

import org.example.model.dto.request.CategoryRequest;
import org.example.model.dto.response.CategoryResponse;
import org.example.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Category management endpoints.
 * Handles all HTTP requests related to task categories.
 */
@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Tag(name = "Category Management", description = "APIs for managing task categories")
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * Creates a new category.
     * Endpoint: POST /api/categories
     *
     * @param request CategoryRequest containing category name and description
     * @return ResponseEntity with CategoryResponse and HTTP 201 (CREATED) status
     */
    @PostMapping
    @Operation(summary = "Create a new category", description = "Creates a new category for organizing tasks")
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest request) {
        CategoryResponse response= categoryService.createCategory(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Retrieves all categories.
     * Endpoint: GET /api/categories
     *
     * @return ResponseEntity with List of CategoryResponse and HTTP 200 (OK) status
     */
    @GetMapping
    @Operation(summary = "Get all categories", description = "Retrieves a list of all task categories")
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    /**
     * Retrieves a specific category by ID.
     * Endpoint: GET /api/categories/{categoryId}
     *
     * @param categoryId The unique identifier of the category
     * @return ResponseEntity with CategoryResponse and HTTP 200 (OK) status
     */
    @GetMapping("/{categoryId}")
    @Operation(summary = "Get category by ID", description = "Retrieves a specific category by its unique identifier")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long categoryId) {
        CategoryResponse response= categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(response);
    }

    /**
     * Updates an existing category.
     * Endpoint: PUT /api/categories/{categoryId}
     *
     * @param categoryId The ID of the category to update
     * @param request CategoryRequest containing updated category details
     * @return ResponseEntity with updated CategoryResponse and HTTP 200 (OK) status
     */
    @PutMapping("/{categoryId}")
    @Operation(summary = "Update category", description = "Updates an existing category's information")
    public ResponseEntity<CategoryResponse> updateCategory(
            @PathVariable Long categoryId,
            @RequestBody CategoryRequest request) {
        CategoryResponse response= categoryService.updateCategory(categoryId, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Deletes a category.
     * Endpoint: DELETE /api/categories/{categoryId}
     *
     * @param categoryId The ID of the category to delete
     * @return ResponseEntity with HTTP 204 (NO_CONTENT) status
     */
    @DeleteMapping("/{categoryId}")
    @Operation(summary = "Delete category", description = "Removes a category from the system")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}

