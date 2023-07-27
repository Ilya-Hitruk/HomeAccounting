package org.accounting.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MessageListener {
    private static MessageListener messageListener;
    private static final String PATH_TO_PROPERTIES = "src/main/resources/message.properties";
    private final Properties properties;

    private MessageListener() {
        properties = new Properties();

        try (InputStream inputStream = new FileInputStream(PATH_TO_PROPERTIES)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MessageListener getInstance() {
        if (messageListener == null) {
            messageListener = new MessageListener();
        }
        return messageListener;
    }

    public String getMessage(String key) {
        return properties.getProperty(key);
    }
}