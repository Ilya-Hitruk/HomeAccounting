package org.accounting.data;

import java.util.Objects;

public record Category(String name) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}