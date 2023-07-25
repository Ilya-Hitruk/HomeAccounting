package org.accounting.menu;

import org.accounting.exceptions.*;
import org.accounting.data.Expense;
import org.accounting.controllers.CategoryControllerImpl;
import org.accounting.controllers.ExpenseControllerImpl;
import org.accounting.controllers.ReportControllerImpl;
import org.accounting.interfaces.AccountingFunctions;
import org.accounting.interfaces.CategoryController;
import org.accounting.interfaces.ExpenseController;
import org.accounting.interfaces.ReportController;
import org.accounting.io.ConsoleReader;
import org.accounting.io.Notifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class HomeAccountingFunctions implements AccountingFunctions {
    private final static Logger logger = LoggerFactory.getLogger(HomeAccountingFunctions.class);
    private final static ConsoleReader CONSOLE_READER = new ConsoleReader();
    private final static Notifier NOTIFIER = new Notifier();

    private final static CategoryController CATEGORY_HANDLER = new CategoryControllerImpl();
    private final static ExpenseController EXPENSE_HANDLER = new ExpenseControllerImpl();
    private final static ReportController REPORT = new ReportControllerImpl();

    @Override
    public void addCategory() {
        try {
            CATEGORY_HANDLER.addCategory(CONSOLE_READER.readTittle());
            logger.info("Category has been successfully added!");
        } catch (CategoryExistsException e) {
            logger.warn(e.getMessage());
        }
    }

    @Override
    public void deleteCategory() {
        try {
            CATEGORY_HANDLER.removeCategory(CONSOLE_READER.readTittle());
            logger.info("Category has been successfully removed!");
        } catch (CategoryNotFoundException | NotEmptyCategoryException e) {
            logger.warn(e.getMessage());
        }
    }

    @Override
    public void addExpense() {
        try {
            EXPENSE_HANDLER.addExpense(CONSOLE_READER.readDate(), CONSOLE_READER.readAmount(), CONSOLE_READER.readTittle());
            logger.info("Expense has been successfully added!");
        } catch (CategoryNotFoundException | InvalidInputException e) {
            logger.warn(e.getMessage());
        }
    }

    @Override
    public void deleteExpense() {
        try {
            EXPENSE_HANDLER.removeExpense(CONSOLE_READER.readDate(), CONSOLE_READER.readAmount(), CONSOLE_READER.readTittle());
            logger.info("Expense has been successfully removed!");
        } catch (CategoryNotFoundException | ExpenseNotFoundException | InvalidInputException e) {
            logger.warn(e.getMessage());
        }
    }

    @Override
    public void reports() {
        NOTIFIER.showReportMenu();
        int report = CONSOLE_READER.readAction();
        List<Expense> expenses;

        try {
            expenses = getSpecifiedReport(report);
            double totalAmount = REPORT.getTotalAmount(expenses);
            NOTIFIER.showReport(expenses, totalAmount);
        } catch (InvalidInputException | NoDeclaredReportException | InvalidYearException | InvalidWeekException |
                 InvalidMonthException e) {
            logger.warn(e.getMessage());
        }
    }

    private List<Expense> getSpecifiedReport(int report) throws InvalidInputException, NoDeclaredReportException, InvalidYearException, InvalidWeekException, InvalidMonthException {
        return switch (report) {
            case 1 -> REPORT.getExpensesForDay(CONSOLE_READER.readDate());
            case 2 -> REPORT.getExpensesForWeek(CONSOLE_READER.getYearAndMonth(), CONSOLE_READER.readWeek());
            case 3 -> REPORT.getExpensesForMonth(CONSOLE_READER.getYearAndMonth());
            case 4 -> REPORT.getExpensesForYear(CONSOLE_READER.readYear());
            case 5 -> REPORT.getExpensesByCategory(CONSOLE_READER.readTittle());
            default -> throw new NoDeclaredReportException();
        };
    }
}