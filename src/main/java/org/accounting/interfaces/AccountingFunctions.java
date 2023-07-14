package org.accounting.interfaces;

import org.accounting.custom.exceptions.*;
import org.accounting.data.Expense;

import java.util.List;

public interface AccountingFunctions {
    public void addCategory();

    public void deleteCategory();

    public void addExpense();

    public void deleteExpense();

    public void reports();
}
