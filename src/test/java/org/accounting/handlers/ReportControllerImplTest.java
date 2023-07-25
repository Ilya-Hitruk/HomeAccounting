package org.accounting.handlers;

import org.accounting.controllers.ReportControllerImpl;
import org.accounting.data.Category;
import org.accounting.data.Expense;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

class ReportControllerImplTest {

    @Test
    void getExpensesByCategoryShouldReturnExpense() {

    }

    @Test
    void getExpensesForDayShouldReturnExpenses() {
        List<Expense> expected = List.of(
                new Expense(LocalDate.parse("2023-06-05"), 200.0, new Category("Coffee")),
                new Expense(LocalDate.parse("2023-06-05"), 1300.0, new Category("Shop")));

        Assertions.assertEquals(expected, new ReportControllerImpl().getExpensesForDay(LocalDate.parse("2023-06-05")));
    }

    @Test
    void getExpensesForWeekShouldReturnExpenses() {
        List<Expense> expected = List.of(
                new Expense(LocalDate.parse("2023-06-05"), 200.0, new Category("Coffee")),
                new Expense(LocalDate.parse("2023-06-05"), 1300.0, new Category("Shop")),
                new Expense(LocalDate.parse("2023-06-06"), 250.0, new Category("Coffee")));
        Assertions.assertEquals(expected, new ReportControllerImpl().getExpensesForWeek(YearMonth.of(2023, 6), 1));

    }

    @Test
    void getExpensesForMonthShouldReturnExpenses() {
        List<Expense> expected = List.of(
                new Expense(LocalDate.parse("2023-06-05"), 200.0, new Category("Coffee")),
                new Expense(LocalDate.parse("2023-06-05"), 1300.0, new Category("Shop")),
                new Expense(LocalDate.parse("2023-06-06"), 250.0, new Category("Coffee")));
        Assertions.assertEquals(expected, new ReportControllerImpl().getExpensesForMonth(YearMonth.of(2023, 6)));
    }

    @Test
    void getExpensesForYearShouldReturnExpenses() {
        List<Expense> expected = List.of(
                new Expense(LocalDate.parse("2022-12-20"), 1000.0, new Category("Activities")));
        Assertions.assertEquals(expected, new ReportControllerImpl().getExpensesForYear(2022));
    }

    @Test
    void getTotalAmountShouldReturnExpenses() {
        List<Expense> expenses = List.of(
                new Expense(LocalDate.parse("2023-06-05"), 200.0, new Category("Coffee")),
                new Expense(LocalDate.parse("2023-06-05"), 1300.0, new Category("Shop")));
        Assertions.assertEquals(1500, new ReportControllerImpl().getTotalAmount(expenses));
    }
}