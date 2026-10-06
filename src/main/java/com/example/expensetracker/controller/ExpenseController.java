package com.example.expensetracker.controller;

import com.example.expensetracker.dto.ExpenseRequest;
import com.example.expensetracker.dto.ExpenseResponse;
import com.example.expensetracker.model.ExpenseCategory;
import com.example.expensetracker.servise.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<ExpenseResponse> getAllExpenses(
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
    public ExpenseResponse getExpenseById(@PathVariable Long id) {
        return expenseService.getExpenseById(id);
    }

    @PostMapping
    public ExpenseResponse createExpense(@Valid @RequestBody ExpenseRequest request) {
        return expenseService.createExpense(request);
    }

    @PutMapping("/{id}")
    public ExpenseResponse updateExpense(
            @PathVariable Long id,
            @Valid @RequestBody ExpenseRequest request
    ) {
        return expenseService.updateExpense(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
    }

    @GetMapping("/total")
    public BigDecimal getTotalExpenses() {
        return expenseService.getTotalExpenses();
    }

    @GetMapping("/page")
    public Page<ExpenseResponse> getExpenses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "expenseData") String sort,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Sort.Direction sortDirection = Sort.Direction.fromString(direction.trim());

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sortDirection, sort)
        );

        return expenseService.getExpenses(pageable);
    }
}
