package org.accounting.io;

import org.accounting.dao.CategoryStorage;
import org.accounting.data.Category;
import org.accounting.exceptions.InvalidInputException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleReader {
    private static final CategoryStorage CATEGORY_STORAGE = CategoryStorage.getInstance();
    private static final MessageListener MESSAGE_LISTENER = MessageListener.getInstance();
    private final static Scanner scanner = new Scanner(System.in);
    public int readAction() {
        String actionInput = scanner.nextLine();
        int action;
        try {
            action = Integer.parseInt(actionInput);
        } catch (NumberFormatException e) {
            return -1;
        }
        return action;
    }
    public String readTittle() {
        System.out.print(MESSAGE_LISTENER.getMessage("input_category"));
        return scanner.nextLine();
    }

    public LocalDate readDate() throws InvalidInputException {
        System.out.print(MESSAGE_LISTENER.getMessage("input_date"));
        String dateString = scanner.nextLine();
        LocalDate date;
        try {
            date = LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw InvalidInputException.invalidExpense("date = " + dateString);
        }
        return date;
    }

    public double readAmount() throws InvalidInputException {
        System.out.print((MESSAGE_LISTENER.getMessage("input_amount")));
        String amountString = scanner.nextLine();
        double amount;
        try {
            amount = Double.parseDouble(amountString);
        } catch (NumberFormatException e) {
            throw InvalidInputException.invalidExpense("amount = " + amountString);
        }
        return amount;
    }

    public int readWeek() throws InvalidInputException {
        System.out.print((MESSAGE_LISTENER.getMessage("input_week")));
        String inputWeek = scanner.nextLine();
        int week;
        try {
            week = Integer.parseInt(inputWeek);
            if (week > 5 || week < 1) {
                throw InvalidInputException.invalidWeek(inputWeek);
            }
        } catch (NumberFormatException e) {
            throw InvalidInputException.invalidWeek(inputWeek);
        }
        return week;
    }

    public YearMonth getYearAndMonth() throws InvalidInputException {
        YearMonth yearMonth;
        try {
            yearMonth = YearMonth.of(readYear(), readMonth());
        } catch (InvalidInputException e) {
            throw new InvalidInputException(e.getMessage());
        }
        return yearMonth;
    }

    public int readYear() throws InvalidInputException {
        System.out.print((MESSAGE_LISTENER.getMessage("input_year")));
        String inputYear = scanner.nextLine();
        int year;
        try {
            year = Integer.parseInt(inputYear);
            if (year > LocalDate.now().getYear()) {
                throw InvalidInputException.invalidYear(inputYear);
            }
        } catch (NumberFormatException e) {
            throw InvalidInputException.invalidYear(inputYear);
        }
        return year;
    }

    public int readMonth() throws InvalidInputException {
        System.out.print((MESSAGE_LISTENER.getMessage("input_month")));
        String inputMonth = scanner.nextLine();
        int month;
        try {
            month = Integer.parseInt(inputMonth);
        } catch (NumberFormatException | DateTimeException e) {
            throw InvalidInputException.invalidMonth(inputMonth);
        }
        return month;
    }

    public List<Category> chooseCategories() {
        List<String> presentCategories = new ArrayList<>(CATEGORY_STORAGE.getCategories().stream().map(Category::name).toList());
        List<Category> resultList = new ArrayList<>();
        String chosenCategory = "";

        while (!chosenCategory.equals("Stop")) {
            System.out.println(presentCategories);
            System.out.print(MESSAGE_LISTENER.getMessage("choose_category"));

            chosenCategory = scanner.nextLine();
            if (presentCategories.contains(chosenCategory)) {
                resultList.add(new Category(chosenCategory));
                presentCategories.remove(chosenCategory);
                System.out.println("Category <" + chosenCategory + "> has been chosen\n");
                continue;
            }
            System.out.println("No such category <" + chosenCategory + ">\n");
        }
        return resultList;
    }
}