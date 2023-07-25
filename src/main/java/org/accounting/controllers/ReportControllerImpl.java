package org.accounting.controllers;

import org.accounting.dao.CategoryStorage;
import org.accounting.dao.ExpenseStorage;
import org.accounting.data.Expense;
import org.accounting.interfaces.ReportController;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public class ReportControllerImpl implements ReportController {
    private static final ExpenseStorage EXPENSE_STORAGE = ExpenseStorage.getInstance();
    private static final CategoryStorage CATEGORY_STORAGE = CategoryStorage.getInstance();
    @Override
    public List<Expense> getExpensesByCategory(String categoryName) {
        return CATEGORY_STORAGE.getExpensesByCategory(categoryName);
    }

    @Override
    public List<Expense> getExpensesForDay(LocalDate date) {
        return EXPENSE_STORAGE.getExpensesForDay(date);
    }

    @Override
    public List<Expense> getExpensesForWeek(YearMonth yearMonth, int weekNumber) {
        return EXPENSE_STORAGE.getExpensesForWeek(yearMonth, weekNumber);
    }

    @Override
    public List<Expense> getExpensesForMonth(YearMonth yearMonth) {
        return EXPENSE_STORAGE.getExpensesForMonth(yearMonth);
    }

    @Override
    public List<Expense> getExpensesForYear(int year) {
        return EXPENSE_STORAGE.getExpensesForYear(year);
    }

    @Override
    public double getTotalAmount(List<Expense> expenses) {
        return expenses.stream()
                .map(Expense::getAmount)
                .reduce(Double::sum)
                .orElse(0.0);
    }
}