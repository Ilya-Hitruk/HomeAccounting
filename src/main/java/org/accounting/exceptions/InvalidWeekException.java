package org.accounting.exceptions;

public class InvalidWeekException extends Exception {
    public InvalidWeekException(String week) {
        super(String.format("Invalid week input <%s>!\n Use as a template: Week - W (e.g: 1 ... 4 ).", week));
    }
}
