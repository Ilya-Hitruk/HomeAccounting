package org.accounting.dao;

import org.accounting.data.Category;
import org.accounting.data.Expense;
import org.accounting.interfaces.Loader;
import org.accounting.interfaces.Saver;
import org.accounting.saveload.CSVLoader;
import org.accounting.saveload.CSVSaver;

import java.util.List;

public class DataStorage {
    private static DataStorage dataStorage;
    private static final Loader LOADER = new CSVLoader();
    private static final Saver SAVER = new CSVSaver();
    private static final List<Expense> EXPENSES = LOADER.loadExpenses();
    private static final List<Category> CATEGORIES = LOADER.loadCategories();

    private DataStorage() {}

    public static DataStorage getInstance() {
        if (dataStorage == null) {
            dataStorage = new DataStorage();
        }
        return dataStorage;
    }

    public List<Expense> getExpenses() {
        return EXPENSES;
    }

    public List<Category> getCategories() {
        return CATEGORIES;
    }

    public void addExpense(Expense expense) {
        EXPENSES.add(expense);
        SAVER.saveExpenses(EXPENSES);
    }

    public void removeExpense(Expense expense) {
        EXPENSES.remove(expense);
        SAVER.saveExpenses(EXPENSES);
    }

    public void addCategory(Category category) {
        CATEGORIES.add(category);
        SAVER.saveCategories(CATEGORIES);
    }

    public void removeCategory(Category category) {
        CATEGORIES.remove(category);
        SAVER.saveCategories(CATEGORIES);
    }
}