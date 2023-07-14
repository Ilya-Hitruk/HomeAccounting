package org.accounting.handlers;

import org.accounting.exceptions.CategoryNotFoundException;
import org.accounting.exceptions.ExpenseNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class ExpenseHandlerImplTest {

    double amount;
    String categoryName;
    LocalDate date;


    @BeforeEach
    void setUp() {
        date = LocalDate.parse("2023-07-01");
        amount = 800;
        categoryName = "Shop";
    }

    @Test
    void addExpenseShouldAddExpenseAndReturnSuccess() throws CategoryNotFoundException {
        String expected = "expense_add_success";
        Assertions.assertEquals(expected, new ExpenseHandlerImpl().addExpense(date, amount, categoryName));
    }

    @Test
    void addExpenseShouldThrowCategoryNotFoundException() {
        Assertions.assertThrows(CategoryNotFoundException.class, () -> new ExpenseHandlerImpl().addExpense(date, amount, "Coffee"));
    }

    @Test
    void removeExpenseShouldRemoveExpenseAndReturnSuccess() throws CategoryNotFoundException, ExpenseNotFoundException {
        String expected = "expense_delete_success";
        Assertions.assertEquals(expected, new ExpenseHandlerImpl().removeExpense(date, amount, categoryName));
    }

    @Test
    void removeExpenseShouldThrowCategoryNotFoundException() {
        Assertions.assertThrows(CategoryNotFoundException.class, () -> new ExpenseHandlerImpl().removeExpense(date, amount, "Coffee"));
    }

    @Test
    void removeExpenseShouldThrowExpenseNotFoundException() {
        Assertions.assertThrows(ExpenseNotFoundException.class, () -> new ExpenseHandlerImpl().removeExpense(date, 1, categoryName));
    }
}