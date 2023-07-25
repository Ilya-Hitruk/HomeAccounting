package org.accounting.exceptions;

public class CategoryNotFoundException extends Exception {
    public CategoryNotFoundException(String categoryName) {
        super(String.format("Specified category <%s> wasn't found!!", categoryName));
    }
}
