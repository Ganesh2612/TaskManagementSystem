package org.example.exception;

/**
 * Custom exception thrown when a requested user cannot be found in the database.
 * This exception is caught by the GlobalExceptionHandler and converted to an HTTP 404 response.
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Creates a new UserNotFoundException with the specified error message.
     *
     * @param message Detailed message explaining why the user was not found
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}

