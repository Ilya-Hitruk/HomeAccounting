package org.accounting.saveload;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.accounting.data.Category;
import org.accounting.data.Expense;
import org.accounting.interfaces.Loader;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CSVLoader implements Loader {
    private static final String CSV_EXPENSES_FILE_PATH = "expenses.csv";
    private static final String CSV_CATEGORIES_FILE_PATH = "categories.csv";
    @Override
    public List<Expense> loadExpenses() {
        List<Expense> expenses = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(CSV_EXPENSES_FILE_PATH))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                LocalDate date = LocalDate.parse(nextLine[0]);
                double amount = Double.parseDouble(nextLine[1]);
                Category category = new Category(nextLine[2]);
                expenses.add(new Expense(date, amount, category));
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return expenses;
    }

    @Override
    public List<Category> loadCategories() {
        List<Category> categories = new ArrayList<>();
        try(CSVReader reader = new CSVReader(new FileReader(CSV_CATEGORIES_FILE_PATH))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                categories.add(new Category(nextLine[0]));
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }
}
