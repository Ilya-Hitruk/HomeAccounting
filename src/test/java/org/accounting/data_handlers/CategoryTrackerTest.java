package org.accounting.data_handlers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class CategoryTrackerTest {
    @Test
    void addCategoryShouldAddCategory() {
        String categoryName = "Coffee";
        Assertions.assertEquals(0, new CategoryTracker().addCategory(categoryName));
    }

    @Test
    void addCategoryShouldWarnOfExistingCategory() {
        String categoryName = "Coffee";
        Assertions.assertEquals(1, new CategoryTracker().addCategory(categoryName));
    }

    @Test
    void removeCategoryShouldRemoveCategory() {
        String categoryName = "Coffee";
        Assertions.assertEquals(0, new CategoryTracker().removeCategory(categoryName));
    }

    @Test
    void removeCategoryShouldWarnOfNotExistingCategory() {
        String categoryName = "Coffee";
        Assertions.assertEquals(1, new CategoryTracker().removeCategory(categoryName));
    }

    @Test
    void removeCategoryShouldWarnOfNotEmptyCategory() {
        String categoryName = "Shopping";
        Assertions.assertEquals(2, new CategoryTracker().removeCategory(categoryName));
    }
}