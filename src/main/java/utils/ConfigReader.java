package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties properties = new Properties();

    private ConfigReader() {
        // Prevent object creation
    }

    static {
        loadProperties();
    }

    private static void loadProperties() {

        String filePath =
                System.getProperty("user.dir")
                        + "/src/test/resources/config.properties";

        try (FileInputStream fis = new FileInputStream(filePath)) {

            properties.load(fis);

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to load config.properties file: "
                            + filePath,
                    e
            );
        }
    }

    public static String get(String key) {

        // First check JVM/System property
        String systemValue =
                System.getProperty(key);

        if (systemValue != null
                && !systemValue.trim().isEmpty()) {

            return systemValue.trim();
        }

        // Otherwise read from config.properties
        String value =
                properties.getProperty(key);

        if (value == null
                || value.trim().isEmpty()) {

            throw new RuntimeException(
                    "Configuration property not found: "
                            + key
            );
        }

        return value.trim();
    }

    public static int getInt(String key) {

        return Integer.parseInt(get(key));
    }
}