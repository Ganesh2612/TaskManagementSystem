package org.example.service;

import org.example.exception.*;
import org.example.model.dto.request.TaskRequest;
import org.example.model.dto.request.TaskStatusUpdateRequest;
import org.example.model.dto.response.*;
import org.example.model.entity.*;
import org.example.model.enums.TaskStatus;
import org.example.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for Task-related business logic.
 * Contains the core business logic for task management including validation
 * of related entities (User, Category, Priority) before task operations.
 */
@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PriorityRepository priorityRepository;

    /**
     * Creates a new task in the system.
     * This method performs several validation steps:
     * 1. Validates that the assigned user exists
     * 2. Validates that the category exists
     * 3. Validates that the priority exists
     * 4. Creates and saves the task with PENDING status
     *
     * @param request TaskRequest containing task details and related entity IDs
     * @return TaskResponse with complete task information including user, category, and priority
     * @throws UserNotFoundException if the specified user doesn't exist
     * @throws CategoryNotFoundException if the specified category doesn't exist
     * @throws PriorityNotFoundException if the specified priority doesn't exist
     */
    @Transactional
    public TaskResponse createTask(TaskRequest request) {
        // Step 1: Validate user exists
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + request.getUserId()));

        // Step 2: Validate category exists
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + request.getCategoryId()));

        // Step 3: Validate priority exists
        Priority priority = priorityRepository.findById(request.getPriorityId())
                .orElseThrow(() -> new PriorityNotFoundException("Priority not found with id: " + request.getPriorityId()));

        // Step 4: Create new task with validated entities
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setUser(user);
        task.setCategory(category);
        task.setPriority(priority);
        task.setStatus(TaskStatus.PENDING); // Default status

        // Save task and return response
        Task savedTask = taskRepository.save(task);
        return mapToResponse(savedTask);
    }

    /**
     * Retrieves all tasks from the database.
     * Each task includes complete information about its user, category, and priority.
     *
     * @return List of TaskResponse containing all tasks with full details
     */
    public List<TaskResponse> getAllTasks() {
        // Fetch all tasks and convert each to TaskResponse with nested entities
        return taskRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a specific task by its ID.
     *
     * @param id The unique identifier of the task
     * @return TaskResponse with complete task information
     * @throws TaskNotFoundException if no task exists with the given ID
     */
    public TaskResponse getTaskById(Long id) {
        // Find task or throw exception
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));

        return mapToResponse(task);
    }

    /**
     * Updates an existing task's information.
     * All related entities (user, category, priority) are validated before update.
     *
     * @param id The ID of the task to update
     * @param request TaskRequest containing updated task details
     * @return TaskResponse with updated task information
     * @throws TaskNotFoundException if the task doesn't exist
     * @throws UserNotFoundException if the new user doesn't exist
     * @throws CategoryNotFoundException if the new category doesn't exist
     * @throws PriorityNotFoundException if the new priority doesn't exist
     */
    @Transactional
    public TaskResponse updateTask(Long id, TaskRequest request) {
        // Step 1: Validate task exists
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));

        // Step 2: Validate new user exists
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + request.getUserId()));

        // Step 3: Validate new category exists
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + request.getCategoryId()));

        // Step 4: Validate new priority exists
        Priority priority = priorityRepository.findById(request.getPriorityId())
                .orElseThrow(() -> new PriorityNotFoundException("Priority not found with id: " + request.getPriorityId()));

        // Step 5: Update task fields
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setUser(user);
        task.setCategory(category);
        task.setPriority(priority);

        // Save and return updated task
        Task updatedTask = taskRepository.save(task);
        return mapToResponse(updatedTask);
    }

    /**
     * Updates only the status of a task.
     * This is a lighter operation than full task update, used when only
     * the status needs to change (e.g., moving from PENDING to IN_PROGRESS).
     *
     * @param id The ID of the task to update
     * @param request TaskStatusUpdateRequest containing the new status
     * @return TaskResponse with updated task information
     * @throws TaskNotFoundException if the task doesn't exist
     */
    @Transactional
    public TaskResponse updateTaskStatus(Long id, TaskStatusUpdateRequest request) {
        // Step 1: Validate task exists
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));

        // Step 2: Update status only
        task.setStatus(request.getStatus());

        // Save and return updated task
        Task updatedTask = taskRepository.save(task);
        return mapToResponse(updatedTask);
    }

    /**
     * Deletes a task from the system.
     *
     * @param id The ID of the task to delete
     * @throws TaskNotFoundException if no task exists with the given ID
     */
    @Transactional
    public void deleteTask(Long id) {
        // Verify task exists before deletion
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException("Task not found with id: " + id);
        }

        taskRepository.deleteById(id);
    }

    /**
     * Converts Task entity to TaskResponse DTO with complete nested information.
     * This method builds a complete response including:
     * - Task details
     * - Complete user information
     * - Complete category information
     * - Complete priority information
     *
     * @param task The Task entity to convert
     * @return TaskResponse DTO with all nested information
     */
    private TaskResponse mapToResponse(Task task) {
        TaskResponse response= new TaskResponse();
        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setStatus(task.getStatus());
        response.setCreatedAt(task.getCreatedAt());
        response.setUpdatedAt(task.getUpdatedAt());

        // Map nested user information
        UserResponse userResponse= new UserResponse();
        userResponse.setId(task.getUser().getId());
        userResponse.setName(task.getUser().getName());
        userResponse.setEmail(task.getUser().getEmail());
        userResponse.setCreatedAt(task.getUser().getCreatedAt());
        response.setUser(userResponse);

        // Map nested category information
        CategoryResponse categoryResponse= new CategoryResponse();
        categoryResponse.setId(task.getCategory().getId());
        categoryResponse.setName(task.getCategory().getName());
        categoryResponse.setDescription(task.getCategory().getDescription());
        response.setCategory(categoryResponse);

        // Map nested priority information
        PriorityResponse priorityResponse= new PriorityResponse();
        priorityResponse.setId(task.getPriority().getId());
        priorityResponse.setName(task.getPriority().getName());
        priorityResponse.setLevel(task.getPriority().getLevel());
        response.setPriority(priorityResponse);

        return response;
    }
}

