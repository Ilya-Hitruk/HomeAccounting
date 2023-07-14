package org.accounting.interfaces;

import org.accounting.data.Expense;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public interface ReportHandler {

    List<Expense> getExpensesByCategory(String categoryName);
    List<Expense> getExpensesForDay(LocalDate date);
    List<Expense> getExpensesForWeek(YearMonth yearMonth, int weekNumber);
    List<Expense> getExpensesForMonth(YearMonth yearMonth);
    List<Expense> getExpensesForYear(int year);
    double getTotalAmount(List<Expense> expenses);
}
