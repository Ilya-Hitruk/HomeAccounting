package org.accounting.controllers;

import org.accounting.dao.CategoryStorage;
import org.accounting.dao.ExpenseStorage;
import org.accounting.exceptions.CategoryExistsException;
import org.accounting.exceptions.CategoryNotFoundException;
import org.accounting.exceptions.NotEmptyCategoryException;
import org.accounting.data.Category;
import org.accounting.interfaces.CategoryController;

public class CategoryControllerImpl implements CategoryController {
    private static final CategoryStorage CATEGORY_STORAGE = CategoryStorage.getInstance();
    private static final ExpenseStorage EXPENSE_STORAGE = ExpenseStorage.getInstance();
    @Override
    public void addCategory(String categoryName) throws CategoryExistsException {
        if (isCategoryExist(categoryName)) {
            throw new CategoryExistsException(categoryName);
        }
        CATEGORY_STORAGE.addCategory(new Category(categoryName));
    }

    @Override
    public void removeCategory(String categoryName) throws CategoryNotFoundException, NotEmptyCategoryException {
        if (!isCategoryExist(categoryName)) {
            throw new CategoryNotFoundException(categoryName);
        }
        if (!isCategoryEmpty(categoryName)) {
            throw new NotEmptyCategoryException(categoryName);
        }
        CATEGORY_STORAGE.removeCategory(new Category(categoryName));
    }

    @Override
    public boolean isCategoryExist(String categoryName) {
        return CATEGORY_STORAGE.getCategories().stream().anyMatch(category -> category.name().equalsIgnoreCase(categoryName));
    }

    @Override
    public boolean isCategoryEmpty(String categoryName) {
        return EXPENSE_STORAGE.getExpenses().stream().noneMatch(expense -> expense.getCategory().name().equalsIgnoreCase(categoryName));
    }
}