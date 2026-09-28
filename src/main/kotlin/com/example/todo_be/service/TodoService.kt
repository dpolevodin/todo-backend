package com.example.todo_be.service

import com.example.todo_be.dto.CreateTodoRequest
import com.example.todo_be.dto.UpdateTodoRequest
import com.example.todo_be.exception.TodoNotFoundException
import com.example.todo_be.model.Todo
import com.example.todo_be.repository.TodoRepository
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service

@Service
class TodoService(
    private val todoRepository: TodoRepository
) {

    fun getTodo(id: Long): Todo {
        return todoRepository.findById(id) ?: throw TodoNotFoundException(id)
    }

    fun getTodos(): List<Todo> {
        return todoRepository.findAll()
    }

    fun createTodo(request: CreateTodoRequest): Todo {
        val todo = Todo(
            id = todoRepository.generateId(),
            title = request.title,
            completed = false
        )
        return todoRepository.save(todo)
    }

    fun updateTodo(id: Long, request: UpdateTodoRequest): Todo {
        val todo = todoRepository.findById(id)
            ?: throw TodoNotFoundException(id)

        val updatedTodo = Todo(
            id = todo.id,
            title = request.title ?: todo.title,
            completed = request.completed ?: todo.completed
        )

        return todoRepository.save(updatedTodo)
    }

    fun deleteTodo(id: Long) {
        todoRepository.deleteById(id) ?: throw TodoNotFoundException(id)
    }

    @PostConstruct
    fun init() {
        todoRepository.save(
            Todo(
                id = todoRepository.generateId(),
                title = "Learn Kotlin init task",
                completed = false,
            )
        )

        todoRepository.save(
            Todo(
                id = todoRepository.generateId(),
                title = "Learn Spring!",
                completed = false
            )
        )

        todoRepository.save(
            Todo(
                id = todoRepository.generateId(),
                title = "Build TODO Api!",
                completed = false,
            )
        )
    }
}

