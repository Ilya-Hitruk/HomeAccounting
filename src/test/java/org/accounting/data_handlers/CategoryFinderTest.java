package org.accounting.data_handlers;

import org.accounting.accounting_data.Category;
import org.accounting.accounting_data.Expense;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

class CategoryFinderTest {

    @Test
    void findCategoryShouldReturnCategory() {

        String categoryName = "Shopping";

        LocalDate date1 = LocalDate.parse("2023-07-01");
        Category shopping = new Category("Shopping");
        shopping.addExpense(new Expense(date1, 1500.0));

        LocalDate date2 = LocalDate.parse("2023-07-02");
        Category coffee = new Category("Coffee");
        coffee.addExpense(new Expense(date2, 200.0));

        List<Category> categories = List.of(shopping, coffee);


        Assertions.assertEquals(shopping, new CategoryFinder().findCategory(categories, categoryName).orElseThrow());
    }

    @Test
    void findCategoryShouldReturnOptionalEmpty() {

        String categoryName = "Food";

        LocalDate date1 = LocalDate.parse("2023-07-01");
        Category shopping = new Category("Shopping");
        shopping.addExpense(new Expense(date1, 1500.0));

        LocalDate date2 = LocalDate.parse("2023-07-02");
        Category coffee = new Category("Coffee");
        coffee.addExpense(new Expense(date2, 200.0));

        List<Category> categories = List.of(shopping, coffee);



        Assertions.assertEquals(Optional.empty(), new CategoryFinder().findCategory(categories, categoryName));
    }
}