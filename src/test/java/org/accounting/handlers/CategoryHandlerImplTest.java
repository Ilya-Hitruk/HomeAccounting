package org.accounting.handlers;

import org.accounting.exceptions.CategoryExistsException;
import org.accounting.exceptions.CategoryNotFoundException;
import org.accounting.exceptions.NotEmptyCategoryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CategoryHandlerImplTest {

    @Test
    void addCategoryShouldAddCategoryAndReturnSuccess() throws CategoryExistsException {
        String expected = "category_add_success";
        Assertions.assertEquals(expected, new CategoryHandlerImpl().addCategory("Activities"));
    }

    @Test
    void addCategoryShouldThrowException() {
        Assertions.assertThrows(CategoryExistsException.class, () -> new CategoryHandlerImpl().addCategory("Coffee"));
    }

    @Test
    void removeCategoryShouldRemoveCategoryAndReturnSuccess() throws NotEmptyCategoryException, CategoryNotFoundException {
        String expected = "category_delete_success";
        Assertions.assertEquals(expected, new CategoryHandlerImpl().removeCategory("Coffee"));
    }

    @Test
    void removeCategoryShouldThrowCategoryNotFoundException() {
        Assertions.assertThrows(CategoryNotFoundException.class, () -> new CategoryHandlerImpl().removeCategory("Coffee"));
    }

    @Test
    void removeCategoryShouldThrowNotEmptyCategoryException() {
        Assertions.assertThrows(NotEmptyCategoryException.class, () -> new CategoryHandlerImpl().removeCategory("Shop"));
    }

    @Test
    void isCategoryExistShouldReturnTrue() {
        Assertions.assertTrue(new CategoryHandlerImpl().isCategoryExist("Shop"));
    }

    @Test
    void isCategoryExistShouldReturnFalse() {
        Assertions.assertFalse(new CategoryHandlerImpl().isCategoryExist("SomeCategory"));
    }

    @Test
    void isCategoryEmptyShouldReturnTrue() {
        Assertions.assertTrue(new CategoryHandlerImpl().isCategoryEmpty("Coffee"));
    }

    @Test
    void isCategoryEmptyShouldReturnFalse() {
        Assertions.assertFalse(new CategoryHandlerImpl().isCategoryEmpty("Shop"));
    }
}