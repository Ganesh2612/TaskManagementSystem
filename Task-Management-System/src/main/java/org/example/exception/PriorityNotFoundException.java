package org.example.exception;


/**
 * Custom exception thrown when a requested priority level cannot be found.
 * Converted to HTTP 404 by the global exception handler.
 */
public class PriorityNotFoundException extends RuntimeException {

    /**
     * Creates a new PriorityNotFoundException with the specified error message.
     *
     * @param message Detailed message about the missing priority
     */
    public PriorityNotFoundException(String message) {
        super(message);
    }
}

