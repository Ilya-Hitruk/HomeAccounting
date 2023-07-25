package org.accounting.dao;

import org.accounting.data.Expense;
import org.accounting.interfaces.Loader;
import org.accounting.interfaces.Saver;
import org.accounting.saveload.CSVLoader;
import org.accounting.saveload.CSVSaver;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

public class ExpenseStorage {
    private static ExpenseStorage expenseStorage;
    private static final Loader LOADER = new CSVLoader();
    private static final Saver SAVER = new CSVSaver();
    private final List<Expense> EXPENSES;

    private ExpenseStorage() {
        EXPENSES = LOADER.loadExpenses();
    }

    public static ExpenseStorage getInstance() {
        if (expenseStorage == null) {
            expenseStorage = new ExpenseStorage();
        }
        return expenseStorage;
    }

    public List<Expense> getExpenses() {
        return EXPENSES;
    }

    public void addExpense(Expense expense) {
        EXPENSES.add(expense);
        SAVER.saveExpenses(EXPENSES);
    }

    public void removeExpense(Expense expense) {
        EXPENSES.remove(expense);
        SAVER.saveExpenses(EXPENSES);
    }

    public List<Expense> getExpensesForDay(LocalDate date) {
        return getExpenses().stream()
                .filter(expense -> expense.getDate().equals(date))
                .toList();
    }

    public List<Expense> getExpensesForWeek(YearMonth yearMonth, int weekNumber) {
        LocalDate firstDayOfMonth = LocalDate.of(yearMonth.getYear(), yearMonth.getMonth(), weekNumber);
        LocalDate firstMondayOfMonth = firstDayOfMonth.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        LocalDate mondayOfRequestedWeek = firstMondayOfMonth.plusWeeks(weekNumber - 1);
        LocalDate mondayOfNextWeek = mondayOfRequestedWeek.plusDays(7);

        return getExpenses().stream()
                .filter(expense ->
                        (expense.getDate().isAfter(mondayOfRequestedWeek.minusDays(1)) && expense.getDate().isBefore(mondayOfNextWeek)))
                .toList();
    }

    public List<Expense> getExpensesForMonth(YearMonth yearMonth) {
        return getExpenses().stream()
                .filter(expense ->
                        (expense.getDate().getYear() == yearMonth.getYear() && expense.getDate().getMonth() == yearMonth.getMonth()))
                .toList();
    }

    public List<Expense> getExpensesForYear(int year) {
        return getExpenses().stream()
                .filter(expense -> expense.getDate().getYear() == year)
                .toList();
    }
}