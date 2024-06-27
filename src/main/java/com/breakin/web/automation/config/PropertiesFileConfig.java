package com.breakin.web.automation.config;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileConfig {
	// Properties file path
    private static final String FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/config.properties";

    public static String getTheKeyValue(String keyName) throws IOException {
        FileInputStream inputStream = null;
        try {
            Properties properties = new Properties();
            inputStream = new FileInputStream(FILE_PATH);
            properties.load(inputStream);
            return properties.getProperty(keyName);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public static void setData(String key, String value) throws IOException {
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            Properties properties = new Properties();
            inputStream = new FileInputStream(FILE_PATH);
            properties.load(inputStream);

            // Check if the key already exists, and update its value
            if (properties.containsKey(key)) {
                properties.setProperty(key, value);
            } else {
                // If the key does not exist, add a new key-value pair
                properties.put(key, value);
            }

            // Save the updated properties file
            outputStream = new FileOutputStream(FILE_PATH);
            properties.store(outputStream, null);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
