package org.example.exception;


/**
 * Custom exception thrown when a requested category cannot be found.
 * Results in an HTTP 404 response to the client.
 */
public class CategoryNotFoundException extends RuntimeException {

    /**
     * Creates a new CategoryNotFoundException with the specified error message.
     *
     * @param message Detailed message about the missing category
     */
    public CategoryNotFoundException(String message) {
        super(message);
    }
}

