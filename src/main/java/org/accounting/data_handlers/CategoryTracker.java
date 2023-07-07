package org.accounting.data_handlers;

import org.accounting.accounting_data.Category;
import org.accounting.interfaces.CategoryHandler;
import org.accounting.interfaces.Saver;
import org.accounting.save_load.DataSaver;

import java.util.List;
import java.util.Optional;

public class CategoryTracker implements CategoryHandler {
    private static final CategoryFinder categoryFinder = new CategoryFinder();
    private static final Saver saver = new DataSaver();
    private final List<Category> categories = new CategoryLoader().getCategories();

    @Override
    public int addCategory(String title) {
        Optional<Category> optionalCategory = categoryFinder.findCategory(categories, title);
        if (optionalCategory.isPresent()) {
            return 1;
        }
        categories.add(new Category(title));
        saver.save(categories);
        return 0;
    }

    @Override
    public int removeCategory(String title) {
        Optional<Category> optionalCategory = categoryFinder.findCategory(categories, title);
        Category category;

        if (optionalCategory.isEmpty()) {
            return 1;
        }

        category = optionalCategory.get();

        if (!category.getExpenses().isEmpty()) {
            return 2;
        }

        categories.remove(category);
        saver.save(categories);
        return 0;
    }

}
