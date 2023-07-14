package org.accounting.io;

import org.accounting.data.Expense;

import java.util.List;

public class Notifier {

    public void showMenu() {
        System.out.print(MessageListener.getMessage("menu"));
    }

    public void showReportMenu() {
        System.out.print(MessageListener.getMessage("report_menu"));
    }

    public void showReport(List<Expense> expenses, double totalAmount) {
        expenses.forEach(System.out::println);
        System.out.print("Total amount = " + totalAmount + "\n");
    }
}