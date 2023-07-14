package org.accounting.handlers;

import org.accounting.exceptions.CategoryNotFoundException;
import org.accounting.exceptions.ExpenseNotFoundException;
import org.accounting.dao.DataStorage;
import org.accounting.data.Category;
import org.accounting.data.Expense;
import org.accounting.interfaces.CategoryHandler;
import org.accounting.interfaces.ExpenseHandler;

import java.time.LocalDate;
import java.util.List;

public class ExpenseHandlerImpl implements ExpenseHandler {
    private static final CategoryHandler CATEGORY_HANDLER = new CategoryHandlerImpl();
    private static final DataStorage DATA_STORAGE = DataStorage.getInstance();
    private static final List<Expense> EXPENSES = DATA_STORAGE.getExpenses();

    @Override
    public String addExpense(LocalDate date, double amount, String categoryName) throws CategoryNotFoundException {
        boolean isCategoryPresent = CATEGORY_HANDLER.isCategoryExist(categoryName);

        if (!isCategoryPresent) {
            throw new CategoryNotFoundException("category_not_found");
        }

        DATA_STORAGE.addExpense(new Expense(date, amount, new Category(categoryName)));

        return "expense_add_success";
    }
    @Override
    public String removeExpense(LocalDate date, double amount, String categoryName) throws CategoryNotFoundException, ExpenseNotFoundException {
        boolean isCategoryPresent = CATEGORY_HANDLER.isCategoryExist(categoryName);

        if (!isCategoryPresent) {
            throw new CategoryNotFoundException("category_not_found");
        }

        Expense expense = new Expense(date, amount, new Category(categoryName));

        if (!EXPENSES.contains(expense)) {
            throw new ExpenseNotFoundException("expense_not_found");
        }

        DATA_STORAGE.removeExpense(expense);

        return "expense_delete_success";

    }
}