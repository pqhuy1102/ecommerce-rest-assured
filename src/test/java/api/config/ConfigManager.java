package api.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigManager {

    private static final Properties PROPERTIES = loadProperties();

    private ConfigManager() {
    }

    private static Properties loadProperties() {
        String environment = System.getProperty("env", "qa");
        String filePath = String.format("config/%s.properties", environment);

        Properties properties = new Properties();

        try (InputStream inputStream = ConfigManager.class
                .getClassLoader()
                .getResourceAsStream(filePath)) {

            if (inputStream == null) {
                throw new IllegalStateException(
                        "Configuration file not found: " + filePath
                );
            }

            properties.load(inputStream);
            return properties;

        } catch (IOException exception) {
            throw new IllegalStateException(
                    "Cannot load configuration: " + filePath,
                    exception
            );
        }
    }

    public static String get(String key) {
        String value = System.getProperty(key);

        if (value == null || value.isBlank()) {
            value = PROPERTIES.getProperty(key);
        }

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    "Missing configuration property: " + key
            );
        }

        return value;
    }

    public static boolean isUiHeadless(String key){
        String value = System.getProperty(key);

        if(value == null || value.isBlank()){
            value = PROPERTIES.getProperty(key);
        }

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    "Missing configuration property: " + key
            );
        }

        return Boolean.parseBoolean(value);
    }
}
