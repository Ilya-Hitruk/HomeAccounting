package org.accounting.exceptions;

public class CategoryExistsException extends Exception {

    public CategoryExistsException(String categoryName) {
        super(String.format("Category <%s> is already exist!", categoryName));
    }
}
