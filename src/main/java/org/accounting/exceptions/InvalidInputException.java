package org.accounting.exceptions;

public class InvalidInputException extends RuntimeException {

    public InvalidInputException(String message) {
        super(message);
    }

    public static InvalidInputException invalidExpense(String input) {
        throw new InvalidInputException(String.format("Invalid input data <%s>!\n" +
                "Please use as a template: Date - YYYY-MM-DD, Amount - 100 or 100.0, Category - Coffee or coffee..", input));
    }
    public static InvalidInputException invalidWeek(String week) {
        throw new InvalidInputException(String.format("Invalid week input <%s>!\n Use as a template: Week - W (e.g: 1 ... 4 ).", week));
    }
    public static InvalidInputException invalidMonth(String month) {
        throw new InvalidInputException(String.format("Invalid month input <%s>!\n" +
                "Use the template: Month - M (e.g: 1 for January ... 12 for December).", month));
    }

    public static InvalidInputException invalidYear(String year) {
        throw new InvalidInputException(String.format("Invalid year input <%s>!\n" +
                "Use the template: YYYY (should be lower then current)", year));
    }
}
