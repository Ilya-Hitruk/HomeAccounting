package org.accounting.interfaces;

import org.accounting.data.Category;
import org.accounting.data.Expense;

import java.util.List;

public interface Saver {
    void saveExpenses(List<Expense> expenses);
    void saveCategories(List<Category> categories);
}
