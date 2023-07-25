package org.accounting.exceptions;

public class InvalidMonthException extends Exception {

    public InvalidMonthException(String month) {
        super(String.format("Invalid month input <%s>!\n" +
                "Use the template: Month - M (e.g: 1 for January ... 12 for December).", month));
    }
}
