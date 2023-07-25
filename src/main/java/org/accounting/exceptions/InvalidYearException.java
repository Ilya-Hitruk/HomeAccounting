package org.accounting.exceptions;

public class InvalidYearException extends Exception {
    public InvalidYearException(String year) {
        super(String.format("Invalid year input <%s>!\n" +
                "Use the template: YYYY (should be lower then current)", year));
    }
}
