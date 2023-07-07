package org.accounting.accounting_data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Category {
    private String name;
    private List<Expense> expenses;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
        expenses = new ArrayList<>();
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public String getName() {
        return name;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category category)) return false;
        return Objects.equals(getName(), category.getName()) && Objects.equals(getExpenses(), category.getExpenses());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getExpenses());
    }
}
