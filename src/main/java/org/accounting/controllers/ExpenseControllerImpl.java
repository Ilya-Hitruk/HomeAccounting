package org.accounting.controllers;

import org.accounting.dao.CategoryStorage;
import org.accounting.dao.ExpenseStorage;
import org.accounting.exceptions.CategoryNotFoundException;
import org.accounting.exceptions.ExpenseNotFoundException;
import org.accounting.data.Category;
import org.accounting.data.Expense;
import org.accounting.interfaces.ExpenseController;
import java.time.LocalDate;

public class ExpenseControllerImpl implements ExpenseController {
    private final CategoryStorage CATEGORY_STORAGE;
    private final ExpenseStorage EXPENSE_STORAGE;

    public ExpenseControllerImpl(CategoryStorage categoryStorage, ExpenseStorage expenseStorage) {
        CATEGORY_STORAGE = categoryStorage;
        EXPENSE_STORAGE = expenseStorage;
    }

    @Override
    public void addExpense(LocalDate date, double amount, String categoryName) throws CategoryNotFoundException {
        if (!CATEGORY_STORAGE.isCategoryExist(categoryName)) {
            throw new CategoryNotFoundException(categoryName);
        }

        EXPENSE_STORAGE.addExpense(new Expense(date, amount, new Category(categoryName)));
    }

    @Override
    public void removeExpense(LocalDate date, double amount, String categoryName) throws CategoryNotFoundException, ExpenseNotFoundException {
        if (!CATEGORY_STORAGE.isCategoryExist(categoryName)) {
            throw new CategoryNotFoundException(categoryName);
        }

        Expense expense = new Expense(date, amount, new Category(categoryName));

        if (!EXPENSE_STORAGE.isExpenseExist(expense)) {
            throw new ExpenseNotFoundException(expense);
        }

        EXPENSE_STORAGE.removeExpense(expense);
    }
}