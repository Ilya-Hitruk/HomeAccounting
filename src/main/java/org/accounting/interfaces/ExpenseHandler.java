package org.accounting.interfaces;

import java.time.LocalDate;

public interface ExpenseHandler {
    int addExpense(LocalDate date, double amount, String categoryName);
    int removeExpense(LocalDate date, double amount, String categoryName);
}
