package com.example.expensetracker.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExpenseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnAllExpenses() throws Exception {
        mockMvc.perform(get("/api/expenses"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateExpense() throws Exception {
        String requestBody = """
                {
                "title": "Launch",
                "amount": 2500.00,
                "category": "FOOD",
                "expenseDate": "2026-10-05"
                }
                """;

        mockMvc.perform(post("/api/expenses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnBadRequestWhenTitleIsBlank() throws Exception {
        String requestBody = """
                {
                "title": "",
                "amount": 2500.00,
                "category": "FOOD",
                "expenseDate": "2026-10-05"
                }
                """;

        mockMvc.perform(post("/api/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenAmountIsZero() throws Exception {
        String requestBody = """
                {
                "title": "Launch",
                "amount": 0,
                "category": "FOOD",
                "expenseDate": "2026-10-05"
                }
                """;

        mockMvc.perform(post("/api/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }
}
