package com.example.todo_be.controller

import com.example.todo_be.dto.CreateTodoRequest
import com.example.todo_be.dto.UpdateTodoRequest
import com.example.todo_be.model.Todo
import com.example.todo_be.service.TodoService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class TodoController(
    private val todoService: TodoService
) {

    @GetMapping("/hello")
    fun hello(): String {
        return "Hello World!"
    }

    @GetMapping("/api/todos/{id}")
    fun getTodo(
        @PathVariable("id") id: Long
    ): Todo? {
        return todoService.getTodo(id)
    }

    @GetMapping("/api/todos")
    fun getTodos(): List<Todo> {
        return todoService.getTodos()
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/todos")
    fun createTodo(@Valid @RequestBody request: CreateTodoRequest): Todo {
        return todoService.createTodo(request)
    }

    @PatchMapping("/api/todos/{id}")
    fun updateTodo(
        @PathVariable("id") id: Long,
        @Valid @RequestBody request: UpdateTodoRequest
    ): Todo {
        return todoService.updateTodo(id, request)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("api/todos/{id}")
    fun deleteTodo(
        @PathVariable("id") id: Long
    ) {
        todoService.deleteTodo(id)
    }
}