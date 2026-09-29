package com.example.todo_be.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class TodoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var title: String = ""

    var completed: Boolean = false

    @Column(nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()

    var priority: Int = 0
}