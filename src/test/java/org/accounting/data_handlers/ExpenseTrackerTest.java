package org.accounting.data_handlers;

import org.accounting.validity.ExecutionCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class ExpenseTrackerTest {
    String categoryName;
    LocalDate date;
    double amount;


    @BeforeEach
    void setUp() {
        categoryName = "Shopping";
        date = LocalDate.parse("2023-07-01");
        amount = 100;
    }

    @Test
    void addExpenseShouldAddExpense() {
        Assertions.assertEquals(ExecutionCode.CODE_SUCCESS, new ExpenseTracker().addExpense(date, amount, categoryName));
    }

    @Test
    void addExpenseShouldWarnNotExistingCategory() {
        categoryName = "SomeCategory";
        Assertions.assertEquals(ExecutionCode.CODE_FAILED_1, new ExpenseTracker().addExpense(date, amount, categoryName));
    }

    @Test
    void removeExpenseShouldRemoveExpense() {
        Assertions.assertEquals(ExecutionCode.CODE_SUCCESS, new ExpenseTracker().removeExpense(date, amount, categoryName));
    }

    @Test
    void removeExpenseShouldWarnNotExistingCategory() {
        categoryName = "SomeCategory";
        Assertions.assertEquals(ExecutionCode.CODE_FAILED_1, new ExpenseTracker().removeExpense(date, amount, categoryName));
    }

    @Test
    void removeExpenseShouldWarnNotExistingExpense() {
        amount = 1;
        Assertions.assertEquals(ExecutionCode.CODE_FAILED_2, new ExpenseTracker().removeExpense(date, amount, categoryName));
    }
}