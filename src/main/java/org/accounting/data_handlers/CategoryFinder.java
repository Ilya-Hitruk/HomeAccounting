package org.accounting.data_handlers;

import org.accounting.accounting_data.Category;

import java.util.List;
import java.util.Optional;

public class CategoryFinder {
    public Optional<Category> findCategory(List<Category> categories, String categoryName) {
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                return Optional.of(category);
            }
        }
        return Optional.empty();
    }
}
