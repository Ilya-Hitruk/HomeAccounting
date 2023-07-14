package org.accounting.handlers;

import org.accounting.dao.DataStorage;
import org.accounting.data.Expense;
import org.accounting.interfaces.ReportHandler;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

public class ReportHandlerImpl implements ReportHandler {

    private static final DataStorage DATA_STORAGE = DataStorage.getInstance();
    private static final List<Expense> EXPENSES = DATA_STORAGE.getExpenses();

    @Override
    public List<Expense> getExpensesByCategory(String categoryName) {
        return EXPENSES.stream()
                .filter(expense -> expense.getCategory().name().equals(categoryName))
                .toList();
    }

    @Override
    public List<Expense> getExpensesForDay(LocalDate date) {
        return EXPENSES.stream()
                .filter(expense -> expense.getDate().equals(date))
                .toList();
    }

    @Override
    public List<Expense> getExpensesForWeek(YearMonth yearMonth, int weekNumber) {
        LocalDate firstDayOfMonth = LocalDate.of(yearMonth.getYear(), yearMonth.getMonth(), weekNumber);
        LocalDate firstMondayOfMonth = firstDayOfMonth.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        LocalDate mondayOfRequestedWeek = firstMondayOfMonth.plusWeeks(weekNumber - 1);
        LocalDate mondayOfNextWeek = mondayOfRequestedWeek.plusDays(7);

        return EXPENSES.stream()
                .filter(expense ->
                        (expense.getDate().isAfter(mondayOfRequestedWeek.minusDays(1)) && expense.getDate().isBefore(mondayOfNextWeek)))
                .toList();
    }

    @Override
    public List<Expense> getExpensesForMonth(YearMonth yearMonth) {
        return EXPENSES.stream()
                .filter(expense ->
                        (expense.getDate().getYear() == yearMonth.getYear() && expense.getDate().getMonth() == yearMonth.getMonth()))
                .toList();
    }

    @Override
    public List<Expense> getExpensesForYear(int year) {
        return EXPENSES.stream()
                .filter(expense -> expense.getDate().getYear() == year)
                .toList();
    }

    @Override
    public double getTotalAmount(List<Expense> expenses) {
        return expenses.stream()
                .map(Expense::getAmount)
                .reduce(Double::sum)
                .orElse(0.0);
    }
}