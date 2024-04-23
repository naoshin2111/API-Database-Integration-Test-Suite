package tests;

import config.TestDataConfig;
import database.DatabaseConnection;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import models.database.Author;
import models.database.Project;
import models.database.Session;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import steps.database.AuthorTableSteps;
import steps.database.ProjectTableSteps;
import steps.database.SessionTableSteps;
import utils.RandomUtils;

public abstract class BaseTest {

    protected final AuthorTableSteps authorTableSteps = new AuthorTableSteps();
    protected final ProjectTableSteps projectTableSteps = new ProjectTableSteps();
    protected final SessionTableSteps sessionTableSteps = new SessionTableSteps();
    protected Session session;
    protected Project project;
    protected Author author;

    @BeforeSuite
    public void setupRestAssured() {
        RestAssured.filters(new RequestLoggingFilter(),
                new ResponseLoggingFilter());
    }

    @BeforeSuite
    protected void preSetup() {
        String login = TestDataConfig.getAuthorLogin();

        Author newAuthor = new Author();

        newAuthor.setLogin(login);
        newAuthor.setName(RandomUtils.generateRandomString());
        newAuthor.setEmail(RandomUtils.generateRandomString());

        author = authorTableSteps.insertAuthorIfAbsent(newAuthor);

        String projectName = TestDataConfig.getProjectName();

        Project newProject = new Project();
        newProject.setName(projectName);

        project = projectTableSteps.insertProjectIfAbsent(newProject);
    }

    @BeforeSuite
    public void initializeSession() {
        session = new Session();

        session.setSessionKey(RandomUtils.generateRandomString());
        session.setBuildNumber(Long.parseLong(TestDataConfig.getBuildNumber()));
        Long sessionId = sessionTableSteps.insertSession(session);
        session.setId(sessionId);
    }

    @AfterSuite
    public void closeDatabaseConnection() {
        DatabaseConnection.getInstance().closeConnection();
    }
}
