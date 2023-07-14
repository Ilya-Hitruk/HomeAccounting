package org.accounting.exceptions;

public class CategoryExistsException extends Exception {
    public CategoryExistsException(String message) {
        super(message);
    }
}
