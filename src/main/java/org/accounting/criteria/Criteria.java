package org.accounting.criteria;

import org.accounting.data.Category;

import java.time.LocalDate;
import java.util.List;

public class Criteria {
    private final List<Category> categories;
    private final LocalDate from;
    private final LocalDate to;

    public Criteria(List<Category> categories, LocalDate from, LocalDate to) {
        this.categories = categories;
        this.from = from;
        this.to = to;
    }
    public List<Category> getCategories() {
        return categories;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }
}