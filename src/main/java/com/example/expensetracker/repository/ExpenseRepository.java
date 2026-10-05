package com.example.expensetracker.repository;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.model.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByCategory(ExpenseCategory category);

    List<Expense> findByExpenseDateBetween(LocalDate from, LocalDate to);

    List<Expense> findByCategoryAndExpenseDateBetween(
            ExpenseCategory category,
            LocalDate from,
            LocalDate to
    );
}
