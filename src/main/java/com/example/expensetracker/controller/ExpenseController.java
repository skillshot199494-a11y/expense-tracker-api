package com.example.expensetracker.controller;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.model.ExpenseCategory;
import com.example.expensetracker.servise.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public List<Expense> getAllExpenses(
            @RequestParam(required = false)ExpenseCategory category,
            @RequestParam(required = false)LocalDate from,
            @RequestParam(required = false)LocalDate to
            ) {

        if (category != null && from != null && to != null) {
            return expenseService.getExpensesByCategoryAndDateRange(category, from, to);
        }

        if (category != null) {
            return expenseService.getExpensesByCategory(category);
        }

        if (from != null && to != null) {
            return expenseService.getExpensesByDateRange(from, to);
        }

        return expenseService.getAllExpenses();
    }

    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable Long id) {
        return expenseService.getExpenseById(id);
    }

    @PostMapping
    public Expense createExpense(@Valid @RequestBody Expense expense) {
        return expenseService.createExpense(expense);
    }

    @PutMapping("/{id}")
    public Expense updateExpense(
            @PathVariable Long id,
            @Valid @RequestBody Expense expense
    ) {
        return expenseService.updateExpense(id, expense);
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
    }

    @GetMapping("/total")
    public BigDecimal getTotalExpenses() {
        return expenseService.getTotalExpenses();
    }
}
