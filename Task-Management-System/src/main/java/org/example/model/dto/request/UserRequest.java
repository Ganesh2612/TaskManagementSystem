package org.example.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) for creating or updating a User.
 * This class is used to transfer user data from the client to the server.
 * Using DTOs prevents direct exposure of entity classes to the API layer.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    /**
     * Full name of the user
     */
    private String name;

    /**
     * Email address of the user
     */
    private String email;
}

