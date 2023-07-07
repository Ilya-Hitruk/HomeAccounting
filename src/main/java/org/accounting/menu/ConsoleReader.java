package org.accounting.menu;

import java.util.Scanner;

public class ConsoleReader {
    private final static Scanner scanner = new Scanner(System.in);

    public int readAction() {
        return Integer.parseInt(scanner.nextLine());
    }
    public String readTittle() {
        System.out.print(Actions.CATEGORY_TITTLE.getMessage());
        return scanner.nextLine();
    }

    public String readDate() {
        System.out.print(Actions.EXPENSE_DATE.getMessage());
        return scanner.nextLine();
    }

    public double readAmount() {
        System.out.print((Actions.EXPENSE_AMOUNT.getMessage()));
        return Double.parseDouble(scanner.nextLine());
    }
}
