package com.example.todo_be.model

data class ApiError(
    val status: Int,
    val message: String,
    val errors: Map<String, String>? = null
)