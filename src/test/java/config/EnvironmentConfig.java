package config;

import lombok.experimental.UtilityClass;
import utils.ConfigReader;

@UtilityClass
public class EnvironmentConfig {

    private static final ConfigReader CONFIG_READER = new ConfigReader("./src/test/resources/environment.json");

    public static String getBaseUri() {
        return CONFIG_READER.getConfigValue("baseUri");
    }

    public static String getEnv() {
        return CONFIG_READER.getConfigValue("env");
    }

    public static String getBrowser() {
        return CONFIG_READER.getConfigValue("browser");
    }
}
