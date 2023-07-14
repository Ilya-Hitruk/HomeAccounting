package org.accounting.io;

import org.accounting.exceptions.InvalidInputException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ConsoleReader {
    private final static Scanner scanner = new Scanner(System.in);
    private final static String INCORRECT_INPUT = "invalid_input";

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
        System.out.print(MessageListener.getMessage("input_category"));
        return scanner.nextLine();
    }

    public LocalDate readDate() throws InvalidInputException {
        System.out.print(MessageListener.getMessage("input_date"));
        String dateString = scanner.nextLine();
        LocalDate date;
        try {
            date = LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(INCORRECT_INPUT);
        }
        return date;
    }

    public double readAmount() throws InvalidInputException {
        System.out.print((MessageListener.getMessage("input_amount")));
        String amountString = scanner.nextLine();
        double amount;
        try {
            amount = Double.parseDouble(amountString);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(INCORRECT_INPUT);
        }
        return amount;
    }

    public int readWeek() throws InvalidInputException {
        int week;
        try {
            System.out.print((MessageListener.getMessage("input_week")));
             week = Integer.parseInt(scanner.nextLine());
            if (week > 5 || week < 1) {
                throw new InvalidInputException("incorrect_input_week");
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException("incorrect_input_week");
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
        int year;
        try {
            System.out.print((MessageListener.getMessage("input_year")));
            year = Integer.parseInt(scanner.nextLine());
            if (year > LocalDate.now().getYear()) {
                throw new InvalidInputException("incorrect_input_year");
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException("incorrect_input_year");
        }
        return year;
    }

    public int readMonth() throws InvalidInputException {
        int month;
        try {
            System.out.print((MessageListener.getMessage("input_month")));
            month = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException | DateTimeException e) {
            throw new InvalidInputException("incorrect_input_month");
        }
        return month;
    }
}