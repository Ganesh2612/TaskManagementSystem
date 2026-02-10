package org.example.service;

import org.example.exception.CategoryNotFoundException;
import org.example.model.dto.request.CategoryRequest;
import org.example.model.dto.response.CategoryResponse;
import org.example.model.entity.Category;
import org.example.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for Category-related business logic.
 * Handles all operations related to task categories.
 */
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * Creates a new category in the system.
     *
     * @param request CategoryRequest containing category name and description
     * @return CategoryResponse with the created category's information
     */
    @Transactional
    public CategoryResponse createCategory(CategoryRequest request) {
        // Create new Category entity
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());

        // Save to database and return response
        Category savedCategory = categoryRepository.save(category);
        return mapToResponse(savedCategory);
    }

    /**
     * Retrieves all categories from the database.
     *
     * @return List of CategoryResponse containing all categories
     */
    public List<CategoryResponse> getAllCategories() {
        // Fetch all categories and convert to response DTOs
        return categoryRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a specific category by its ID.
     *
     * @param id The unique identifier of the category
     * @return CategoryResponse with the category's information
     * @throws CategoryNotFoundException if no category exists with the given ID
     */
    public CategoryResponse getCategoryById(Long id) {
        // Find category or throw exception if not found
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));

        return mapToResponse(category);
    }

    /**
     * Updates an existing category's information.
     *
     * @param id The ID of the category to update
     * @param request CategoryRequest containing updated category details
     * @return CategoryResponse with updated category information
     * @throws CategoryNotFoundException if no category exists with the given ID
     */
    @Transactional
    public CategoryResponse updateCategory(Long id, CategoryRequest request) {
        // Find existing category
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));

        // Update fields
        category.setName(request.getName());
        category.setDescription(request.getDescription());

        // Save and return updated category
        Category updatedCategory = categoryRepository.save(category);
        return mapToResponse(updatedCategory);
    }

    /**
     * Deletes a category from the system.
     *
     * @param id The ID of the category to delete
     * @throws CategoryNotFoundException if no category exists with the given ID
     */
    @Transactional
    public void deleteCategory(Long id) {
        // Verify category exists before deletion
        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException("Category not found with id: " + id);
        }

        categoryRepository.deleteById(id);
    }

    /**
     * Converts Category entity to CategoryResponse DTO.
     *
     * @param category The Category entity to convert
     * @return CategoryResponse DTO
     */
    private CategoryResponse mapToResponse(Category category) {
        CategoryResponse response= new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        response.setDescription(category.getDescription());
        return response;
    }
}

