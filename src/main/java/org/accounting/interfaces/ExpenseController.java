package org.accounting.interfaces;

import org.accounting.exceptions.CategoryNotFoundException;
import org.accounting.exceptions.ExpenseNotFoundException;

import java.time.LocalDate;

public interface ExpenseController {
    void addExpense(LocalDate date, double amount, String categoryName) throws CategoryNotFoundException;
    void removeExpense(LocalDate date, double amount, String categoryName) throws CategoryNotFoundException, ExpenseNotFoundException;
}
