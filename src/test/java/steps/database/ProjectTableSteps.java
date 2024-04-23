package steps.database;

import constants.Query;
import lombok.extern.slf4j.Slf4j;
import models.database.Project;
import database.DbUtils;
import utils.ResultSetUtils;
import java.sql.*;
import java.util.Optional;

@Slf4j
public class ProjectTableSteps {

    public Optional<Project> findProjectByName(String name) {
        String query = Query.SELECT_PROJECT_BY_NAME.getFormattedQuery(name);
        ResultSet resultSet = DbUtils.select(query);
        return Optional.of(ResultSetUtils.mapToProject(resultSet));
    }

    public long insertProject(Project project) {
        String query = Query.INSERT_PROJECT.getFormattedQuery(project.getName());
        ResultSet resultSet = DbUtils.insert(query);
        return ResultSetUtils.getIdFromResultSet(resultSet);
    }

    public Project insertProjectIfAbsent(Project project) {
        return findProjectByName(project.getName())
                .orElseGet(() ->{
                    long projectId = insertProject(project);
                    project.setId(projectId);
                    return project;
                });
    }
}
