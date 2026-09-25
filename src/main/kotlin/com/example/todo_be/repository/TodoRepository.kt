package com.example.todo_be.repository

import com.example.todo_be.model.Todo
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

@Repository
class TodoRepository {

    private val todos = ConcurrentHashMap<Long, Todo>()

    private val nextId = AtomicLong(0)

    fun findById(id: Long): Todo? {
        return todos[id]
    }

    fun findAll(): List<Todo> {
        return todos.values.toList()
    }

    fun save(todo: Todo): Todo {
        todos[todo.id] = todo
        return todo
    }

    fun deleteById(id: Long): Todo? {
        return todos.remove(id)
    }

    fun generateId(): Long {
        return nextId.incrementAndGet()
    }
}