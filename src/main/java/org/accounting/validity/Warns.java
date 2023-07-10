package org.accounting.validity;

public enum Warns {
    NO_DECLARED_ACTION("Specified action does not exist!"),
    CATEGORY_EXISTS("Specified category is already exists!"),
    CATEGORY_DELETE_FAILED("Specified category is not empty!"),
    CATEGORY_DOES_NOT_EXIST("Specified category does not exist!"),
    EXPENSE_DELETE_FAILED("Specified expense does not exist!");

    private final String message;

    Warns(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
