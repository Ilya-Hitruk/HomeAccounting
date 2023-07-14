package org.accounting.handlers;

import org.accounting.exceptions.CategoryExistsException;
import org.accounting.exceptions.CategoryNotFoundException;
import org.accounting.exceptions.NotEmptyCategoryException;
import org.accounting.dao.DataStorage;
import org.accounting.data.Category;
import org.accounting.data.Expense;
import org.accounting.interfaces.CategoryHandler;

import java.util.List;

public class CategoryHandlerImpl implements CategoryHandler {
    private static final DataStorage DATA_STORAGE = DataStorage.getInstance();
    private static final List<Category> CATEGORIES = DATA_STORAGE.getCategories();
    private static final List<Expense> EXPENSES = DATA_STORAGE.getExpenses();

    @Override
    public String addCategory(String categoryName) throws CategoryExistsException {
        if (isCategoryExist(categoryName)) {
            throw new CategoryExistsException("category_exists");
        }

        DATA_STORAGE.addCategory(new Category(categoryName));

        return "category_add_success";
    }

    @Override
    public String removeCategory(String categoryName) throws CategoryNotFoundException, NotEmptyCategoryException {
        if (!isCategoryExist(categoryName)) {
            throw new CategoryNotFoundException("category_not_found");
        }
        if (!isCategoryEmpty(categoryName)) {
            throw new NotEmptyCategoryException("category_not_empty");
        }
        DATA_STORAGE.removeCategory(new Category(categoryName));

        return "category_delete_success";
    }

    @Override
    public boolean isCategoryExist( String categoryName) {
        return CATEGORIES.stream().anyMatch(category -> category.name().equalsIgnoreCase(categoryName));
    }

    @Override
    public boolean isCategoryEmpty(String categoryName) {
        return EXPENSES.stream().noneMatch(expense -> expense.getCategory().name().equalsIgnoreCase(categoryName));
    }
}
