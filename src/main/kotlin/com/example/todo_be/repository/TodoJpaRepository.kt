package com.example.todo_be.repository

import com.example.todo_be.model.TodoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TodoJpaRepository : JpaRepository<TodoEntity, Long>