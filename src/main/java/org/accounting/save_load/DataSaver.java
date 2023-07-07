package org.accounting.save_load;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.accounting.accounting_data.Category;
import org.accounting.interfaces.Saver;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DataSaver implements Saver {

    private static final String JSON_FILE_PATH = "expenses.json";
    private static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    @Override
    public void save(List<Category> categories) {
        try {
            objectMapper.writeValue(new File(JSON_FILE_PATH), categories);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
