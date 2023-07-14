package org.accounting.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MessageListener {
    private static final Properties properties = new Properties();
    private static final String PATH_TO_PROPERTIES = "src/main/resources/message.properties";

    private MessageListener(){}
    public static String getMessage(String key) {
        try (InputStream inputStream = new FileInputStream(PATH_TO_PROPERTIES)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }
}