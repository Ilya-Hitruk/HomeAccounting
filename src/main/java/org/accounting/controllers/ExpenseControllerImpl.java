package org.accounting.controllers;

import org.accounting.dao.ExpenseStorage;
import org.accounting.exceptions.CategoryNotFoundException;
import org.accounting.exceptions.ExpenseNotFoundException;
import org.accounting.data.Category;
import org.accounting.data.Expense;
import org.accounting.interfaces.CategoryController;
import org.accounting.interfaces.ExpenseController;
import java.time.LocalDate;

public class ExpenseControllerImpl implements ExpenseController {
    private static final CategoryController CATEGORY_CONTROLLER = new CategoryControllerImpl();
    private static final ExpenseStorage EXPENSE_STORAGE = ExpenseStorage.getInstance();

    @Override
    public void addExpense(LocalDate date, double amount, String categoryName) throws CategoryNotFoundException {
        boolean isCategoryPresent = CATEGORY_CONTROLLER.isCategoryExist(categoryName);

        if (!isCategoryPresent) {
            throw new CategoryNotFoundException(categoryName);
        }

        EXPENSE_STORAGE.addExpense(new Expense(date, amount, new Category(categoryName)));
    }

    @Override
    public void removeExpense(LocalDate date, double amount, String categoryName) throws CategoryNotFoundException, ExpenseNotFoundException {
        boolean isCategoryPresent = CATEGORY_CONTROLLER.isCategoryExist(categoryName);

        if (!isCategoryPresent) {
            throw new CategoryNotFoundException(categoryName);
        }

        Expense expense = new Expense(date, amount, new Category(categoryName));

        if (!EXPENSE_STORAGE.getExpenses().contains(expense)) {
            throw new ExpenseNotFoundException(expense);
        }

        EXPENSE_STORAGE.removeExpense(expense);
    }
}