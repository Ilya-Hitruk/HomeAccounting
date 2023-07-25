package org.accounting.io;

import org.accounting.exceptions.InvalidInputException;
import org.accounting.exceptions.InvalidMonthException;
import org.accounting.exceptions.InvalidWeekException;
import org.accounting.exceptions.InvalidYearException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ConsoleReader {

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
            throw new InvalidInputException("date = " + dateString);
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
            throw new InvalidInputException("amount = " + amountString);
        }
        return amount;
    }

    public int readWeek() throws InvalidWeekException {
        System.out.print((MESSAGE_LISTENER.getMessage("input_week")));
        String inputWeek = scanner.nextLine();
        int week;
        try {
            week = Integer.parseInt(inputWeek);
            if (week > 5 || week < 1) {
                throw new InvalidWeekException(inputWeek);
            }
        } catch (NumberFormatException e) {
            throw new InvalidWeekException(inputWeek);
        }
        return week;
    }

    public YearMonth getYearAndMonth() throws InvalidYearException, InvalidMonthException {
        YearMonth yearMonth;
        try {
            yearMonth = YearMonth.of(readYear(), readMonth());
        } catch (InvalidYearException e) {
            throw new InvalidYearException(e.getMessage());
        } catch (InvalidMonthException e) {
            throw new InvalidMonthException(e.getMessage());
        }
        return yearMonth;
    }

    public int readYear() throws InvalidYearException {
        System.out.print((MESSAGE_LISTENER.getMessage("input_year")));
        String inputYear = scanner.nextLine();
        int year;
        try {
            year = Integer.parseInt(inputYear);
            if (year > LocalDate.now().getYear()) {
                throw new InvalidYearException(inputYear);
            }
        } catch (NumberFormatException e) {
            throw new InvalidYearException(inputYear);
        }
        return year;
    }

    public int readMonth() throws InvalidMonthException {
        System.out.print((MESSAGE_LISTENER.getMessage("input_month")));
        String input = scanner.nextLine();
        int month;
        try {
            month = Integer.parseInt(input);
        } catch (NumberFormatException | DateTimeException e) {
            throw new InvalidMonthException(input);
        }
        return month;
    }
}