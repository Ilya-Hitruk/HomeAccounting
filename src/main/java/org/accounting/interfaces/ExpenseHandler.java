package org.accounting.interfaces;

import org.accounting.exceptions.CategoryNotFoundException;
import org.accounting.exceptions.ExpenseNotFoundException;

import java.time.LocalDate;

public interface ExpenseHandler {
    String addExpense(LocalDate date, double amount, String categoryName) throws CategoryNotFoundException;
    String removeExpense(LocalDate date, double amount, String categoryName) throws CategoryNotFoundException, ExpenseNotFoundException;
}
