package com.example.todo_be.dto

data class UpdateTodoRequest(
    val title: String? = null,
    val completed: Boolean? = null,
)