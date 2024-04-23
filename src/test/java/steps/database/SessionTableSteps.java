package steps.database;

import constants.Query;
import database.DbUtils;
import models.database.Session;
import utils.ResultSetUtils;
import java.sql.ResultSet;

public class SessionTableSteps {

    public long insertSession(Session session) {
        String query = Query.INSERT_SESSION.getFormattedQuery(session.getSessionKey(), session.getBuildNumber());
        ResultSet resultSet = DbUtils.insert(query);
        return ResultSetUtils.getIdFromResultSet(resultSet);
    }
}
