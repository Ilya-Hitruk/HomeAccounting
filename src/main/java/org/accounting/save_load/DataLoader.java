package org.accounting.save_load;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.accounting.accounting_data.Category;
import org.accounting.interfaces.Loader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataLoader implements Loader {
    private static final String JSON_FILE_PATH = "expenses.json";
    private static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    @Override
    public List<Category> load() {
        List<Category> loadedCategories = new ArrayList<>();

        File file = new File(JSON_FILE_PATH);
        if (file.exists()) {
            try {
                loadedCategories = objectMapper.readValue(file, new TypeReference<>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return loadedCategories;
    }
}
