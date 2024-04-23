package steps.api;

import constants.Endpoints;
import constants.Parameters;
import io.restassured.response.Response;
import models.api.UserResponse;

import java.util.Arrays;

public class UserClient extends BaseApiClient {

    public Response getUserById(int userId) {
        return getBaseRequestSpecification()
                .pathParam(Parameters.ID, userId)
                .get(Endpoints.USER_BY_ID);
    }

    public Response getAllUsers() {
        return getBaseRequestSpecification()
                .get(Endpoints.USERS);
    }
    
    public static UserResponse getUserResponseByID(Response response, int id) {
        return Arrays.stream(response.as(UserResponse[].class))
                .filter(userResponse -> userResponse.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User Doesn't exist"));
    }
}
