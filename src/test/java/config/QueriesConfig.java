package config;

import lombok.experimental.UtilityClass;
import utils.ConfigReader;

@UtilityClass
public class QueriesConfig {

    private static final ConfigReader CONFIG_READER = new ConfigReader("./src/test/resources/queries.json");

    public static String selectAuthorByLogin() {
        return CONFIG_READER.getConfigValue("selectAuthorByLogin");
    }

    public static String selectProjectByName() {
        return CONFIG_READER.getConfigValue("selectProjectByName");
    }

    public static String selectInsertAuthor() {
        return CONFIG_READER.getConfigValue("insertAuthor");
    }

    public static String selectInsertProject() {
        return CONFIG_READER.getConfigValue("insertProject");
    }

    public static String selectInsertSession() {
        return CONFIG_READER.getConfigValue("insertSession");
    }

    public static String selectInsertTest() {
        return CONFIG_READER.getConfigValue("insertTest");
    }

    public static String selectTestWithRepeatingID() {
        return CONFIG_READER.getConfigValue("selectTestWithRepeatingID");
    }

    public static String selectUpdateTest() {
        return CONFIG_READER.getConfigValue("updateTest");
    }

    public static String selectDeletedBatchTest() {
        return CONFIG_READER.getConfigValue("deleteBatchTest");
    }
}
