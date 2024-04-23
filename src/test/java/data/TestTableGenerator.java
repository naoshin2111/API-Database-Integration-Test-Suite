package data;

import config.EnvironmentConfig;
import lombok.experimental.UtilityClass;
import models.database.TestTable;
import org.testng.ITestResult;
import java.sql.Timestamp;

@UtilityClass
public class TestTableGenerator {

    public static TestTable createTest(String name, String env, String browser, Long authorId, Long projectId, Long sessionId) {
        return TestTable.builder()
                .name(name)
                .env(env)
                .browser(browser)
                .authorId(authorId)
                .projectId(projectId)
                .sessionId(sessionId)
                .endTime(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public static TestTable createTestFromResult(ITestResult testResult, Long authorId, Long projectId, Long sessionId) {
        return TestTable.builder()
                .name(testResult.getInstanceName())
                .statusId(testResult.getStatus())
                .methodName(testResult.getName())
                .projectId(projectId)
                .sessionId(sessionId)
                .startTime(new Timestamp(testResult.getStartMillis()))
                .endTime(new Timestamp(testResult.getEndMillis()))
                .env(EnvironmentConfig.getEnv())
                .browser(EnvironmentConfig.getBrowser())
                .authorId(authorId)
                .build();
    }
}
