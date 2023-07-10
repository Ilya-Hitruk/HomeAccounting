package org.accounting;

import org.accounting.data_handlers.CategoryTracker;
import org.accounting.data_handlers.ExpenseTracker;
import org.accounting.interfaces.CategoryHandler;
import org.accounting.interfaces.ExpenseHandler;
import org.accounting.menu.ConsoleReader;
import org.accounting.menu.Notifier;
import org.accounting.validity.ExecutionCode;
import org.accounting.validity.Warns;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class HomeAccountingMenu {
    private final static Logger logger = LoggerFactory.getLogger(HomeAccountingMenu.class);
    private final static CategoryHandler CATEGORY_HANDLER = new CategoryTracker();
    private final static ExpenseHandler EXPENSE_HANDLER = new ExpenseTracker();
    private final static ConsoleReader CONSOLE_READER = new ConsoleReader();
    private final static Notifier NOTIFIER = new Notifier();

    public static void execute() {

        NOTIFIER.showMenu();

        int choice = CONSOLE_READER.readAction();

        switch (choice) {
            case 1 -> addCategory();
            case 2 -> addExpense();
            case 3 -> deleteCategory();
            case 4 -> deleteExpense();
            case 5 -> reports();
            default -> logger.warn(Warns.NO_DECLARED_ACTION.getMessage());
        }
    }


    private static void addCategory() {
        String categoryTittle = CONSOLE_READER.readTittle();
        ExecutionCode code = CATEGORY_HANDLER.addCategory(categoryTittle);
        NOTIFIER.categoryAddSuccess(code);
    }

    private static void deleteCategory() {
        String category = CONSOLE_READER.readTittle();
        ExecutionCode code = CATEGORY_HANDLER.removeCategory(category);
        NOTIFIER.categoryRemoveSuccess(code);
    }

    private static void addExpense() {
        String dateString = CONSOLE_READER.readDate();
        LocalDate date = LocalDate.parse(dateString);

        double amount = CONSOLE_READER.readAmount();
        String categoryTittle = CONSOLE_READER.readTittle();

        ExecutionCode code = EXPENSE_HANDLER.addExpense(date, amount, categoryTittle);
        NOTIFIER.expenseAddSuccess(code);
    }

    private static void deleteExpense() {
        String dateString = CONSOLE_READER.readDate();
        LocalDate date = LocalDate.parse(dateString);

        double amount = CONSOLE_READER.readAmount();
        String categoryTittle = CONSOLE_READER.readTittle();

        ExecutionCode code = EXPENSE_HANDLER.removeExpense(date, amount, categoryTittle);
        NOTIFIER.expenseRemoveSuccess(code);
    }
    public static void reports() {

    }
}