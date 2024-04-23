package steps.database;

import constants.Query;
import database.DbUtils;
import lombok.extern.slf4j.Slf4j;
import models.database.TestTable;
import utils.ResultSetUtils;
import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class TestTableSteps {

    public long insertTest(TestTable test) {
        String query = Query.INSERT_TEST.getFormattedQuery(
                test.getName(), test.getStatusId(), test.getMethodName(),
                test.getProjectId(), test.getSessionId(), test.getEndTime(),
                test.getEnv(), test.getBrowser(), test.getAuthorId());
        ResultSet resultSet = DbUtils.insert(query);
        return ResultSetUtils.getIdFromResultSet(resultSet);
    }

    public List<TestTable> selectTestsWithRepeatingId(int limit) {
        String query = Query.SELECT_TEST_WITH_REPEATING_ID.getFormattedQuery(limit);
        ResultSet resultSet = DbUtils.select(query);
        return ResultSetUtils.mapToTestList(resultSet);
    }

    public int updateTest(Long testId, TestTable test) {
        String query = Query.UPDATE_TEST.getFormattedQuery(
                test.getName(), test.getEnv(), test.getBrowser(), testId
        );
        return DbUtils.update(query);
    }

    public long deleteBatchTest(List<Long> ids) {
        String idList = ids.stream().map(Object::toString).collect(Collectors.joining(", "));
        String query = Query.DELETE_BATCH_TEST.getFormattedQuery(idList);
        return DbUtils.delete(query);
    }
}
