package org.accounting.controllers;

import org.accounting.dao.CategoryStorage;
import org.accounting.dao.ExpenseStorage;
import org.accounting.data.Category;
import org.accounting.exceptions.CategoryExistsException;
import org.accounting.exceptions.CategoryNotFoundException;
import org.accounting.exceptions.NotEmptyCategoryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;

class CategoryControllerImplTest {
    ArgumentCaptor<Category> categoryArgumentCaptor;
    ExpenseStorage expenseStorage;
    CategoryStorage categoryStorage;
    CategoryControllerImpl categoryController;

    @BeforeEach
    void setUp() {
        categoryArgumentCaptor = ArgumentCaptor.forClass(Category.class);
        expenseStorage = mock(ExpenseStorage.class);
        categoryStorage = mock(CategoryStorage.class);
        categoryController = new CategoryControllerImpl(categoryStorage);
    }

    @Test
    void addCategoryShouldAddCategory() {
        when(categoryStorage.isCategoryExist("Shop"))
                .thenReturn(false);
        try {
            categoryController.addCategory("Shop");
            verify(categoryStorage).addCategory(categoryArgumentCaptor.capture());
            Category value = categoryArgumentCaptor.getValue();
            Assertions.assertEquals(value.name(),"Shop");

        } catch (CategoryExistsException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void addCategoryShouldThrowCategoryExistsException() {
        when(categoryStorage.isCategoryExist("Shop"))
                .thenReturn(true);
        Assertions.assertThrows(CategoryExistsException.class, () -> categoryController.addCategory("Shop"));
    }

    @Test
    void removeCategoryShouldRemoveCategory() {
        when(categoryStorage.isCategoryExist("Shop"))
                .thenReturn(true);
        when(categoryStorage.isCategoryEmpty("Shop"))
                .thenReturn(true);
        try {
            categoryController.removeCategory("Shop");
            verify(categoryStorage).removeCategory(categoryArgumentCaptor.capture());
            Category value = categoryArgumentCaptor.getValue();
            Assertions.assertEquals(value.name(),"Shop");

        } catch (NotEmptyCategoryException | CategoryNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void removeCategoryShouldThrowCategoryNotFoundException() {
        when(categoryStorage.isCategoryExist("Shop"))
                .thenReturn(false);
        Assertions.assertThrows(CategoryNotFoundException.class, () -> categoryController.removeCategory("Shop"));
    }

    @Test
    void removeCategoryShouldThrowNotEmptyCategoryException() {
        when(categoryStorage.isCategoryExist("Shop"))
                .thenReturn(true);
        when(categoryStorage.isCategoryEmpty("Shop"))
                .thenReturn(false);
        Assertions.assertThrows(NotEmptyCategoryException.class, () -> categoryController.removeCategory("Shop"));
    }
}