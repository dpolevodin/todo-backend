package com.example.todo_be.controller

import com.example.todo_be.dto.CreateTodoRequest
import com.example.todo_be.dto.UpdateTodoRequest
import com.example.todo_be.model.ApiError
import com.example.todo_be.model.Todo
import com.example.todo_be.service.TodoService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Tag(
    name = "Todos",
    description = "Todo management api"
)
@RestController
class TodoController(
    private val todoService: TodoService
) {

    @GetMapping("/hello")
    fun hello(): String {
        return "Hello World!"
    }

    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Todo found"
            ),
            ApiResponse(
                responseCode = "404",
                description = "Todo not found",
                content = [
                    Content(
                        schema = Schema(implementation = ApiError::class)
                    )
                ]
            )
        ]
    )
    @GetMapping("/api/todos/{id}")
    fun getTodo(
        @PathVariable("id") id: Long
    ): Todo {
        return todoService.getTodo(id)
    }

    @Operation(
        summary = "Get all todos",
        description = "Returns all todos"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Todos successfully retrieved"
            )
        ]
    )
    @GetMapping("/api/todos")
    fun getTodos(): List<Todo> {
        return todoService.getTodos()
    }

    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                description = "Todo successfully created"
            ),
            ApiResponse(
                responseCode = "400",
                description = "Validation failed",
                content = [
                    Content(
                        schema = Schema(implementation = ApiError::class)
                    )
                ]
            )
        ]
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/todos")
    fun createTodo(@Valid @RequestBody request: CreateTodoRequest): Todo {
        return todoService.createTodo(request)
    }

    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Todo successfully updated"
            ),
            ApiResponse(
                responseCode = "404",
                description = "Todo not found",
                content = [Content(schema = Schema(implementation = ApiError::class))]
            )
        ]
    )
    @PatchMapping("/api/todos/{id}")
    fun updateTodo(
        @PathVariable("id") id: Long,
        @Valid @RequestBody request: UpdateTodoRequest
    ): Todo {
        return todoService.updateTodo(id, request)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "204",
                description = "Todo successfully deleted"
            ),
            ApiResponse(
                responseCode = "404",
                description = "Todo not found",
                content = [Content(schema = Schema(implementation = ApiError::class))]
            )
        ]
    )
    @DeleteMapping("/api/todos/{id}")
    fun deleteTodo(
        @PathVariable("id") id: Long
    ) {
        todoService.deleteTodo(id)
    }
}