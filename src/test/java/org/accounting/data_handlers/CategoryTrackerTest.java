package org.accounting.data_handlers;

import org.accounting.validity.ExecutionCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class CategoryTrackerTest {
    @Test
    void addCategoryShouldAddCategory() {
        String categoryName = "Coffee";
        Assertions.assertEquals(ExecutionCode.CODE_SUCCESS, new CategoryTracker().addCategory(categoryName));
    }

    @Test
    void addCategoryShouldWarnOfExistingCategory() {
        String categoryName = "Coffee";
        Assertions.assertEquals(ExecutionCode.CODE_FAILED_1, new CategoryTracker().addCategory(categoryName));
    }

    @Test
    void removeCategoryShouldRemoveCategory() {
        String categoryName = "Coffee";
        Assertions.assertEquals(ExecutionCode.CODE_SUCCESS, new CategoryTracker().removeCategory(categoryName));
    }

    @Test
    void removeCategoryShouldWarnOfNotExistingCategory() {
        String categoryName = "Coffee";
        Assertions.assertEquals(ExecutionCode.CODE_FAILED_1, new CategoryTracker().removeCategory(categoryName));
    }

    @Test
    void removeCategoryShouldWarnOfNotEmptyCategory() {
        String categoryName = "Shopping";
        Assertions.assertEquals(ExecutionCode.CODE_FAILED_2, new CategoryTracker().removeCategory(categoryName));
    }
}