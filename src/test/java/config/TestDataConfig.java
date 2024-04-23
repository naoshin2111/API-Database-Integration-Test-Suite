package config;

import lombok.experimental.UtilityClass;
import utils.ConfigReader;

@UtilityClass
public class TestDataConfig {

    private static final ConfigReader CONFIG_READER = new ConfigReader("./src/test/resources/testData.json");

    public static String getAuthorLogin() {
        return CONFIG_READER.getConfigValue("authorLogin");
    }

    public static String getProjectName() {
        return CONFIG_READER.getConfigValue("projectName");
    }

    public static String getBuildNumber() {
        return CONFIG_READER.getConfigValue("buildNumber");
    }
}
