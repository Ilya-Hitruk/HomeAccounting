package org.accounting.controllers;

import org.accounting.criteria.Criteria;
import org.accounting.dao.ExpenseStorage;
import org.accounting.data.Category;
import org.accounting.data.Expense;
import org.accounting.interfaces.ReportController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

class ReportControllerImplTest {
    ArgumentCaptor<Expense> categoryArgumentCaptor;
    ExpenseStorage expenseStorage;
    ReportController reportController;
    Expense first;
    Expense second;
    Expense third;
    Expense excess;

    @BeforeEach
    void setUp() {
        categoryArgumentCaptor = ArgumentCaptor.forClass(Expense.class);
        expenseStorage = mock(ExpenseStorage.class);
        reportController = new ReportControllerImpl(expenseStorage);

        excess = new Expense(LocalDate.parse("2015-01-29"), 250.0, new Category("SomeCategory"));
        first = new Expense(LocalDate.parse("2023-06-05"), 200.0, new Category("Coffee"));
        second = new Expense(LocalDate.parse("2023-06-05"), 1300.0, new Category("Shop"));
        third = new Expense(LocalDate.parse("2023-06-06"), 250.0, new Category("Coffee"));
    }

    @Test
    void getExpensesByCriteriaShouldReturnExpense() {
        Criteria criteria = new Criteria(List.of(first.getCategory(), third.getCategory(), excess.getCategory()),
                LocalDate.parse("2023-06-05"),
                LocalDate.parse("2023-06-07"));

        when(expenseStorage.getExpenses()).thenReturn(List.of(first, third, excess));
        when(expenseStorage.getExpensesByCriteria(criteria)).thenReturn(List.of(first, third));

        List<Expense> actual = reportController.getExpensesByCriteria(criteria);
        List<Expense> expected = expenseStorage.getExpensesByCriteria(criteria);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getExpensesByCategoryShouldReturnExpense() {
        when(expenseStorage.getExpenses()).thenReturn(List.of(first, third, excess));
        when(expenseStorage.getExpensesByCategory("Coffee")).thenReturn(List.of(first, third));

        List<Expense> actual = reportController.getExpensesByCategory("Coffee");
        List<Expense> expected = expenseStorage.getExpensesByCategory("Coffee");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getExpensesForDayShouldReturnExpenses() {
        when(expenseStorage.getExpenses()).thenReturn(List.of(first, second, excess));
        when(expenseStorage.getExpensesForDay(LocalDate.parse("2023-06-05"))).thenReturn(List.of(first, second));

        List<Expense> actual = reportController.getExpensesForDay(LocalDate.parse("2023-06-05"));
        List<Expense> expected = expenseStorage.getExpensesForDay(LocalDate.parse("2023-06-05"));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getExpensesForWeekShouldReturnExpenses() {
        when(expenseStorage.getExpenses()).thenReturn(List.of(first, second,third, excess));
        when(expenseStorage.getExpensesForWeek(YearMonth.of(2023, 6), 1)).thenReturn(List.of(first, second, third));

        List<Expense> actual = reportController.getExpensesForWeek(YearMonth.of(2023, 6), 1);
        List<Expense> expected = expenseStorage.getExpensesForWeek(YearMonth.of(2023, 6), 1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getExpensesForMonthShouldReturnExpenses() {
        when(expenseStorage.getExpenses()).thenReturn(List.of(first, second,third, excess));
        when(expenseStorage.getExpensesForMonth(YearMonth.of(2023, 6))).thenReturn(List.of(first, second, third));

        List<Expense> actual = reportController.getExpensesForMonth(YearMonth.of(2023, 6));
        List<Expense> expected = expenseStorage.getExpensesForMonth(YearMonth.of(2023, 6));
        Assertions.assertEquals(expected, actual);
    }


    @Test
    void getExpensesForYearShouldReturnExpenses() {
        when(expenseStorage.getExpenses()).thenReturn(List.of(first, second,third, excess));
        when(expenseStorage.getExpensesForYear(2023)).thenReturn(List.of(first, second, third));
        List<Expense> expected = expenseStorage.getExpensesForYear(2023);
        List<Expense> actual = reportController.getExpensesForYear(2023);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getTotalAmountShouldReturnExpenses() {
        List<Expense> expenses = List.of(first, second);
        Assertions.assertEquals(1500, reportController.getTotalAmount(expenses));
    }
}