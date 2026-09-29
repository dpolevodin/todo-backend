package com.example.todo_be.service

import com.example.todo_be.dto.CreateTodoRequest
import com.example.todo_be.dto.TodoPageResponse
import com.example.todo_be.dto.UpdateTodoRequest
import com.example.todo_be.exception.TodoNotFoundException
import com.example.todo_be.model.Todo
import com.example.todo_be.model.TodoEntity
import com.example.todo_be.repository.TodoJpaRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class TodoService(
    private val todoJpaRepository: TodoJpaRepository
) {
    private val allowedSortFields = setOf(
        "title", "completed", "priority", "createdAt"
    )

    fun getTodo(id: Long): Todo {
        val entity = todoJpaRepository.findById(id)
            .orElseThrow { TodoNotFoundException(id) }

        return entity.toTodo()
    }

    fun getTodos(pageable: Pageable): TodoPageResponse {
        pageable.sort.forEach { order ->
            if (order.property !in allowedSortFields) {
                throw IllegalArgumentException("${order.property} is not allowed")
            }
        }
        val page = todoJpaRepository.findAll(pageable).map { it.toTodo() }

        return TodoPageResponse(
            items = page.content,
            page = page.number,
            size = page.size,
            totalElements = page.totalElements,
            totalPages = page.totalPages,
        )
    }

    @Transactional
    fun createTodo(request: CreateTodoRequest): Todo {
        val entity = TodoEntity()

        entity.title = request.title
        entity.completed = false
        entity.priority = request.priority

        val savedEntity = todoJpaRepository.save(entity)

        return savedEntity.toTodo()
    }

    @Transactional
    fun updateTodo(id: Long, request: UpdateTodoRequest): Todo {
        val entity = todoJpaRepository.findById(id)
            .orElseThrow { TodoNotFoundException(id) }

        entity.title = request.title ?: entity.title
        entity.completed = request.completed ?: entity.completed
        entity.priority = request.priority ?: entity.priority

        return entity.toTodo()
    }

    @Transactional
    fun deleteTodo(id: Long) {
        if (!todoJpaRepository.existsById(id)) {
            throw TodoNotFoundException(id)
        }

        todoJpaRepository.deleteById(id)
    }

    private fun TodoEntity.toTodo(): Todo {
        return Todo(
            id = this.id!!,
            title = this.title,
            completed = this.completed,
            priority = this.priority,
        )
    }
}


