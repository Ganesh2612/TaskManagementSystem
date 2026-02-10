package org.example.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Standard error response structure returned to clients when exceptions occur.
 * Provides consistent error information across all API endpoints.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    /**
     * Timestamp when the error occurred
     */
    private LocalDateTime timestamp;

    /**
     * HTTP status code (e.g., 404, 500)
     */
    private int status;

    /**
     * Brief error category (e.g., "Not Found", "Internal Server Error")
     */
    private String error;

    /**
     * Detailed error message explaining what went wrong
     */
    private String message;

    /**
     * The API endpoint path where the error occurred
     */
    private String path;
}

