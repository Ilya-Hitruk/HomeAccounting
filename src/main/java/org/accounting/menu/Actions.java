package org.accounting.menu;

public enum Actions {

    ACTION("Choose your action: "),

    // ACTIONS
    ADD_CATEGORY("1 - Add new category.\n"),
    ADD_EXPENSE("2 - Add new expense.\n"),
    DELETE_CATEGORY("3 - Delete category (specified category must be empty).\n"),
    DELETE_EXPENSE("4 - Delete expense\n"),
    REPORTS("5 - Form report.\n"),


    // CATEGORY ACTIONS
    CATEGORY_TITTLE("Write the title of category: "),
    CATEGORY_ADD_SUCCESS("Category was successfully added."),
    CATEGORY_DELETE_SUCCESS("Category was successfully removed."),


    // EXPENSE ACTIONS
    EXPENSE_DATE("Write date of your expansion in format YYYY-MM-DD: "),
    EXPENSE_AMOUNT("Write amount of expansion: "),
    EXPENSE_ADD_SUCCESS("Expansion was successfully added."),
    EXPENSE_DELETE_SUCCESS("Expansion was successfully removed.");


    private final String message;

    Actions(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
