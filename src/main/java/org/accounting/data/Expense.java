package org.accounting.data;

import java.beans.ConstructorProperties;
import java.time.LocalDate;
import java.util.Objects;

public class Expense {
    private final LocalDate date;
    private final double amount;
    private final Category category;


    @ConstructorProperties({"date", "amount, category"})
    public Expense(LocalDate date, double amount, Category category) {
        this.date = date;
        this.amount = amount;
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return Double.compare(expense.amount, amount) == 0 && Objects.equals(date, expense.date) && Objects.equals(category, expense.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, amount, category);
    }

    @Override
    public String toString() {
        return date.toString() + ", " +  amount + ", " + category.name();
    }
}
