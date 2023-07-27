package org.accounting.menu;

import org.accounting.criteria.Criteria;
import org.accounting.dao.CategoryStorage;
import org.accounting.dao.ExpenseStorage;
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
    private final ConsoleReader CONSOLE_READER;
    private final Notifier NOTIFIER;
    private final CategoryController CATEGORY_CONTROLLER;
    private final ExpenseController EXPENSE_CONTROLLER;
    private final ReportController REPORT;

    public HomeAccountingFunctions() {
        CONSOLE_READER = new ConsoleReader();
        NOTIFIER = new Notifier();
        CATEGORY_CONTROLLER = new CategoryControllerImpl(CategoryStorage.getInstance());
        EXPENSE_CONTROLLER = new ExpenseControllerImpl(CategoryStorage.getInstance(), ExpenseStorage.getInstance());
        REPORT = new ReportControllerImpl(ExpenseStorage.getInstance());
    }

    @Override
    public void addCategory() {
        try {
            CATEGORY_CONTROLLER.addCategory(CONSOLE_READER.readTittle());
            logger.info("Category has been successfully added!");
        } catch (CategoryExistsException e) {
            logger.warn(e.getMessage());
        }
    }

    @Override
    public void deleteCategory() {
        try {
            CATEGORY_CONTROLLER.removeCategory(CONSOLE_READER.readTittle());
            logger.info("Category has been successfully removed!");
        } catch (CategoryNotFoundException | NotEmptyCategoryException e) {
            logger.warn(e.getMessage());
        }
    }

    @Override
    public void addExpense() {
        try {
            EXPENSE_CONTROLLER.addExpense(CONSOLE_READER.readDate(), CONSOLE_READER.readAmount(), CONSOLE_READER.readTittle());
            logger.info("Expense has been successfully added!");
        } catch (CategoryNotFoundException | InvalidInputException e) {
            logger.warn(e.getMessage());
        }
    }

    @Override
    public void deleteExpense() {
        try {
            EXPENSE_CONTROLLER.removeExpense(CONSOLE_READER.readDate(), CONSOLE_READER.readAmount(), CONSOLE_READER.readTittle());
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
        } catch (InvalidInputException | NoDeclaredReportException e) {
            logger.warn(e.getMessage());
        }
    }

    private List<Expense> getSpecifiedReport(int report) throws InvalidInputException, NoDeclaredReportException {
        return switch (report) {
            case 1 -> REPORT.getExpensesForDay(CONSOLE_READER.readDate());
            case 2 -> REPORT.getExpensesForWeek(CONSOLE_READER.getYearAndMonth(), CONSOLE_READER.readWeek());
            case 3 -> REPORT.getExpensesForMonth(CONSOLE_READER.getYearAndMonth());
            case 4 -> REPORT.getExpensesForYear(CONSOLE_READER.readYear());
            case 5 -> REPORT.getExpensesByCategory(CONSOLE_READER.readTittle());
            case 6 -> REPORT.getExpensesByCriteria(new Criteria(CONSOLE_READER.chooseCategories(), CONSOLE_READER.readDate(), CONSOLE_READER.readDate()));
            default -> throw new NoDeclaredReportException();
        };
    }
}