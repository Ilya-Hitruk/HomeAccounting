package org.accounting.dao;

import org.accounting.data.Category;
import org.accounting.interfaces.Loader;
import org.accounting.interfaces.Saver;
import org.accounting.saveload.CSVLoader;
import org.accounting.saveload.CSVSaver;

import java.util.List;

public class CategoryStorage {
    private static CategoryStorage categoryStorage;
    private static final Loader LOADER = new CSVLoader();
    private static final Saver SAVER = new CSVSaver();
    private final List<Category> CATEGORIES;

    private CategoryStorage() {
        CATEGORIES = LOADER.loadCategories();
    }

    public static CategoryStorage getInstance() {
        if (categoryStorage == null) {
            categoryStorage = new CategoryStorage();
        }
        return categoryStorage;
    }

    public List<Category> getCategories() {
        return CATEGORIES;
    }

    public void addCategory(Category category) {
        CATEGORIES.add(category);
        SAVER.saveCategories(CATEGORIES);
    }

    public void removeCategory(Category category) {
        CATEGORIES.remove(category);
        SAVER.saveCategories(CATEGORIES);
    }

    public boolean isCategoryExist(String categoryName) {
        return getCategories().stream().anyMatch(category -> category.name().equalsIgnoreCase(categoryName));
    }

    public boolean isCategoryEmpty(String categoryName) {
        return ExpenseStorage.getInstance().getExpenses().stream().noneMatch(expense -> expense.getCategory().name().equalsIgnoreCase(categoryName));
    }
}