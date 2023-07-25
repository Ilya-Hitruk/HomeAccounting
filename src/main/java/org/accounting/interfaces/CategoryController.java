package org.accounting.interfaces;

import org.accounting.exceptions.CategoryExistsException;
import org.accounting.exceptions.CategoryNotFoundException;
import org.accounting.exceptions.NotEmptyCategoryException;

public interface CategoryController {
    void addCategory(String categoryName) throws CategoryExistsException;
    void removeCategory(String categoryName) throws CategoryNotFoundException, NotEmptyCategoryException;
    boolean isCategoryExist(String categoryName);
    boolean isCategoryEmpty(String categoryName);
}
