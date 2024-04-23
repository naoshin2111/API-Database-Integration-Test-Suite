package tests;

import data.TestTableGenerator;
import models.database.TestTable;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import steps.database.TestTableSteps;
import utils.RandomUtils;
import java.util.ArrayList;
import java.util.List;

public class DuplicateValueTests extends BaseTest {

    private static final int LIMIT = 9;
    private final TestTableSteps testTableSteps = new TestTableSteps();
    private List<Long> insertedTestIds;

    @BeforeClass
    public void setup() {
        List<TestTable> testsWithRepeatingIDs = testTableSteps.selectTestsWithRepeatingId(LIMIT);
        this.insertedTestIds = new ArrayList<>();
        for (TestTable test : testsWithRepeatingIDs) {
            test.setAuthorId(super.author.getId());
            test.setProjectId(super.project.getId());
            insertedTestIds.add(testTableSteps.insertTest(test));
        }
    }

    @Test
    public void updateTests() {
        TestTable updatedTest = TestTableGenerator.createTest(
                RandomUtils.generateRandomString(),
                RandomUtils.generateRandomString(),
                RandomUtils.generateRandomString(),
                super.author.getId(),
                super.project.getId(),
                super.session.getId()
        );

        for (Long testId : insertedTestIds) {
            int rowsUpdated = testTableSteps.updateTest(testId, updatedTest);
            Assert.assertEquals(rowsUpdated,1, String.format("Test with ID %d was not updated successfully", testId));
        }
    }

    @AfterClass
    public void cleanupInsertedTests() {
        long deletedRows = testTableSteps.deleteBatchTest(insertedTestIds);
        Assert.assertEquals(deletedRows, insertedTestIds.size(), "Tests cleanup isn't successful");
    }
}
