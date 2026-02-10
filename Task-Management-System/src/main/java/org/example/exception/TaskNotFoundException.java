package org.example.exception;


/**
 * Custom exception thrown when a requested task cannot be found.
 * Results in an HTTP 404 response.
 */
public class TaskNotFoundException extends RuntimeException {

    /**
     * Creates a new TaskNotFoundException with the specified error message.
     *
     * @param message Detailed message about the missing task
     */
    public TaskNotFoundException(String message) {
        super(message);
    }
}

