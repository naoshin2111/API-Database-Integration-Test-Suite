package config;

import lombok.experimental.UtilityClass;
import utils.ConfigReader;

@UtilityClass
public class DatabaseConfig {

    private static final ConfigReader CONFIG_READER = new ConfigReader("./src/test/resources/database.json");

    public static String getDatabaseUrl() {
        return CONFIG_READER.getConfigValue("databaseUrl");
    }

    public static String getUserName(){
        return CONFIG_READER.getConfigValue("username");
    }

    public static String getPassword(){
        return CONFIG_READER.getConfigValue("password");
    }
}
