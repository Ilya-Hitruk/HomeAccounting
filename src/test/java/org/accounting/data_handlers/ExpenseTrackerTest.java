package org.accounting.data_handlers;

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
        Assertions.assertEquals(0, new ExpenseTracker().addExpense(date, amount, categoryName));
    }

    @Test
    void addExpenseShouldWarnNotExistingCategory() {
        categoryName = "SomeCategory";
        Assertions.assertEquals(1, new ExpenseTracker().addExpense(date, amount, categoryName));
    }

    @Test
    void removeExpenseShouldRemoveExpense() {
        Assertions.assertEquals(0, new ExpenseTracker().removeExpense(date, amount, categoryName));
    }

    @Test
    void removeExpenseShouldWarnNotExistingCategory() {
        categoryName = "SomeCategory";
        Assertions.assertEquals(1, new ExpenseTracker().removeExpense(date, amount, categoryName));
    }

    @Test
    void removeExpenseShouldWarnNotExistingExpense() {
        amount = 1;
        Assertions.assertEquals(2, new ExpenseTracker().removeExpense(date, amount, categoryName));
    }
}