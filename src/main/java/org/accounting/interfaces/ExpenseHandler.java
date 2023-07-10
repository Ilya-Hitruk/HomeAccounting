package org.accounting.interfaces;

import org.accounting.validity.ExecutionCode;

import java.time.LocalDate;

public interface ExpenseHandler {
    ExecutionCode addExpense(LocalDate date, double amount, String categoryName);
    ExecutionCode removeExpense(LocalDate date, double amount, String categoryName);
}
