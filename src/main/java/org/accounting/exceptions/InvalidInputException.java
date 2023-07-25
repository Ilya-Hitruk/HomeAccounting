package org.accounting.exceptions;

import org.accounting.data.Expense;

public class InvalidInputException extends Exception {
    public InvalidInputException(String input) {
        super(String.format("Invalid input data <%s>!\n" +
                "Please use as a template: Date - YYYY-MM-DD, Amount - 100 or 100.0, Category - Coffee or coffee..", input));
    }
}
