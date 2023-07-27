package org.accounting.controllers;

import org.accounting.dao.CategoryStorage;
import org.accounting.exceptions.CategoryExistsException;
import org.accounting.exceptions.CategoryNotFoundException;
import org.accounting.exceptions.NotEmptyCategoryException;
import org.accounting.data.Category;
import org.accounting.interfaces.CategoryController;

public class CategoryControllerImpl implements CategoryController {
    private final CategoryStorage CATEGORY_STORAGE;

    public CategoryControllerImpl(CategoryStorage categoryStorage) {
        CATEGORY_STORAGE = categoryStorage;
    }

    @Override
    public void addCategory(String categoryName) throws CategoryExistsException {
        if (CATEGORY_STORAGE.isCategoryExist(categoryName)) {
            throw new CategoryExistsException(categoryName);
        }
        CATEGORY_STORAGE.addCategory(new Category(categoryName));
    }

    @Override
    public void removeCategory(String categoryName) throws CategoryNotFoundException, NotEmptyCategoryException {
        if (!CATEGORY_STORAGE.isCategoryExist(categoryName)) {
            throw new CategoryNotFoundException(categoryName);
        }
        if (!CATEGORY_STORAGE.isCategoryEmpty(categoryName)) {
            throw new NotEmptyCategoryException(categoryName);
        }
        CATEGORY_STORAGE.removeCategory(new Category(categoryName));
    }
}