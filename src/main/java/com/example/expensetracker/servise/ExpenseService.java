package com.example.expensetracker.servise;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.model.ExpenseCategory;
import com.example.expensetracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
    }

    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public Expense updateExpense(Long id, Expense expense) {
        Expense existingExpense = getExpenseById(id);

        existingExpense.setTitle(expense.getTitle());
        existingExpense.setAmount(expense.getAmount());
        existingExpense.setCategory(expense.getCategory());
        existingExpense.setExpenseDate(expense.getExpenseDate());

        return expenseRepository.save(existingExpense);
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    public List<Expense> getExpensesByCategory(ExpenseCategory category) {
        return expenseRepository.findByCategory(category);
    }

    public List<Expense> getExpensesByDateRange(LocalDate from, LocalDate to) {
        return expenseRepository.findByExpenseDateBetween(from, to);
    }

    public List<Expense> getExpensesByCategoryAndDateRange(
            ExpenseCategory category,
            LocalDate from,
            LocalDate to
    ) {
        return expenseRepository.findByCategoryAndExpenseDateBetween(category,
                from,
                to
        );
    }
}
