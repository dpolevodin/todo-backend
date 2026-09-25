package com.example.todo_be.exception

import com.example.todo_be.model.ApiError
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.server.ResponseStatusException

@RestControllerAdvice
class GlobalExceptionHandler {


    @ExceptionHandler(ResponseStatusException::class)
    fun handleResponseStatusException(
        exception: ResponseStatusException
    ): ResponseEntity<ApiError> {
        val status = exception.statusCode

        return ResponseEntity
            .status(status)
            .body(
                ApiError(
                    status = status.value(),
                    message = exception.reason ?: "Request failed"
                )
            )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(
        exception: MethodArgumentNotValidException
    ): ResponseEntity<ApiError> {
        val errors = exception.bindingResult.fieldErrors
            .associate { error ->
                error.field to (error.defaultMessage ?: "Invalid value")
            }

        return ResponseEntity
            .badRequest()
            .body(
                ApiError(
                    status = HttpStatus.BAD_REQUEST.value(),
                    message = "Validation failed",
                    errors = errors
                )
            )
    }

}