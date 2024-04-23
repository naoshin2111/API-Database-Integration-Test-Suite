package steps.database;

import database.DbUtils;
import lombok.extern.slf4j.Slf4j;
import models.database.Author;
import utils.ResultSetUtils;
import constants.Query;
import java.sql.ResultSet;
import java.util.Optional;

@Slf4j
public class AuthorTableSteps {

    public Optional<Author> findAuthorByLogin(String login) {
        String query = Query.SELECT_AUTHOR_BY_LOGIN.getFormattedQuery(login);
        ResultSet resultSet = DbUtils.select(query);
        return Optional.of(ResultSetUtils.mapToAuthor(resultSet));
    }

    public long insertAuthor(Author author) {
        String query = Query.INSERT_AUTHOR.getFormattedQuery(author.getName(), author.getLogin(), author.getEmail());
        ResultSet resultSet = DbUtils.insert(query);
        return ResultSetUtils.getIdFromResultSet(resultSet);
    }

    public Author insertAuthorIfAbsent(Author author) {
        return findAuthorByLogin(author.getLogin())
                .orElseGet(() -> {
                    long authorId = insertAuthor(author);
                    author.setId(authorId);
                    return author;
                });
    }
}
