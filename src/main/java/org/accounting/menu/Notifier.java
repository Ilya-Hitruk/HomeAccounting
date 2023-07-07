package org.accounting.menu;

import org.accounting.warns.Warns;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Notifier {

    private final static Logger logger = LoggerFactory.getLogger(Notifier.class);

    public void showMenu() {
        System.out.print(Actions.ADD_CATEGORY.getMessage() +
                Actions.ADD_EXPENSE.getMessage() +
                Actions.DELETE_CATEGORY.getMessage() +
                Actions.DELETE_EXPENSE.getMessage() +
                Actions.REPORTS.getMessage() +
                Actions.ACTION.getMessage());
    }

    public void categoryAddSuccess(int success) {
        if (success == 0) {
            logger.info(Actions.CATEGORY_ADD_SUCCESS.getMessage());
        } else {
            logger.warn(Warns.CATEGORY_EXISTS.getMessage());
        }
    }

    public void categoryRemoveSuccess(int success) {
        if (success == 0) {
            logger.info(Actions.CATEGORY_DELETE_SUCCESS.getMessage());
        } else if (success == 1) {
            logger.warn(Warns.CATEGORY_DOES_NOT_EXIST.getMessage());
        } else {
            logger.warn(Warns.CATEGORY_DELETE_FAILED.getMessage());
        }
    }

    public void expenseAddSuccess(int success) {
        if (success == 0) {
            logger.info(Actions.EXPENSE_ADD_SUCCESS.getMessage());
        } else {
            logger.warn(Warns.CATEGORY_DOES_NOT_EXIST.getMessage());
        }
    }

    public void expenseRemoveSuccess(int success) {
        if (success == 0) {
            logger.info(Actions.EXPENSE_DELETE_SUCCESS.getMessage());
        } else if (success == 1){
            logger.warn(Warns.CATEGORY_DOES_NOT_EXIST.getMessage());
        } else {
            logger.warn(Warns.EXPENSE_DELETE_FAILED.getMessage());
        }
    }
}
