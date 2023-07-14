package org.accounting.interfaces;

import org.accounting.data.Category;
import org.accounting.data.Expense;

import java.util.List;

public interface Loader {
    List<Expense> loadExpenses();
    List<Category> loadCategories();
}
