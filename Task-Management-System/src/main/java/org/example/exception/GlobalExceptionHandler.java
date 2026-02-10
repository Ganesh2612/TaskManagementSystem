package org.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/**
 * Global exception handler for the entire application.
 * This class intercepts all exceptions thrown by controllers and services,
 * converts them to appropriate HTTP responses, and ensures consistent error formatting.
 *
 * @RestControllerAdvice makes this handler apply to all controllers in the application.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles UserNotFoundException.
     * Called automatically when a UserNotFoundException is thrown anywhere in the application.
     *
     * @param ex The UserNotFoundException that was thrown
     * @param request The web request that caused the exception
     * @return ResponseEntity with ErrorResponse and HTTP 404 (NOT_FOUND) status
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(
            UserNotFoundException ex, WebRequest request) {

        // Build structured error response
        ErrorResponse errorResponse= new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles CategoryNotFoundException.
     * Returns HTTP 404 with detailed error information.
     *
     * @param ex The CategoryNotFoundException that was thrown
     * @param request The web request that caused the exception
     * @return ResponseEntity with ErrorResponse and HTTP 404 status
     */
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotFoundException(
            CategoryNotFoundException ex, WebRequest request) {

        ErrorResponse errorResponse= new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles PriorityNotFoundException.
     * Returns HTTP 404 when a priority cannot be found.
     *
     * @param ex The PriorityNotFoundException that was thrown
     * @param request The web request that caused the exception
     * @return ResponseEntity with ErrorResponse and HTTP 404 status
     */
    @ExceptionHandler(PriorityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePriorityNotFoundException(
            PriorityNotFoundException ex, WebRequest request) {

        ErrorResponse errorResponse= new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles TaskNotFoundException.
     * Returns HTTP 404 when a task cannot be found.
     *
     * @param ex The TaskNotFoundException that was thrown
     * @param request The web request that caused the exception
     * @return ResponseEntity with ErrorResponse and HTTP 404 status
     */
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTaskNotFoundException(
            TaskNotFoundException ex, WebRequest request) {

        ErrorResponse errorResponse= new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles all other unexpected exceptions.
     * This is a catch-all handler for any exception not specifically handled above.
     * Returns HTTP 500 (Internal Server Error) for unexpected errors.
     *
     * @param ex The Exception that was thrown
     * @param request The web request that caused the exception
     * @return ResponseEntity with ErrorResponse and HTTP 500 status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex, WebRequest request) {

        ErrorResponse errorResponse= new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

