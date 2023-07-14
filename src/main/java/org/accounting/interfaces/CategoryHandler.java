package org.accounting.interfaces;

import org.accounting.exceptions.CategoryExistsException;
import org.accounting.exceptions.CategoryNotFoundException;
import org.accounting.exceptions.NotEmptyCategoryException;

public interface CategoryHandler {
    String addCategory(String categoryName) throws CategoryExistsException;
    String removeCategory(String categoryName) throws CategoryNotFoundException, NotEmptyCategoryException;
    boolean isCategoryExist(String categoryName);
    boolean isCategoryEmpty(String categoryName);
}
