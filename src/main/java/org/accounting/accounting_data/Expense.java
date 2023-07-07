package org.accounting.accounting_data;

import java.beans.ConstructorProperties;
import java.time.LocalDate;
import java.util.Objects;

public class Expense {
    private final LocalDate date;
    private final double amount;



    @ConstructorProperties({"date", "amount"})
    public Expense(LocalDate date, double amount) {
        this.date = date;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Expense expense)) return false;
        return Double.compare(expense.getAmount(), getAmount()) == 0 && Objects.equals(getDate(), expense.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getAmount());
    }
}
