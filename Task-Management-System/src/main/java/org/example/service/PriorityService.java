package org.example.service;

import org.example.exception.PriorityNotFoundException;
import org.example.model.dto.request.PriorityRequest;
import org.example.model.dto.response.PriorityResponse;
import org.example.model.entity.Priority;
import org.example.repository.PriorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for Priority-related business logic.
 * Manages operations related to task priority levels.
 */
@Service
@RequiredArgsConstructor
public class PriorityService {

    private final PriorityRepository priorityRepository;

    /**
     * Creates a new priority level in the system.
     *
     * @param request PriorityRequest containing priority name and level
     * @return PriorityResponse with the created priority's information
     */
    @Transactional
    public PriorityResponse createPriority(PriorityRequest request) {
        // Create new Priority entity
        Priority priority = new Priority();
        priority.setName(request.getName());
        priority.setLevel(request.getLevel());

        // Save and return response
        Priority savedPriority = priorityRepository.save(priority);
        return mapToResponse(savedPriority);
    }

    /**
     * Retrieves all priority levels from the database.
     *
     * @return List of PriorityResponse containing all priorities
     */
    public List<PriorityResponse> getAllPriorities() {
        // Fetch all priorities and convert to response DTOs
        return priorityRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a specific priority by its ID.
     *
     * @param id The unique identifier of the priority
     * @return PriorityResponse with the priority's information
     * @throws PriorityNotFoundException if no priority exists with the given ID
     */
    public PriorityResponse getPriorityById(Long id) {
        // Find priority or throw exception
        Priority priority = priorityRepository.findById(id)
                .orElseThrow(() -> new PriorityNotFoundException("Priority not found with id: " + id));

        return mapToResponse(priority);
    }

    /**
     * Updates an existing priority's information.
     *
     * @param id The ID of the priority to update
     * @param request PriorityRequest containing updated priority details
     * @return PriorityResponse with updated priority information
     * @throws PriorityNotFoundException if no priority exists with the given ID
     */
    @Transactional
    public PriorityResponse updatePriority(Long id, PriorityRequest request) {
        // Find existing priority
        Priority priority = priorityRepository.findById(id)
                .orElseThrow(() -> new PriorityNotFoundException("Priority not found with id: " + id));

        // Update fields
        priority.setName(request.getName());
        priority.setLevel(request.getLevel());

        // Save and return updated priority
        Priority updatedPriority = priorityRepository.save(priority);
        return mapToResponse(updatedPriority);
    }

    /**
     * Deletes a priority from the system.
     *
     * @param id The ID of the priority to delete
     * @throws PriorityNotFoundException if no priority exists with the given ID
     */
    @Transactional
    public void deletePriority(Long id) {
        // Verify priority exists before deletion
        if (!priorityRepository.existsById(id)) {
            throw new PriorityNotFoundException("Priority not found with id: " + id);
        }

        priorityRepository.deleteById(id);
    }

    /**
     * Converts Priority entity to PriorityResponse DTO.
     *
     * @param priority The Priority entity to convert
     * @return PriorityResponse DTO
     */
    private PriorityResponse mapToResponse(Priority priority) {
        PriorityResponse response= new PriorityResponse();
        response.setId(priority.getId());
        response.setName(priority.getName());
        response.setLevel(priority.getLevel());
        return response;
    }
}

