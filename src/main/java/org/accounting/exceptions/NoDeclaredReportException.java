package org.accounting.exceptions;

public class NoDeclaredReportException extends Exception {
    public NoDeclaredReportException() {
        super("No such report!>");
    }
}
