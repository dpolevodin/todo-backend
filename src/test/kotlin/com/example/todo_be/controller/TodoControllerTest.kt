package com.example.todo_be.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `GET todos returns 200`() {
        mockMvc.perform(get("/api/todos"))
            .andExpect(status().isOk)
    }
}