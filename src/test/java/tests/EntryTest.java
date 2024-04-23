package tests;

import constants.ApiResponsesPaths;
import data.TestTableGenerator;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.api.PostResponse;
import models.api.UserResponse;
import models.database.TestTable;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import steps.api.PostClient;
import steps.api.UserClient;
import steps.database.TestTableSteps;
import utils.JsonMapperUtils;
import utils.RandomUtils;
import utils.ResponseUtils;
import static steps.api.UserClient.getUserResponseByID;

public class EntryTest extends BaseTest {

    private static final int VALID_POST_ID = 99;
    private static final int NON_EXISTENT_POST_ID = 150;
    private static final int VALID_ID = 5;
    private static final int VALID_USER_ID = 10;
    private static final int USER_ID = 1;
    private static final int ID = 101;
    private final PostClient postClient = new PostClient();
    private final UserClient userClient = new UserClient();
    private final SoftAssert softAssert = new SoftAssert();
    private final TestTableSteps testTableSteps = new TestTableSteps();

    @Test
    public void testGetAllPosts() {
        Response response = postClient.getAllPosts();

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Status code should be 200");
        Assert.assertTrue(postClient.arePostsSortedById(), "Posts are not sorted by ID.");
        ResponseUtils.assertValidContentType(response, ContentType.JSON);
    }

    @Test
    public void testGetPostById() {
        Response response = postClient.getPostById(VALID_POST_ID);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Status code should be 200");

        PostResponse postResponse = response.as(PostResponse.class);

        softAssert.assertEquals(postResponse.getId(), VALID_POST_ID, "Post ID does not match");
        softAssert.assertEquals(postResponse.getUserId(), VALID_USER_ID, "User ID does not match");
        softAssert.assertFalse(postResponse.getTitle().isEmpty(), "Title is empty");
        softAssert.assertFalse(postResponse.getBody().isEmpty(), "Body is empty");

        softAssert.assertAll();
    }

    @Test
    public void testGetNonExistentPost() {
        Response response = postClient.getPostById(NON_EXISTENT_POST_ID);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND, "Status code should be 404");
        Assert.assertTrue(ResponseUtils.isResponseBodyEmptyOrEmptyJsonObject(response), "Response body is not empty or an empty JSON object.");
    }

    @Test
    public void testCreatePost() {
        String randomTitle = RandomUtils.generateRandomString();
        String randomBody = RandomUtils.generateRandomString();

        PostResponse expectedPost = new PostResponse();

        expectedPost.setTitle(randomTitle);
        expectedPost.setBody(randomBody);
        expectedPost.setUserId(USER_ID);

        Response response = postClient.createPost(expectedPost);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED, "Status code should be 201");

        PostResponse createdPost = response.as(PostResponse.class);

        softAssert.assertEquals(createdPost.getTitle(), randomTitle, "Titles do not match");
        softAssert.assertEquals(createdPost.getBody(), randomBody, "Bodies do not match");
        softAssert.assertEquals(createdPost.getUserId(), USER_ID, "User ID should be 1");
        softAssert.assertEquals(createdPost.getId(), ID, "Post ID should be present in the response");

        softAssert.assertAll();
    }

    @Test
    public void testGetAllUsers() {
        Response response = userClient.getAllUsers();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Status code should be 200");
        ResponseUtils.assertValidContentType(response, ContentType.JSON);

        UserResponse actualUserResponse = getUserResponseByID(response, VALID_ID);
        UserResponse expectedUserResponse = JsonMapperUtils.fromJsonFile(ApiResponsesPaths.EXPECTED_USER_JSON_PATH, UserResponse.class);

        Assert.assertEquals(actualUserResponse, expectedUserResponse, "The actual user response does not match the expected user response");
    }

    @Test
    public void testCompareUserWithID() {
        UserResponse expectedUserResponse = JsonMapperUtils.fromJsonFile(ApiResponsesPaths.EXPECTED_USER_JSON_PATH, UserResponse.class);

        Response response = userClient.getUserById(VALID_ID);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Status code should be 200");
        UserResponse actualUserResponse = response.as(UserResponse.class);
        Assert.assertEquals(actualUserResponse, expectedUserResponse, "The actual user response does not match the expected user response");
    }

    @AfterMethod
    public void afterTestMethod(ITestResult testResult) {
        TestTable test = TestTableGenerator.createTestFromResult(testResult, super.author.getId(), super.project.getId(), super.session.getId());
        long savedTestId = testTableSteps.insertTest(test);
        Assert.assertTrue(savedTestId > 0, "Test was not saved successfully. Test ID: " + savedTestId);
    }
}
