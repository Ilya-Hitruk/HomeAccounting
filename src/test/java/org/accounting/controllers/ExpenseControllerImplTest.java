package org.accounting.controllers;

import org.accounting.dao.CategoryStorage;
import org.accounting.dao.ExpenseStorage;
import org.accounting.data.Category;
import org.accounting.data.Expense;
import org.accounting.exceptions.CategoryNotFoundException;
import org.accounting.exceptions.ExpenseNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

class ExpenseControllerImplTest {
    ArgumentCaptor<Expense> expenseArgumentCaptor;
    CategoryStorage categoryStorage;
    ExpenseStorage expenseStorage;
    ExpenseControllerImpl expenseController;

    @BeforeEach
    void setUp() {
        expenseArgumentCaptor = ArgumentCaptor.forClass(Expense.class);
        categoryStorage = mock(CategoryStorage.class);
        expenseStorage = mock(ExpenseStorage.class);
        expenseController = new ExpenseControllerImpl(categoryStorage, expenseStorage);
    }

    @Test
    void addExpenseShouldAddExpense() {
        when(categoryStorage.isCategoryExist("food")).thenReturn(true);
        try {
            expenseController.addExpense(LocalDate.of(2023, 1, 1), 10.0, "food");

            verify(expenseStorage).addExpense(expenseArgumentCaptor.capture());

            Expense value = expenseArgumentCaptor.getValue();
            Assertions.assertEquals(value.getCategory().name(), "food");
            Assertions.assertEquals(value.getAmount(), 10.0);
            Assertions.assertEquals(value.getDate(), LocalDate.of(2023, 1, 1));
        } catch (CategoryNotFoundException e) {
            Assertions.fail();
        }
    }

    @Test
    void addExpenseShouldThrowCategoryNotFoundException() {
        Assertions.assertThrows(CategoryNotFoundException.class,
                () -> expenseController.addExpense(LocalDate.of(2023, 1, 1), 10.0, "11111111"));
    }

    @Test
    void removeExpenseShouldRemoveExpense() {
        when(categoryStorage.isCategoryExist("Shop"))
                .thenReturn(true);

        when(expenseStorage.isExpenseExist(new Expense(LocalDate.of(2023, 7, 10), 1000.0, new Category("Shop"))))
                .thenReturn(true);

        try {
            expenseController.removeExpense(LocalDate.of(2023, 7, 10), 1000.0, "Shop");
            verify(expenseStorage).removeExpense(expenseArgumentCaptor.capture());

            Expense value = expenseArgumentCaptor.getValue();
            Assertions.assertEquals(value.getCategory().name(), "Shop");
            Assertions.assertEquals(value.getAmount(), 1000);
            Assertions.assertEquals(value.getDate(), LocalDate.of(2023, 7, 10));
        } catch (CategoryNotFoundException e) {
            Assertions.fail();
        } catch (ExpenseNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void removeExpenseShouldThrowCategoryNotFoundException() {
        Assertions.assertThrows(CategoryNotFoundException.class,
                () -> expenseController.removeExpense(LocalDate.of(2023, 1, 1), 10.0, "11111111"));
    }

    @Test
    void removeExpenseShouldThrowExpenseNotFoundException() {
        when(categoryStorage.isCategoryExist("Shop"))
                .thenReturn(true);
        Assertions.assertThrows(ExpenseNotFoundException.class,
                () -> expenseController.removeExpense(LocalDate.of(2023, 1, 1), 10.0, "Shop"));
    }
}