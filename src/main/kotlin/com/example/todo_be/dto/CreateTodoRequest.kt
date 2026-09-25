package com.example.todo_be.dto

import jakarta.validation.constraints.NotBlank

data class CreateTodoRequest(
    @field:NotBlank
    val title: String,
)