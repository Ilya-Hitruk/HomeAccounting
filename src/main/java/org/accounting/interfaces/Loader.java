package org.accounting.interfaces;

import org.accounting.accounting_data.Category;

import java.util.List;

public interface Loader {
    List<Category> load();
}
