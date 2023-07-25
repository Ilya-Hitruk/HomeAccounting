package org.accounting.menu;

import org.accounting.interfaces.AccountingFunctions;
import org.accounting.io.ConsoleReader;
import org.accounting.io.Notifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeAccountingMenu {
    private final static Logger logger = LoggerFactory.getLogger(HomeAccountingMenu.class);
    private final static ConsoleReader CONSOLE_READER = new ConsoleReader();
    private final static Notifier NOTIFIER = new Notifier();
    private static final AccountingFunctions ACCOUNTING_FUNCTIONS = new HomeAccountingFunctions();

    public static void execute() {
        boolean processing = true;
        do {
            NOTIFIER.showMenu();
            int choice = CONSOLE_READER.readAction();

            switch (choice) {
                case 1 -> ACCOUNTING_FUNCTIONS.addCategory();
                case 2 -> ACCOUNTING_FUNCTIONS.addExpense();
                case 3 -> ACCOUNTING_FUNCTIONS.deleteCategory();
                case 4 -> ACCOUNTING_FUNCTIONS.deleteExpense();
                case 5 -> ACCOUNTING_FUNCTIONS.reports();
                case 0 -> processing = false;
                default -> logger.warn("No such action!");
            }
            System.out.println();
        } while (processing);
    }
}