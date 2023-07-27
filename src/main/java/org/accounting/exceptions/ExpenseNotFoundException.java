package org.accounting.exceptions;

import org.accounting.data.Expense;

public class ExpenseNotFoundException extends RuntimeException {

    public ExpenseNotFoundException(Expense expense) {
        super(String.format("Specified expense <%s, %f, %s> wasn't found!!",
                expense.getDate().toString(), expense.getAmount(), expense.getCategory().name()));
    }
}
