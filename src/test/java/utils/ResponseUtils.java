package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ResponseUtils {
    private static final String EMPTY_JSON_OBJECT = "{}";

    public static boolean isResponseBodyEmptyOrEmptyJsonObject(Response response) {
        String responseBody = response.getBody().asString().trim();
        return responseBody.isEmpty() || EMPTY_JSON_OBJECT.equals(responseBody);
    }

    public static void assertValidContentType(Response response, ContentType contentType) {
        response.then()
                .assertThat()
                .contentType(contentType);
    }
}
