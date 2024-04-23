package constants;

import config.QueriesConfig;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Query {

    INSERT_AUTHOR(QueriesConfig.selectInsertAuthor()),
    INSERT_PROJECT(QueriesConfig.selectInsertProject()),
    SELECT_AUTHOR_BY_LOGIN(QueriesConfig.selectAuthorByLogin()),
    SELECT_PROJECT_BY_NAME(QueriesConfig.selectProjectByName()),
    INSERT_SESSION(QueriesConfig.selectInsertSession()),
    INSERT_TEST(QueriesConfig.selectInsertTest()),
    SELECT_TEST_WITH_REPEATING_ID(QueriesConfig.selectTestWithRepeatingID()),
    UPDATE_TEST(QueriesConfig.selectUpdateTest()),
    DELETE_BATCH_TEST(QueriesConfig.selectDeletedBatchTest());

    private final String query;

    public String getFormattedQuery(Object... args) {
        return String.format(this.query, args);
    }
}
