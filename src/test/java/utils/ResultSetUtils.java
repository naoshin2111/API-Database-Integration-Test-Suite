package utils;

import constants.Columns;
import lombok.experimental.UtilityClass;
import models.database.Author;
import models.database.Project;
import models.database.TestTable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ResultSetUtils {

    public static Author mapToAuthor(ResultSet resultSet) {
        try {
            Author author = new Author();

            if (resultSet.next()) {
                author.setId(resultSet.getLong(Columns.ID));
                author.setName(resultSet.getString(Columns.NAME));
                author.setLogin(resultSet.getString(Columns.Author.LOG_IN));
                author.setEmail(resultSet.getString(Columns.Author.EMAIL));
            }
            return author;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Project mapToProject(ResultSet resultSet) {
        try {
            Project project = new Project();

            if (resultSet.next()) {
                project.setId(resultSet.getLong(Columns.ID));
                project.setName(resultSet.getString(Columns.NAME));
            }
            return project;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static TestTable mapToTest(ResultSet resultSet) {
        try {
            return TestTable.builder()
                    .id(resultSet.getLong(Columns.ID))
                    .name(resultSet.getString(Columns.NAME))
                    .statusId(resultSet.getInt(Columns.Test.STATUS_ID))
                    .methodName(resultSet.getString(Columns.Test.METHOD_NAME))
                    .projectId(resultSet.getLong(Columns.Test.PROJECT_ID))
                    .sessionId(resultSet.getLong(Columns.Test.SESSION_ID))
                    .endTime(resultSet.getTimestamp(Columns.Test.END_TIME))
                    .env(resultSet.getString(Columns.Test.ENV))
                    .browser(resultSet.getString(Columns.Test.BROWSER))
                    .authorId(resultSet.getLong(Columns.Test.AUTHOR_ID))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<TestTable> mapToTestList(ResultSet resultSet) {
        List<TestTable> tests = new ArrayList<>();
        try {
            while (resultSet.next()) {
                tests.add(mapToTest(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tests;
    }

    public static long getIdFromResultSet(ResultSet resultSet) {

        int generatedKey = 0;
        try {
            if (resultSet.next()) {
                generatedKey = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return generatedKey;
    }
}
