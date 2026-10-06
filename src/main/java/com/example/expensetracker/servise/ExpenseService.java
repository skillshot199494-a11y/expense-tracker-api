package com.example.expensetracker.servise;

import com.example.expensetracker.dto.ExpenseRequest;
import com.example.expensetracker.dto.ExpenseResponse;
import com.example.expensetracker.model.Expense;
import com.example.expensetracker.model.ExpenseCategory;
import com.example.expensetracker.repository.ExpenseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<ExpenseResponse> getAllExpenses() {
        return expenseRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private Expense getExpenseEntityById(Long id) {
        return expenseRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Expense not found"));
    }

    public ExpenseResponse getExpenseById(Long id) {
        return toResponse(getExpenseEntityById(id));
    }

    public ExpenseResponse createExpense(ExpenseRequest request) {
        Expense expense = toEntity(request);
        Expense savedExpense = expenseRepository.save(expense);

        return toResponse(savedExpense);
    }

    public ExpenseResponse updateExpense(Long id, ExpenseRequest request) {
        Expense existingExpense = getExpenseEntityById(id);

        existingExpense.setTitle(request.getTitle());
        existingExpense.setAmount(request.getAmount());
        existingExpense.setCategory(request.getCategory());
        existingExpense.setExpenseDate(request.getExpenseDate());

        Expense updatedExpense = expenseRepository.save(existingExpense);

        return toResponse(updatedExpense);
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    public List<ExpenseResponse> getExpensesByCategory(ExpenseCategory category) {
        return expenseRepository.findByCategory(category)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<ExpenseResponse> getExpensesByDateRange(LocalDate from, LocalDate to) {
        return expenseRepository.findByExpenseDateBetween(from, to)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<ExpenseResponse> getExpensesByCategoryAndDateRange(
            ExpenseCategory category,
            LocalDate from,
            LocalDate to
    ) {
        return expenseRepository.findByCategoryAndExpenseDateBetween(category, from, to)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public BigDecimal getTotalExpenses() {
        return expenseRepository.findAll()
                .stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Page<ExpenseResponse> getExpenses(Pageable pageable) {
        return expenseRepository.findAll(pageable)
                .map(this::toResponse);
    }

    private ExpenseResponse toResponse(Expense expense) {
        ExpenseResponse response = new ExpenseResponse();

        response.setId(expense.getId());
        response.setTitle(expense.getTitle());
        response.setAmount(expense.getAmount());
        response.setCategory(expense.getCategory());
        response.setExpenseDate(expense.getExpenseDate());
        response.setCreatedAt(expense.getCreatedAt());
        response.setUpdatedAt(expense.getUpdatedAt());

        return response;
    }

    private Expense toEntity(ExpenseRequest request) {
        Expense expense = new Expense();

        expense.setTitle(request.getTitle());
        expense.setAmount(request.getAmount());
        expense.setCategory(request.getCategory());
        expense.setExpenseDate(request.getExpenseDate());

        return expense;
    }
}
