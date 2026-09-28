package com.example.todo_be.exception

class TodoNotFoundException(id: Long) : RuntimeException("Todo with id $id not found")