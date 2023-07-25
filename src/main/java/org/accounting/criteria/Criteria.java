package org.accounting.criteria;

import org.accounting.data.Expense;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public class Criteria {
    private List<Expense> result;
    private LocalDate day;
    private int week;
    private YearMonth month;
    private String category;

    public static CriteriaBuilder aCriteria() {
        return new CriteriaBuilder();
    }

    public void setResult(List<Expense> result) {
        this.result = result;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public void setMonth(YearMonth month) {
        this.month = month;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Expense> getResult() {
        return result;
    }

    public LocalDate getDay() {
        return day;
    }

    public int getWeek() {
        return week;
    }

    public YearMonth getMonth() {
        return month;
    }

    public String getCategory() {
        return category;
    }
}