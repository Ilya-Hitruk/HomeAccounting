package org.accounting.menu;

import org.accounting.exceptions.*;
import org.accounting.io.MessageListener;
import org.accounting.data.Expense;
import org.accounting.handlers.CategoryHandlerImpl;
import org.accounting.handlers.ExpenseHandlerImpl;
import org.accounting.handlers.ReportHandlerImpl;
import org.accounting.interfaces.AccountingFunctions;
import org.accounting.interfaces.CategoryHandler;
import org.accounting.interfaces.ExpenseHandler;
import org.accounting.interfaces.ReportHandler;
import org.accounting.io.ConsoleReader;
import org.accounting.io.Notifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class HomeAccountingFunctions implements AccountingFunctions {
    private final static Logger logger = LoggerFactory.getLogger(HomeAccountingFunctions.class);
    private final static ConsoleReader CONSOLE_READER = new ConsoleReader();
    private final static Notifier NOTIFIER = new Notifier();

    private final static CategoryHandler CATEGORY_HANDLER = new CategoryHandlerImpl();
    private final static ExpenseHandler EXPENSE_HANDLER = new ExpenseHandlerImpl();
    private final static ReportHandler REPORT = new ReportHandlerImpl();

    @Override
    public void addCategory() {
        try {
            String log = CATEGORY_HANDLER.addCategory(CONSOLE_READER.readTittle());
            logger.info(MessageListener.getMessage(log));
        } catch (CategoryExistsException e) {
            logger.warn(MessageListener.getMessage(e.getMessage()));
        }
    }

    @Override
    public void deleteCategory() {
        try {
            String log = CATEGORY_HANDLER.removeCategory(CONSOLE_READER.readTittle());
            logger.info(MessageListener.getMessage(log));
        } catch (CategoryNotFoundException | NotEmptyCategoryException e) {
            logger.warn(MessageListener.getMessage(e.getMessage()));
        }
    }

    @Override
    public void addExpense() {
        try {
            String log = EXPENSE_HANDLER.addExpense(CONSOLE_READER.readDate(), CONSOLE_READER.readAmount(), CONSOLE_READER.readTittle());
            logger.info(MessageListener.getMessage(log));
        } catch (CategoryNotFoundException | InvalidInputException e) {
            logger.warn(MessageListener.getMessage(e.getMessage()));
        }
    }

    @Override
    public void deleteExpense() {
        try {
            String log = EXPENSE_HANDLER.removeExpense(CONSOLE_READER.readDate(), CONSOLE_READER.readAmount(), CONSOLE_READER.readTittle());
            logger.info(MessageListener.getMessage(log));
        } catch (CategoryNotFoundException | ExpenseNotFoundException | InvalidInputException e) {
            logger.warn(MessageListener.getMessage(e.getMessage()));
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
        } catch (InvalidInputException | NoDeclaredReportException e) {
            logger.warn(MessageListener.getMessage(e.getMessage()));
        }
    }

    private List<Expense> getSpecifiedReport(int report) throws InvalidInputException, NoDeclaredReportException {
        return switch (report) {
            case 1 -> REPORT.getExpensesForDay(CONSOLE_READER.readDate());
            case 2 -> REPORT.getExpensesForWeek(CONSOLE_READER.getYearAndMonth(), CONSOLE_READER.readWeek());
            case 3 -> REPORT.getExpensesForMonth(CONSOLE_READER.getYearAndMonth());
            case 4 -> REPORT.getExpensesForYear(CONSOLE_READER.readYear());
            case 5 -> REPORT.getExpensesByCategory(CONSOLE_READER.readTittle());
            default -> throw new NoDeclaredReportException("no_declared_report");
        };
    }
}
