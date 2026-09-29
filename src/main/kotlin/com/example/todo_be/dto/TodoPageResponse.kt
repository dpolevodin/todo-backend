package com.example.todo_be.dto

import com.example.todo_be.model.Todo

data class TodoPageResponse(
    val items: List<Todo>,
    val page: Int,
    val size: Int,
    val totalElements: Long,
    val totalPages: Int
)