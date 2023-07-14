package org.accounting.saveload;

import com.opencsv.CSVWriter;
import org.accounting.data.Category;
import org.accounting.data.Expense;
import org.accounting.interfaces.Saver;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVSaver implements Saver {
    private static final String CSV_EXPENSES_FILE_PATH = "expenses.csv";
    private static final String CSV_CATEGORIES_FILE_PATH = "categories.csv";
    @Override
    public void saveExpenses(List<Expense> expenses) {
        try(CSVWriter writer = new CSVWriter(new FileWriter(CSV_EXPENSES_FILE_PATH))) {
            for (Expense expense: expenses) {
                String[] line = {expense.getDate().toString(), String.valueOf(expense.getAmount()), expense.getCategory().name()};
                writer.writeNext(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveCategories(List<Category> categories) {
        try(CSVWriter writer = new CSVWriter(new FileWriter(CSV_CATEGORIES_FILE_PATH))) {
            for (Category category: categories) {
                String[] line = {category.name()};
                writer.writeNext(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}