package org.accounting.data_handlers;

import org.accounting.accounting_data.Category;
import org.accounting.accounting_data.Expense;
import org.accounting.interfaces.ExpenseHandler;
import org.accounting.interfaces.Saver;
import org.accounting.save_load.DataSaver;
import org.accounting.validity.ExecutionCode;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ExpenseTracker implements ExpenseHandler {
    private static final Saver saver = new DataSaver();
    private static final CategoryFinder CATEGORY_FINDER = new CategoryFinder();
    private final List<Category> categories = new CategoryLoader().getCategories();

    @Override
    public ExecutionCode addExpense(LocalDate date, double amount, String categoryName) {
        Optional<Category> optionalCategory = CATEGORY_FINDER.findCategory(categories, categoryName);
        Expense expense = new Expense(date, amount);
        Category category;

        if (optionalCategory.isEmpty()) {
            return ExecutionCode.CODE_FAILED_1;
        }

        category = optionalCategory.get();
        category.addExpense(expense);
        saver.save(categories);
        return ExecutionCode.CODE_SUCCESS;
    }

    @Override
    public ExecutionCode removeExpense(LocalDate date, double amount, String categoryName) {
        Optional<Category> optionalCategory = CATEGORY_FINDER.findCategory(categories, categoryName);
        Expense expense = new Expense(date, amount);
        Category category;

        if (optionalCategory.isEmpty()) {
            return ExecutionCode.CODE_FAILED_1;
        }

        category = optionalCategory.get();

        if (!category.getExpenses().contains(expense)) {
            return ExecutionCode.CODE_FAILED_2;
        }

        category.getExpenses().remove(expense);
        saver.save(categories);
        return ExecutionCode.CODE_SUCCESS;
    }

}
