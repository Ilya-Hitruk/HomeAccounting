package org.accounting.interfaces;

import org.accounting.validity.ExecutionCode;

public interface CategoryHandler {
    ExecutionCode addCategory(String title);
    ExecutionCode removeCategory(String title);
}
