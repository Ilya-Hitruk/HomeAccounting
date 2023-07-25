package org.accounting.exceptions;

public class NotEmptyCategoryException extends Exception{
    public NotEmptyCategoryException(String categoryName) {
        super(String.format("Specified category <%s> can't be removed because it's not empty!", categoryName));
    }
}
