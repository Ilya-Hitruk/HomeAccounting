package org.accounting.data_handlers;

import org.accounting.accounting_data.Category;
import org.accounting.interfaces.Loader;
import org.accounting.save_load.DataLoader;

import java.util.List;

public class CategoryLoader {
    private final List<Category> categories;
    private static final Loader loader = new DataLoader();

    public CategoryLoader() {
        this.categories = loader.load();
    }

    public List<Category> getCategories() {
        return categories;
    }
}
