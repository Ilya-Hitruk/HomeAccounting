package org.accounting.criteria;

import org.accounting.data.Expense;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

final class CriteriaBuilder {
    private List<Expense> result;
    private LocalDate day;
    private int week;
    private YearMonth month;
    private String category;

    CriteriaBuilder() {
    }

    public CriteriaBuilder withResult(List<Expense> result) {
        this.result = result;
        return this;
    }

    public CriteriaBuilder withDay(LocalDate day) {
        this.day = day;
        return this;
    }

    public CriteriaBuilder withWeek(int week) {
        this.week = week;
        return this;
    }

    public CriteriaBuilder withMonth(YearMonth month) {
        this.month = month;
        return this;
    }

    public CriteriaBuilder withCategory(String category) {
        this.category = category;
        return this;
    }

    public Criteria build() {
        Criteria criteria = new Criteria();
        criteria.setResult(result);
        criteria.setDay(day);
        criteria.setWeek(week);
        criteria.setMonth(month);
        criteria.setCategory(category);
        return criteria;
    }
}
